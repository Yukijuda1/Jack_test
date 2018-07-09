package tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class toUpper extends SimpleTagSupport {
	private String time;
	public void setTime(String time) {
		this.time = time;
	}
@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
   PageContext pageContext=(PageContext) getJspContext();
   JspWriter out=pageContext.getOut();
   StringWriter sw=new StringWriter();
   JspFragment bodyContent=getJspBody();
   bodyContent.invoke(sw);
   System.out.println(sw);
   if(sw!=null) {
   String strUpper=sw.toString().toUpperCase();
   Integer time=Integer.valueOf(this.time);
   for(int i=0;i<time;i++) {
	   out.println(strUpper);
   }
   }
}
}
