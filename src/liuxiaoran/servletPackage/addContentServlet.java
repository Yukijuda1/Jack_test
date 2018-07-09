package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
import liuxiaoran.JavaBean.content;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.contentDAOimp;
import liuxiaoran.daoPackage.contentDAO;
import liuxiaoran.uploadUtils.fileUpload;

/**
 * Servlet implementation class addContentServlet
 */
@WebServlet("/addContent")
public class addContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idStr=request.getParameter("id");
		if(idStr!=null && !idStr.equals("")) {
		Integer id=Integer.valueOf(idStr);
		contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
		List<content> contents=dao.getContents(id);
		Collections.sort(contents);//按编号排序
		Integer maxNo=contents.get(contents.size()-1).getNo();//最后一个编号，注意书至少要有一卷内容
		List<Integer> lackNos=new ArrayList<>();
		for(int i=1;i<=maxNo;i++) {
			boolean isExist=false;
			for(int j=0;j<contents.size();j++) {
				if(i==contents.get(j).getNo()) {
					isExist=true;
				}
			}
			if(isExist==false) {//
				lackNos.add(i);
			}
		}
		if(lackNos.size()!=0) {
		request.setAttribute("lackNos", lackNos);
		}
		request.setAttribute("contents", contents);
		request.getRequestDispatcher("manager/addContent.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("manager/manaQuery.jsp");
		}
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
		items=upload.parseRequest(request);
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   String book_idStr=null;
	   for(FileItem item:items) {
		   if(item.isFormField()) {
			   book_idStr=item.getString();//param.id
		   }
	   }
	   for(FileItem item:items) {
		   if(!item.isFormField()) {
			   if(book_idStr!=null) {
				String randomName=System.currentTimeMillis()+new Random().nextInt(100)+".txt";
            	String filePath=request.getServletContext().getRealPath("/contents/")+"\\"+randomName;	
            	fileUpload.upload(filePath, item.getInputStream());
            	Timestamp createtime=new Timestamp(new Date().getTime());
			   Integer book_id=Integer.valueOf(book_idStr);
					 contentDAO dao=new contentDAOimp(ConnectionContext.getInstance().get());
						 List<content> contents= dao.getContents(book_id);
						 Collections.sort(contents);
						 Integer nextNo=contents.get(contents.size()-1).getNo()+1;
						 dao.addContent(book_id, nextNo, filePath, createtime);
						 user u=new user("l", "l");
							ObjectMapper mapper=new ObjectMapper();
		                	String jsonStr=mapper.writeValueAsString(u);
		                	response.getWriter().print(jsonStr);
			   }
		   }
	   }
	}

}
