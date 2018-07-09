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
 * Servlet implementation class platContentServlet
 */
@WebServlet("/platContent")
public class platContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public platContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	   String noStr=null;
	   String book_idStr=null;
		for(FileItem item:items){
			if(item.isFormField()) {
				if(item.getFieldName().equals("no")) {
					noStr=item.getString();
				}
				else if(item.getFieldName().equals("id")) {
					book_idStr=item.getString();
				}
			}
		}
		  for(FileItem item:items) {
			   if(!item.isFormField()) {
				   if(noStr!=null && book_idStr!=null) {
					String randomName=System.currentTimeMillis()+new Random().nextInt(100)+".txt";
	            	String filePath=request.getServletContext().getRealPath("/contents/")+"\\"+randomName;	
	            	fileUpload.upload(filePath, item.getInputStream());
	            	Timestamp createtime=new Timestamp(new Date().getTime());
				   Integer book_id=Integer.valueOf(book_idStr);
				   Integer no=Integer.valueOf(noStr);
						 contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
						dao.addContent(book_id, no, filePath, createtime);
						user u=new user("l", "l");
						ObjectMapper mapper=new ObjectMapper();
	                	String jsonStr=mapper.writeValueAsString(u);
	                	response.getWriter().print(jsonStr);
				   }
			   }
		   }
	}

}
