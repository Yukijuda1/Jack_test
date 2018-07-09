package liuxiaoran.servletPackage;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.userDAO;
import liuxiaoran.tools.sendCode;

/**
 * Servlet implementation class forgetServlet
 */
@WebServlet("/forget")
public class forgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if(method.equals("check")) {
		String username=request.getParameter("username");
		if(!username.equals("")) {
		String resultData=null;
	/*	c3p0Pool pool=new c3p0Pool();
		Connection conn=pool.getConnection();*/
		userDAO dao=new userDAOimp(ConnectionContext.getInstance().get());
		user u=dao.getUser(username);
		if(u!=null) {
			resultData="yes";
		}
		else{
			resultData="no";
		}
		response.getWriter().print(resultData);//这步将resultData放入data里
	}
		else {
		response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
	}
		}
		else if(method.equals("email")) {
			String username=request.getParameter("username");
            userDAO dao=new userDAOimp(ConnectionContext.getInstance().get());
            String email=dao.getUser(username).getEmail();
			int code = (int)(Math.random()*(9999-1000+1))+1000;
			sendCode send=new sendCode();
			try {
				send.send(email, username, code);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(code);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		userDAO dao=new userDAOimp(ConnectionContext.getInstance().get());
		dao.updatePassword(username, password);
		response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
	}

}
