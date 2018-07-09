package liuxiaoran.uploadUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class fileUpload {
public static void upload(String filePath,InputStream fileInputStream) throws IOException {
	System.out.println("in");
   OutputStream out= new FileOutputStream(filePath);
   byte[] buffer =new byte[1024];
   int len=0;
   while((len=fileInputStream.read(buffer))!=-1) {
	   out.write(buffer, 0, len);
   }
fileInputStream.close();
out.close();
}
}
