package liuxiaoran.servletPackage;

import java.io.IOException;
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
import liuxiaoran.daoImpPackage.userDAOimp;
import liuxiaoran.daoPackage.userDAO;
import liuxiaoran.uploadUtils.fileUpload;

/**
 * Servlet implementation class headpictureServlet
 */
@WebServlet("/headpicture")
public class headpictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public headpictureServlet() {
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
		upload.setSizeMax(5*1024 * 1024);//��������ļ���С
	   List<FileItem> items=null;
	   try {
			items=upload.parseRequest(request);//��request�����������л�ȡ������Ϣ
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   if(items!=null) {
		   for(FileItem item:items) {
			   if(item.isFormField()) {
			   }
			   else {
				   //��������һ����ͼƬ�ļ�
				   String FileName=item.getName();
				   int index=FileName.lastIndexOf(".");
	    	        String extensionName=FileName.substring(index);
	                	String randomName=System.currentTimeMillis()+new Random().nextInt(100)+extensionName;
	                	String filePath=request.getServletContext().getRealPath("/imgs/")+"\\"+randomName;	//�ļ��洢·��
	                	fileUpload.upload(filePath, item.getInputStream());
	                	String dataBasePath=request.getServletContext().getInitParameter("localhost")+"imgs/"+randomName;//����img src·��
	                	user u=(user) request.getSession().getAttribute("user");
	                	if(u!=null) {
	                		userDAO dao=new userDAOimp(ConnectionContext.getInstance().get());
	                		dao.updatePicture(dataBasePath, u.getId());
	                		u.setPictureurl(dataBasePath);
	                		request.setAttribute("user", u);
	                	}
	                	ObjectMapper mapper=new ObjectMapper();
	                	String jsonStr=mapper.writeValueAsString(u);
	                	response.getWriter().print(jsonStr);

			   }
		   }
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
