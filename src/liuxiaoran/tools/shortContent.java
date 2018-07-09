package liuxiaoran.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class shortContent {
	public static String getShortContent(String filePath) {
		File file=new File(filePath);
		 BufferedReader reader = null;
		  String ans = "";
		  try {
			  reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			  String tempString=null;
			  //一行一行读取文件内容
			  for(int i=0;i<5;i++) {
				  if(reader.readLine()!=null) {
				  tempString=reader.readLine();
				  ans+=tempString+"\n";//只读5行内容
		}
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
