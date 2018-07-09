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
//   �޷���ô�������file���ݣ���Ϊ���Ƕ������������������ֻ�ܻ��String���͵�
   System.out.println(request.getParameter("desc"));
//   ���޷����������String�������ݣ���Ϊ���뷽ʽ��Ϊ�˶�������
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		//�����ݵ��Ƿ�����enctype="multipart/form-data
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
		   if(item.isFormField()) {//�ж����item�Ƿ�Ϊ����
			   String name=item.getFieldName();//item��������������request��ParamName  desc
			   String value=item.getString();//ֵ  
			   System.out.println(name+":"+value);
		   }
		   else{//�����Ϊ�ļ���
			   String name=item.getFieldName();//�ļ�����  file
			   String FileName=item.getName();//��ȡ���ļ����ļ������� 111.png
			   String Type=item.getContentType();//�ļ�����  image/png
			   boolean isInMemory=item.isInMemory();//�ж��ļ��Ƿ����ڴ�
			   long Size=item.getSize();//��ȡ���ļ��Ĵ�С
			   System.out.println(name);
			   System.out.println(FileName);
			   System.out.println(Type);
			   System.out.println(isInMemory);
			   System.out.println(Size);
			   InputStream is=item.getInputStream();//������ļ�תΪ������
			   OutputStream os=new FileOutputStream(new File("E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\example1\\WEB-INF\\imgs\\"+FileName));
			   byte[] b=new byte[1024];
			   int len;
			   while((len=is.read(b))!=-1) {
					os.write(b, 0, len);
			   }
			   is.close();
			   os.close();//�����˹ر��������������
		   }
	   }
	}

}
