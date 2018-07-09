package liuxiaoran.servletPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.authorityDAOimp;
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.authorityDAO;
import liuxiaoran.daoPackage.userDAO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		String code=request.getParameter("code");
		String CHECK_CODE_KEY=(String) request.getSession().getAttribute("CHECK_CODE_KEY");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
	    
	    if(username.equals("")){
	    	request.setAttribute("info", 0);
	    	rd.forward(request, response);
	    }
	    else if(!username.equals("") && password.equals("")) {
	    	request.setAttribute("info", 1);
	    	rd.forward(request, response);
	    }
	    else if(!CHECK_CODE_KEY.equals(code)) {
	    	request.setAttribute("info", 3);
	    	rd.forward(request, response);
	    }
	    else if(!username.equals("") && !password.equals("") && CHECK_CODE_KEY.equals(code)) {
                 /*c3p0Pool pool=new c3p0Pool();*/
			
			userDAO dao=new userDAOimp(ConnectionContext.getInstance().get());
	    	user u=dao.getUser(username, password);
	    	  if(u!=null) {
	  	    	HttpSession session=request.getSession();
	  	    	session.setAttribute("user", u);
	  	    	authorityDAO auth_dao=new authorityDAOimp(ConnectionContext.getInstance().get());
	  	    	String auth_name=auth_dao.getAuth_name(u.getId());
	  	    	session.setAttribute("auth_name", auth_name);//权限信息
	  	    	response.sendRedirect("index.jsp");
	  	    }
	    	  
	    	  else {
	    		  request.setAttribute("info", 2);
	  	    	rd.forward(request, response);
	    	  }

	    	
	    }
	}

}
