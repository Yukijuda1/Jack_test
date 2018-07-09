package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.authority;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.authorityDAOimp;
import liuxiaoran.daoImpPackage.messageDAOimp;
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.authorityDAO;
import liuxiaoran.daoPackage.messageDAO;
import liuxiaoran.daoPackage.userDAO;

/**
 * Servlet implementation class authorityServlet
 */
@WebServlet("/authority")
public class authorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authorityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	authorityDAO dao=new authorityDAOimp(ConnectionContext.getInstance().get());
	userDAO user_dao=new userDAOimp(ConnectionContext.getInstance().get());
	List<authority> managers=dao.getManagers();
	List<user> users=new ArrayList<>();
	for(authority manager:managers) {
		Integer user_id=manager.getUser_id();
		user u=user_dao.getUser(user_id);
		users.add(u);
	}
	if(users.size()>0) {
		request.setAttribute("managers", users);
		request.getRequestDispatcher("authority.jsp").forward(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_idStr=request.getParameter("manager");
		String message=request.getParameter("message");
		String phone=request.getParameter("phone");
		user u=(user)request.getSession().getAttribute("user");
		Integer send_id=u.getId();
		Integer receive_id=Integer.valueOf(user_idStr);
		String content=message+"<br />"+"联系电话:"+phone;
		Timestamp createtime=new Timestamp(new Date().getTime());
		messageDAO dao=new messageDAOimp(ConnectionContext.getInstance().get());
		dao.addMessage(receive_id, send_id, content,createtime);
//		List<message> messages=dao.getMessages(send_id);
//	request.getSession().setAttribute("messageList", messages);
		response.sendRedirect(request.getServletContext().getContextPath()+"/successSend.jsp");
	}

}
