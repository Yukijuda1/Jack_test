package tags;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class forEach extends SimpleTagSupport {
private Collection<?> items;
private String var;
public void setItems(Collection<?> collection) {
	this.items = collection;
}
public void setVar(String var) {
	this.var = var;
}
@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
	if(items!=null) {
		for(Object obj:this.items) {
			getJspContext().setAttribute(var, obj);
			//把集合里的对象放到pageContext中
			getJspBody().invoke(null);
			//将标签体内容输出到页面上
		}
	}
	else
		getJspContext().getOut().print("没有东西哦~");
	}
}
