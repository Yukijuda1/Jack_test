package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class parentTag extends SimpleTagSupport {
	private String name="liuxiaoran";
	public String getName() {
		return name;
	}
@Override
public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	System.out.println("dad");
	getJspBody().invoke(null);
	//让父标签内的自标签运作，如果这个方法不加。则不会调用自标签的标签类
	
	//父标签的标签类写了一定会运作
	//父标签不能获得子标签的对象
}
}
