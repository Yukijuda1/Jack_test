package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class when extends SimpleTagSupport {
	private boolean test;
	public void setTest(boolean test) {
		this.test = test;
	}
@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	System.out.println(test);
	JspTag parent=getParent();
	choose c=(choose) parent;
	boolean b=c.getTest();
	if(test==true && b==true) {
		getJspBody().invoke(null);
		c.setTest(false);
	}
}
}
