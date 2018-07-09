package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.contentDAO;
import liuxiaoran.uploadUtils.fileUpload;

/**
 * Servlet implementation class alertContentServlet
 */
@WebServlet("/alertContent")
public class alertContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alertContentServlet() {
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
		   Integer book_id=0;
		   Integer no=0;
		   String contenturl=null;
		   for(FileItem item:items) {
			   if(item.isFormField()) {
				   if(item.getFieldName().equals("id")) {
				   String book_idStr=item.getString("UTF-8");
				   book_id=Integer.valueOf(book_idStr);
				   }
				   else if(item.getFieldName().equals("no")) {
					   String noStr=item.getString("UTF-8");
					   no=Integer.valueOf(noStr);
				   }
			   }
			   else {
				   String FileName=item.getName();
			   int index=FileName.lastIndexOf(".");
    	        String extensionName=FileName.substring(index);
	    	        String randomName=System.currentTimeMillis()+new Random().nextInt(100)+extensionName;
	    	        contenturl=request.getServletContext().getRealPath("/contents/")+"\\"+randomName;	
                	fileUpload.upload(contenturl, item.getInputStream());
			   }
		   }
	   contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
	   Timestamp createtime=new Timestamp(new Date().getTime());
	   dao.alert(book_id, no, contenturl, createtime);
	   }
		user u=new user("l", "l");
		ObjectMapper mapper=new ObjectMapper();
   	String jsonStr=mapper.writeValueAsString(u);
   	response.getWriter().print(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpSerletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
