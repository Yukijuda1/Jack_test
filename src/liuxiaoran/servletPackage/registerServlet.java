package liuxiaoran.servletPackage;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.authorityDAOimp;
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.authorityDAO;
import liuxiaoran.daoPackage.userDAO;
import liuxiaoran.tools.sendCode;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
			resultData="<span class='messageError'>用户名已存在！</span>";
		}
		else{
			resultData="<span class='messageRight'>用户名可以使用</span>";
		}
		response.getWriter().print(resultData);//这步将resultData放入data里
	}
		else {
		response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
	}
		}
		else if(method.equals("email")) {
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			System.out.println(email);
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
/*		c3p0Pool pool=new c3p0Pool();
		Connection conn=pool.getConnection();*/
		userDAOimp dao=new userDAOimp(ConnectionContext.getInstance().get());
		authorityDAO auth_dao=new authorityDAOimp(ConnectionContext.getInstance().get());
		RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
//		String sql="select username from user where username=?";
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	String passwordRep=request.getParameter("passwordRep");
	String email=request.getParameter("email");
	Boolean b=null;
		b=dao.checkUser(username);
if(password.equals("")) {
	request.setAttribute("index", 0); 
	rd.forward(request, response);
}
	else if(!password.equals(passwordRep)) {
		request.setAttribute("index", 1);
		rd.forward(request, response);
	}else if(password.equals(passwordRep) && b==true) {
		dao.addUser(username, password,email);
        Integer user_id=dao.getUser(username).getId();
        String dataBasePath=request.getServletContext().getInitParameter("localhost")+"imgs/noman.png";
        dao.updatePicture(dataBasePath, user_id);
		auth_dao.addAuthority(user_id);
//		out.println("注册成功！");这句话不会出现
		response.sendRedirect("login.jsp");
	}else if(password.equals(passwordRep) && b==false){
		request.setAttribute("index", 2); 
		rd.forward(request, response);
	}
	}

}
