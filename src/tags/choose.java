package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class choose extends SimpleTagSupport {
	private boolean test=true;
public boolean getTest() {
	return this.test;
}
public void setTest(boolean test) {
	this.test=test;
}

@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	getJspBody().invoke(null);
}
}
