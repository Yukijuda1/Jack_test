package liuxiaoran.servletPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.contentDAO;

/**
 * Servlet implementation class downloadServlet
 */
@WebServlet("/download")
public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 response.setContentType("application/x-msdownload"); 
//		 response.setHeader("Content-Disposition", "attachment;filename=abc.txt"); 
		String idStr=request.getParameter("id");
		String noStr=request.getParameter("no");
		Integer book_id=Integer.valueOf(idStr);
		Integer no=Integer.valueOf(noStr);
		contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
		String filePath=dao.getContent(book_id, no).getContenturl();
		bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
		String name=book_dao.getBook(book_id).getName()+"第"+noStr+"卷";
		response.setContentType("application/force-download");//应用程序强制下载
		InputStream is=new FileInputStream(new File(filePath));
		////设置响应头，对文件进行url编码
	    name = URLEncoder.encode(name, "UTF-8");
	    response.setHeader("Content-Disposition", "attachment;filename="+name+".txt");   
	    response.setContentLength(is.available());
	    //copy
	    OutputStream out=response.getOutputStream();
	    byte[] b=new byte[1024];
	    int len=0;
	    while((len = is.read(b))!=-1){
	        out.write(b, 0, len);
	      }
	    out.flush();
	    out.close();
	    is.close();
	}

}
