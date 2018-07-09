package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class javatest {
@SuppressWarnings("finally")
public int test0(int i) {
	try {
		return 10/i;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return i++;
	}finally{
		return ++i;
	}
}
public int test(String a,String b) {
	if(a.length()<b.length()) 
		return 0;
	else {
		int count=0;
		char[] chars=a.toCharArray();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<a.length();i++) {
			sb.append(chars[i]);
			if(sb.toString().contains(b)) {
				count++;
				sb.delete(0, i);
			}
		}
		return count;
	}
}
@Test
public void test1() {
	System.out.println(test0(0));
}
@Test
public void test2() throws IOException {
   InputStream is=this.getClass().getResourceAsStream("111.txt");
   OutputStream os=new FileOutputStream("C:\\Users\\95\\Desktop\\new.txt");
//   //输入流转成字符流
//   Reader reader=new InputStreamReader(is);
//   BufferedReader br=new BufferedReader(reader);
//  StringBuffer sb=new StringBuffer();
//String str;
//while((str=br.readLine())!=null) {
//	sb.append(str);
//}
   byte[] b=new byte[1024];
   int len=0;
   while((len=is.read(b))!=-1) {
	   System.out.println(len);
	   os.write(b, 0, len);
   }
   StringBuffer sb=new StringBuffer();
}
@Test
public void test3() {
	System.out.println(test("ssjackccjacjjackss","jack"));
}
}
