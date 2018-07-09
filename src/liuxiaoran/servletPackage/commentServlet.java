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
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.commentDAOimp;
import liuxiaoran.daoPackage.commentDAO;

/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/comment")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idStr=request.getParameter("id");
		Integer id=Integer.valueOf(idStr);
		commentDAO dao=new commentDAOimp(ConnectionContext.getInstance().get());
		dao.deleteComment(id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String book_idStr=request.getParameter("book_id");
		Integer book_id=Integer.valueOf(book_idStr);
		String textfield=request.getParameter("textfield");
		user u=(user) request.getSession().getAttribute("user");
		if(u!=null) {
			Integer user_id=u.getId();
			Timestamp createtime=new Timestamp(new Date().getTime());
			String content=textfield.replaceAll(" ", "");
			commentDAO dao=new commentDAOimp(ConnectionContext.getInstance().get());
			dao.addComment(user_id, book_id,content, createtime);
			response.sendRedirect(request.getServletContext().getContextPath()+"/bookInfo?id="+book_idStr);
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
		}
        
		
		
	}

}
