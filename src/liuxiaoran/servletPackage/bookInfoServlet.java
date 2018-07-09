package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.JavaBean.comment;
import liuxiaoran.JavaBean.content;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.commentDAOimp;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.commentDAO;
import liuxiaoran.daoPackage.contentDAO;

/**
 * Servlet implementation class bookInfoServlet
 */
@WebServlet("/bookInfo")
public class bookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strid=request.getParameter("id");
		if(strid!=null) {
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		Integer id=Integer.valueOf(strid);
		book b=dao.getBook(id);
		if(b!=null) {
		Cookie nowCookie=new Cookie("bookid_"+strid, strid);
		response.addCookie(nowCookie);//添加cookie	
		//找出书籍的contentList
		contentDAO content_dao=new contentDAOimp(ConnectionContext.getInstance().get());
		commentDAO comment_dao=new commentDAOimp(ConnectionContext.getInstance().get());
		List<content> contentList=content_dao.getContents(b.getId());
		List<comment> commentList=comment_dao.getCommentsByBook_id(id);
		Integer commentSize=0;
		if(commentList!=null) {
		commentSize=commentList.size();
		}
		Collections.sort(contentList);//按照content中no排序
//       String sc=shortContent.getShortContent(b.getContenturl());
    		   //找相似书籍List
		String sameType=b.getType();
		List<book> sameBooks=dao.getBooksByTypes(sameType.split("/"));//imp中已改为范围查询
		for(int i=0;i<sameBooks.size();i++) {
			book removeBook=sameBooks.get(i);
			if(removeBook.getId() == id) {
				sameBooks.remove(removeBook);
			}
		}
		//相似书籍list中删除该书
		Collections.shuffle(sameBooks);//打乱相似书籍排序
		if(sameBooks.size()>=3) {
		sameBooks=sameBooks.subList(0, 3);
		}//最多三个相似书籍推荐
		request.setAttribute("comments", commentList);
		request.setAttribute("commentSize", commentSize);
		request.setAttribute("sameBooks", sameBooks);//添加相似书籍List
		request.setAttribute("bookInfo", b);//书本信息
		request.setAttribute("contents", contentList);
		request.getRequestDispatcher("bookInfo.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/error404.jsp");
		}
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
