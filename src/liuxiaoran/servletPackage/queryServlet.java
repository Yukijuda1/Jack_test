package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class queryServlet
 */
@WebServlet("/query")
public class queryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
/*		System.out.println("i'm in");*/
String search=request.getParameter("search");
/*response.setContentType("text/javascript");
response.setCharacterEncoding("UTF-8");*/
bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
List<book> bookList=dao.queryBookInfo(search);
/*System.out.println(bookList);*/
if(bookList!=null) {
/*	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(bookList);
	response.getWriter().print(json);*/

}
else {
	
}
/*request.getRequestDispatcher("searchInfo.jsp").forward(request, response);*/
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd=request.getRequestDispatcher("query.jsp");
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		List<book> bookList=null;
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		String author=request.getParameter("author");
         bookList= dao.queryBookInfo(name, type, author);
		if(name.equals("") && type.equals("") && author.equals("")) {
			request.setAttribute("index",0);
			rd.forward(request, response);
		}
		else if(bookList.size()==0) {
			request.setAttribute("index", 1);
			rd.forward(request, response);
		}
		else {
   request.setAttribute("bookList", bookList);
   rd.forward(request, response);
		}

	}
}
