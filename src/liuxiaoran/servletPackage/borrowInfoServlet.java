package liuxiaoran.servletPackage;

import java.io.IOException;

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
 * Servlet implementation class borrowInfoServlet
 */
@WebServlet("/borrowInfo")
public class borrowInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//传过来id进行取出操作。原因：URI不能用中文
		String idStr=request.getParameter("id");
		if(idStr!=null) {
		Integer id=Integer.valueOf(idStr);
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		book b= dao.getBook(id);
		if(b!=null) {
		request.setAttribute("bookInfo",b);
		request.getRequestDispatcher("borrow.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("noBookError.jsp");
		}
	}
		else
		{
			response.sendRedirect("index.jsp");
		}
	}

}
