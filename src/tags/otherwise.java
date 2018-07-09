package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class otherwise extends SimpleTagSupport {
@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	JspTag parent=getParent();
	choose c=(choose) parent;
	boolean test=c.getTest();
	if(test==true) {
		getJspBody().invoke(null);
	}
	
}
}
