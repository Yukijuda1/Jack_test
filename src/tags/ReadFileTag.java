package tags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ReadFileTag extends SimpleTagSupport {
private String src;
public void setSrc(String src) {
	this.src = src;
}
@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		PageContext pageContext=(PageContext) getJspContext();
		JspWriter out=pageContext.getOut();
		InputStream is=pageContext.getServletContext().getResourceAsStream(src);
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		String str=null;
		while((str=reader.readLine())!=null) {
			out.write(str);
			out.write("<br>");
		}
		is.close();
	}
}
