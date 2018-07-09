package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.contentDAO;
import liuxiaoran.uploadUtils.fileUpload;

/**
 * Servlet implementation class insertServlet
 */
@WebServlet("/insert")
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String bookName=request.getParameter("bookName");
		String no=request.getParameter("no");
		String resultData=null;
	/*	boolean notExist=true;*/
	/*	c3p0Pool pool=new c3p0Pool();
		Connection conn=pool.getConnection();*/
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		if(bookName!=null) {
		book b=dao.getBook(bookName);
		if(b!=null) {
//			notExist=false;
			resultData="<span class='messageError'>该书信息已存在！</span>";
		}
		else {
           resultData="<span class='messageRight'>√</span>";
		}
		response.getWriter().print(resultData);//这步将resultData放入data里
		}
		else if(no!=null) {
			book b=dao.getBookbyNo(no);
			if(b!=null) {
				/*notExist=false;*/
				resultData="<span class='messageError'>该编号已存在！</span>";
			}
			else {
				 resultData="<span class='messageRight'>√</span>";
			}
			response.getWriter().print(resultData);//这步将resultData放入data里
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
		//注意传过来的表单是二进制编码！
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		DiskFileItemFactory factory =new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(5*1024 * 1024);//设置最大文件大小
	   List<FileItem> items=null;
	   try {
		items=upload.parseRequest(request);//从request过来的数据中获取所有信息
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   if(items!=null) {
		   String bookName=items.get(0).getString("UTF-8").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");//看书名和编号是否存在
		   String no=items.get(1).getString("UTF-8").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		    if(dao.getBook(bookName)!=null || dao.getBookbyNo(no)!=null) {
		    	request.setAttribute("message", 0);//错误信息0表示已存在
		    }
		    else {
		 	   List<String> bookInfos=new ArrayList<>();
		 	   List<String> contentInfos=new ArrayList<>();
		    	for(FileItem item:items){
		    		if(item.isFormField()) {
		    			if(!item.getFieldName().equals("contentNo")) 
		    			{
		    		String bookValue=item.getString("UTF-8").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "");
		    		bookInfos.add(bookValue);
		    			}
		    			else {
		    				String contentValue=item.getString("UTF-8");
		    				contentInfos.add(contentValue);
		    			}
		    		}
		    		else {
		    			String FileName=item.getName();//获取文件名称.扩展名
		    	        int index=FileName.lastIndexOf(".");
		    	        String extensionName=FileName.substring(index);
		                if(extensionName.equals(".txt")) {
		                	String randomName=System.currentTimeMillis()+new Random().nextInt(100)+extensionName;
		                	String filePath=request.getServletContext().getRealPath("/contents/")+"\\"+randomName;	
		                	fileUpload.upload(filePath, item.getInputStream());
		                	contentInfos.add(filePath);
		                }
		                else{
		                	String randomName=System.currentTimeMillis()+new Random().nextInt(100)+extensionName;
		    			String filePath=request.getServletContext().getRealPath("/imgs/")+"\\"+randomName;
		    			fileUpload.upload(filePath, item.getInputStream());//存储路径没问题
		    			String dataBasePath=request.getServletContext().getInitParameter("localhost")+"imgs/"+randomName;
		    			//入库路径要改
		    			bookInfos.add(dataBasePath);
		                }
		    		}
		    	}
		    	Object[] bookInfosStr=bookInfos.toArray();
		    	dao.addBook(bookInfosStr);
		    	Integer book_id=dao.getBook(bookName).getId();//获取刚刚入库的书籍id
		    	contentDAO content_dao=new contentDAOimp(ConnectionContext.getInstance().get());
		    	for(int i=1;i<=(contentInfos.size()/2);i++) {
		    		String contenturl=contentInfos.get(2*i-2);
		    		String contentNoStr=contentInfos.get(2*i-1);
		    		Integer contentNo=Integer.valueOf(contentNoStr);
		    		Timestamp createtime=new Timestamp(new Date().getTime());
		    		content_dao.addContent(book_id, contentNo, contenturl, createtime);
		    	}
		    	request.setAttribute("message", 1);//正确信息1表示添加成功
		    }

	   }
	   else {
		   response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
	   }
	   
	   request.getRequestDispatcher("/manager/insert.jsp").forward(request, response);
}
}
