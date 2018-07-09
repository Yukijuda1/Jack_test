package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoPackage.bookDAO;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/search")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String typeStr=request.getParameter("type");//但实际上传过来的是id
		String authorStr=request.getParameter("author");//也是id
		if(typeStr!=null) {
			Integer type=Integer.valueOf(typeStr);
			bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
			String relType=dao.getBook(type).getType();
			List<book> bookList=dao.getBooksByTypes(relType.split("/"));
			request.setAttribute("search", relType);
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("searchInfo.jsp").forward(request, response);
		}
		else if(authorStr!=null) {
			Integer author=Integer.valueOf(authorStr);
			bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
			String relAuthor=dao.getBook(author).getAuthor();//relAuthor取出来很可能是xxx,xxx形式的
			List<book> bookList=dao.getBooksByAuthors(relAuthor.split("/"));
			request.setAttribute("search", relAuthor);
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("searchInfo.jsp").forward(request, response);
		}
		else {
		response.sendRedirect("error404.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search=request.getParameter("search");
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		List<book> bookList=dao.queryBookInfo(search);
		System.out.println(bookList);
		if(bookList!=null) {
		request.setAttribute("bookList", bookList);
			}
			else {
				request.setAttribute("message", 0);
			}
		request.setAttribute("search",search);
		request.getRequestDispatcher("searchInfo.jsp").forward(request, response);
	}

}
