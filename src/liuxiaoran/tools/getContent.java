package liuxiaoran.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class getContent {
public static String getTXT(String filePath) {
	File file=new File(filePath);
	 BufferedReader reader = null;
	  String ans = "";
	  try {
		  reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
		  String tempString=null;
		  //һ��һ�ж�ȡ�ļ�����
		  while((tempString = reader.readLine()) != null){
			  ans+=tempString+"\n";
		  }
	  }catch (IOException e) {
		// TODO: handle exception
		  e.printStackTrace();
	}finally {
		 if(reader != null)
			            {
			                 try{
			                      reader.close();
			                  }catch(IOException e1){
			                      e1.printStackTrace();
			                }
			       }
	}
	  if(!ans.equals("")) {
	return ans;}
	  else
		  return null;
}
}
