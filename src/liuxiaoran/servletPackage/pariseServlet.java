package liuxiaoran.servletPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.daoImpPackage.commentDAOimp;
import liuxiaoran.daoPackage.commentDAO;

/**
 * Servlet implementation class pariseServlet
 */
@WebServlet("/parise")
public class pariseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pariseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String idStr=request.getParameter("id");
	String method=request.getParameter("method");
	if(idStr!=null) {
		Integer id=Integer.valueOf(idStr);
		commentDAO dao=new commentDAOimp(ConnectionContext.getInstance().get());
		if(method.equals("parise")) {
			dao.praise(id);
		}
		else if(method.equals("disparise")) {
			dao.dispraise(id);
		}
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
