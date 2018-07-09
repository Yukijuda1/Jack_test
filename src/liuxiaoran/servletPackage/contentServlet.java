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
import liuxiaoran.tools.getContent;

/**
 * Servlet implementation class contentServlet
 */
@WebServlet("/content")
public class contentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idStr=request.getParameter("id");
		String noStr=request.getParameter("no");
		Integer book_id=Integer.valueOf(idStr);
		Integer no=Integer.valueOf(noStr);
		bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
		contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
		book bookInfo=book_dao.getBook(book_id);
		List<content> contentList=dao.getContents(book_id);//这本书的所有分卷
		Collections.sort(contentList);
		Integer contentSize=contentList.size();//分卷总数
		content nowContent=dao.getContent(book_id, no);//当前分卷信息
		Integer nowNo=nowContent.getNo();
		String filePath=nowContent.getContenturl();
		String mainContent=getContent.getTXT(filePath);//主要内容（数据库存的仅是路径）
		request.setAttribute("contentList", contentList);
		request.setAttribute("contentSize", contentSize);
		request.setAttribute("bookInfo",bookInfo);
		request.setAttribute("nowNo", nowNo);
		request.setAttribute("mainContent", mainContent);
		request.getRequestDispatcher("content.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
