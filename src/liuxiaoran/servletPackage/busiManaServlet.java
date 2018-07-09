package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.JavaBean.borrowinfo;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.borrowinfoDAOimp;
import liuxiaoran.daoImpPackage.returninfoDAOimp;
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.borrowinfoDAO;
import liuxiaoran.daoPackage.returninfoDAO;
import liuxiaoran.daoPackage.userDAO;

/**
 * Servlet implementation class busiManaServlet
 */
@WebServlet("/busiMana")
public class busiManaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busiManaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		if(method!=null) {
		if(method.equals("out")) {
			String bi_idStr=request.getParameter("bi_id");
			Integer bi_id=Integer.valueOf(bi_idStr);
			borrowinfoDAO dao=new borrowinfoDAOimp(ConnectionContext.getInstance().get());
			bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
			borrowinfo bi=dao.getBorrowinfo(bi_id);
			book_dao.borrow(bi.getBook_id());//数量-1
			Timestamp finishtime=new Timestamp(new Date().getTime());
			dao.out(bi_id, finishtime);//完善借阅信息，即添加领书的时间
		}
		else if(method.equals("in")) {
			String bi_idStr=request.getParameter("bi_id");
			Integer bi_id=Integer.valueOf(bi_idStr);
			borrowinfoDAO dao=new borrowinfoDAOimp(ConnectionContext.getInstance().get());
			bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
			returninfoDAO returninfo_dao=new returninfoDAOimp(ConnectionContext.getInstance().get());
			borrowinfo bi=dao.getBorrowinfo(bi_id);
			book_dao.back(bi.getBook_id());//数量+1
			Integer book_id=bi.getBook_id();
			Integer user_id=bi.getUser_id();
			Timestamp createtime=new Timestamp(new Date().getTime());
			dao.delete(bi_id);//删除用户的借阅信息
			returninfo_dao.in(book_id, user_id, createtime);//添加归还时间
		}
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/manager/busiMana.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code=request.getParameter("code");
		if(code!=null) {
		borrowinfoDAO dao=new borrowinfoDAOimp(ConnectionContext.getInstance().get());
		bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
		userDAO user_dao=new userDAOimp(ConnectionContext.getInstance().get());
		borrowinfo bi=dao.getBorrowInfo(code);
		if(bi!=null) {
		Integer book_id=bi.getBook_id();
		Integer user_id=bi.getUser_id();
		book b=book_dao.getBook(book_id);
		user u=user_dao.getUser(user_id);
		request.setAttribute("user", u);
		request.setAttribute("book", b);
		request.setAttribute("borrowinfo", bi);
		request.getRequestDispatcher("manager/busiMana.jsp").forward(request, response);
		}
		else {
			request.setAttribute("message", 0);
			request.getRequestDispatcher("manager/busiMana.jsp").forward(request, response);
		}
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/manager/busiMana.jsp");
		}
	}

}
