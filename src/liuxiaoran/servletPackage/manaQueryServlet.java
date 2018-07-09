package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.JavaBean.content;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.contentDAO;

/**
 * Servlet implementation class manaQueryServlet
 */
@WebServlet("/manaQuery")
public class manaQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manaQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String idStr=request.getParameter("id");
		if(idStr!=null && !idStr.equals("")) {
		Integer id=Integer.valueOf(idStr);
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		contentDAO content_dao=new contentDAOimp(ConnectionContext.getInstance().get());
		List<content> contentList=content_dao.getContents(id);//获取书籍所有分卷
		Collections.sort(contentList);
		book b=dao.getBook(id);//获取该书对象
		request.setAttribute("bookInfo", b);
	request.setAttribute("contentList", contentList);
		request.getRequestDispatcher("manager/alert.jsp").forward(request, response);; 
		}
		else {
			response.sendRedirect("manager/manaQuery.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String search=request.getParameter("search");
		List<book> bookList=null;
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		bookList=dao.queryBookInfo(search);
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("manager/manaQuery.jsp").forward(request, response);
	}
	

}
