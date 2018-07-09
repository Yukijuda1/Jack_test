package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class sonTag extends SimpleTagSupport {
@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	System.out.println("son");
	JspTag parent=getParent();//重要！通过getParent()方法获得父标签对象，然后强转即可
	//这个方法需要继承SimpleTagSupport，或者实现SimpleTag的getParent()方法
    parentTag pt=(tags.parentTag) parent;
    String name=pt.getName();
    getJspContext().getOut().print("name:"+name);
}
}
