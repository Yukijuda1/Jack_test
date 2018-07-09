package liuxiaoran.servletPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.authorityDAOimp;
import liuxiaoran.daoPackage.authorityDAO;

/**
 * Servlet implementation class mySpaceServlet
 */
@WebServlet("/mySpace")
public class mySpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mySpaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	user u=(user) request.getSession().getAttribute("user");
	if(u!=null) {
		authorityDAO dao=new authorityDAOimp(ConnectionContext.getInstance().get());
		String auth_name=dao.getAuth_name(u.getId());
		request.setAttribute("auth_name", auth_name);
		request.getRequestDispatcher("mySpace.jsp").forward(request, response);
	}
	else {
		response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
