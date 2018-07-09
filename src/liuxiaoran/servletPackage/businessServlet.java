package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.borrowinfo;
import liuxiaoran.JavaBean.comment;
import liuxiaoran.JavaBean.message;
import liuxiaoran.JavaBean.returninfo;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.borrowinfoDAOimp;
import liuxiaoran.daoImpPackage.commentDAOimp;
import liuxiaoran.daoImpPackage.messageDAOimp;
import liuxiaoran.daoImpPackage.returninfoDAOimp;
import liuxiaoran.daoPackage.borrowinfoDAO;
import liuxiaoran.daoPackage.commentDAO;
import liuxiaoran.daoPackage.messageDAO;
import liuxiaoran.daoPackage.returninfoDAO;

/**
 * Servlet implementation class businessServlet
 */
@WebServlet("/business")
public class businessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public businessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		user u=(user) request.getSession().getAttribute("user");
		Integer user_id=u.getId();
		  borrowinfoDAO borrowinfo_dao=new borrowinfoDAOimp(ConnectionContext.getInstance().get());
 		 returninfoDAO returninfo_dao=new returninfoDAOimp(ConnectionContext.getInstance().get());
 		 messageDAO message_dao=new messageDAOimp(ConnectionContext.getInstance().get());
 		 commentDAO comment_dao=new commentDAOimp(ConnectionContext.getInstance().get());
          List<borrowinfo> borrowBookList=borrowinfo_dao.getBorrowBooks(user_id);
          List<returninfo> returnBookList=returninfo_dao.getReturnBooks(user_id);
          List<message> messageList=message_dao.getMessages(user_id);//参数为message表的send_id
          List<comment> comments=comment_dao.getCommentsByUser_id(user_id);
          request.setAttribute("borrowBookList",borrowBookList);
          request.setAttribute("returnBookList", returnBookList);
          request.setAttribute("messageList", messageList);
          request.setAttribute("comments", comments);
          request.getRequestDispatcher("business.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
