package liuxiaoran.servletPackage;
import java.io.IOException;
import java.util.ArrayList;
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
import liuxiaoran.JavaBean.content;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.contentDAO;
import liuxiaoran.uploadUtils.fileUpload;

/**
 * Servlet implementation class alertServlet
 */
@WebServlet("/alert")
public class alertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alertServlet() {
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
	bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
	String name=request.getParameter("name");
	String nostr=request.getParameter("no");
	String resultData=null;
	if(name!=null) {
		if(dao.checkByName(name)) {
			resultData="true";
		}
		else {
			resultData="false";
		}
		response.getWriter().print(resultData);
	}
	if(nostr!=null) {
		Integer no=Integer.valueOf(nostr);
		if(dao.checkByNo(no)) {
			resultData="true";
		}
		else {
			resultData="false";
		}
		response.getWriter().print(resultData);
	}
	}
//get���ж������ͱ���Ƿ��Ѵ���
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
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
		 	   List<String> bookInfos=new ArrayList<>();
		    	for(FileItem item:items){
		    		if(item.isFormField()) {
		    			if(!item.getFieldName().equals("contentNo")) 
		    			{
		    		String bookValue=item.getString("UTF-8");
		    		bookInfos.add(bookValue);
		    			}
		    		}
		    		else {
		    			String FileName=item.getName();//��ȡ�ļ�����.��չ��
		    			if(!FileName.equals("")) {
		    	        int index=FileName.lastIndexOf(".");
		    	        String extensionName=FileName.substring(index);
		                if(extensionName.equals(".jpg") || extensionName.equals(".png") || extensionName.equals(".jpeg")){
		                	String randomName=System.currentTimeMillis()+new Random().nextInt(100)+extensionName;
		    			String filePath=request.getServletContext().getRealPath("/imgs/")+"\\"+randomName;
		    			fileUpload.upload(filePath, item.getInputStream());//�洢·��û����
		    			String dataBasePath=request.getServletContext().getInitParameter("localhost")+"imgs/"+randomName;
		    			//���·��Ҫ��
		    			bookInfos.add(dataBasePath);//�����鼮��Ϣ������
		                }
		    			}
		    		}
		    	}
//		    	Object[] bookInfosStr=bookInfos.subList(0, 8).toArray();
		    	Object[] bookInfosStr=bookInfos.subList(0, 8).toArray();
		    	dao.alertAll(bookInfosStr);
		    	
	    		String book_idStr=bookInfos.get(7);//��ȡ�������ص�book_id;
		    	Integer book_id=Integer.valueOf(book_idStr);
		    	if(bookInfos.size()>8) {//�ھŸ���url�����Ϊ�ղ��ᱻ����bookInfos
			    	String pictureurl=bookInfos.get(8);
			    	dao.alertPictureurl(book_id, pictureurl);
		    	}
		    	contentDAO content_dao=new contentDAOimp(ConnectionContext.getInstance().get());
		    	List<content> contentList=content_dao.getContents(book_id);//��ȡ�鼮���з־�
				book b=dao.getBook(book_id);//��ȡ�������
				request.setAttribute("bookInfo", b);
			request.setAttribute("contentList", contentList);
		    	request.setAttribute("message", 1);//��ȷ��Ϣ1��ʾ�޸ĳɹ�
		    }
	   else {
		   response.sendRedirect(request.getServletContext().getContextPath()+"manager/manaQuery.jsp");
	   }
	   
	   request.getRequestDispatcher("/manager/alert.jsp").forward(request, response);
}

}
