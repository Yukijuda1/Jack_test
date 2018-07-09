package liuxiaoran.servletPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.contentDAO;

/**
 * Servlet implementation class deleteContentServlet
 */
@WebServlet("/deleteContent")
public class deleteContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ajax
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String book_idStr=request.getParameter("book_id");
		String noStr=request.getParameter("no");
		System.out.println(book_idStr);
		System.out.println(noStr);
		if(book_idStr!=null && noStr!=null) {
		Integer book_id=Integer.valueOf(book_idStr);
		Integer no=Integer.valueOf(noStr);
		contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
		dao.delete(book_id, no);
		response.getWriter().print("yes");
		}
		else {
			response.getWriter().print("no");
		}
	}


}
