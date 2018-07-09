package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.message;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.authorityDAOimp;
import liuxiaoran.daoImpPackage.messageDAOimp;
import liuxiaoran.daoPackage.authorityDAO;
import liuxiaoran.daoPackage.messageDAO;

/**
 * Servlet implementation class userManaServlet
 */
@WebServlet("/userMana")
public class userManaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userManaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		user u=(user) request.getSession().getAttribute("user");
		Integer receive_id=u.getId();
		messageDAO dao=new messageDAOimp(ConnectionContext.getInstance().get());
		List<message> readMessages=dao.getReadMessages(receive_id);
		List<message> notReadMessages=dao.getNotReadMessages(receive_id);
		request.setAttribute("readMessages",readMessages);
		request.setAttribute("notReadMessages", notReadMessages);
		request.getRequestDispatcher("manager/userMana.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		if(method.equals("yes")) {
		String idStr=request.getParameter("id");
		Integer id=Integer.valueOf(idStr);
		authorityDAO dao=new authorityDAOimp(ConnectionContext.getInstance().get());
		messageDAO message_dao=new messageDAOimp(ConnectionContext.getInstance().get());
		Integer send_id=message_dao.getMessage(id).getSend_id();
		Timestamp createtime=new Timestamp(new Date().getTime());
		message_dao.agree(id,createtime);
		dao.becomeManager(send_id);
		}
		else if(method.equals("no")){
			String idStr=request.getParameter("id");
			Integer id=Integer.valueOf(idStr);
			messageDAO message_dao=new messageDAOimp(ConnectionContext.getInstance().get());
			Timestamp createtime=new Timestamp(new Date().getTime());
			message_dao.disagree(id,createtime);
		}
		else if(method.equals("delete")) {
			String idStr=request.getParameter("id");
			Integer id=Integer.valueOf(idStr);
			messageDAO message_dao=new messageDAOimp(ConnectionContext.getInstance().get());
			message_dao.deleteMessage(id);
		}
	}

}
