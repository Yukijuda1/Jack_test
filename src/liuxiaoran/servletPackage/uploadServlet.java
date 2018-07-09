package liuxiaoran.servletPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 */
@WebServlet("/upload")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public uploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   System.out.println(request.getParameter("file"));
//   无法获得穿过来的file内容，因为他是二进制流，而这个方法只能获得String类型的
   System.out.println(request.getParameter("desc"));
//   且无法获得正常的String类型内容，因为编码方式变为了二进制流
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		//表单传递的是否是以enctype="multipart/form-data
		System.out.println(isMultipart);
		DiskFileItemFactory factory =new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);
		File repository=new File("D:\\temp");
	    factory.setRepository(repository); 
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);
	   List<FileItem> items=null;
	   try {
		items=upload.parseRequest(request);
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   for(FileItem item:items) {
		   if(item.isFormField()) {//判断这个item是否为表单域
			   String name=item.getFieldName();//item名，即传过来的request的ParamName  desc
			   String value=item.getString();//值  
			   System.out.println(name+":"+value);
		   }
		   else{//否则就为文件域
			   String name=item.getFieldName();//文件域名  file
			   String FileName=item.getName();//获取该文件域文件的名字 111.png
			   String Type=item.getContentType();//文件类型  image/png
			   boolean isInMemory=item.isInMemory();//判断文件是否在内存
			   long Size=item.getSize();//获取该文件的大小
			   System.out.println(name);
			   System.out.println(FileName);
			   System.out.println(Type);
			   System.out.println(isInMemory);
			   System.out.println(Size);
			   InputStream is=item.getInputStream();//将这个文件转为输入流
			   OutputStream os=new FileOutputStream(new File("E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\example1\\WEB-INF\\imgs\\"+FileName));
			   byte[] b=new byte[1024];
			   int len;
			   while((len=is.read(b))!=-1) {
					os.write(b, 0, len);
			   }
			   is.close();
			   os.close();//别忘了关闭输入流和输出流
		   }
	   }
	}

}
