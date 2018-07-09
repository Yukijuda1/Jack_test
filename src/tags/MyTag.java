package tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag extends SimpleTagSupport {
	private String value;
	private String count;
public void setValue(String value) {
	this.value=value;
}
public void setCount(String count) {
	this.count=count;
}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
//		PageContext pageContext=(PageContext) getJspContext();//SimpleTagSupport是SimpleTag的实现类
		//继承它后只需要重写doTag既可，PageContext对象可由内部函数getJspContext()获得
//		System.out.println("doTag");
//		编写实际标签体逻辑
//		JspWriter out=pageContext.getOut();
//		System.out.println(value+"&"+count);
//pageContext可以获取jsp页面其他8个隐含对象
//		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
//		String name=request.getParameter("name");
//		out.print(name);
		
		JspFragment bodyContent=getJspBody();
		//标签体内容通过setJspBody方法传入
		StringWriter sw=new StringWriter();
		System.out.println(sw);
		bodyContent.invoke(sw);//把标签体内容从Writter中输出
		//若bodyContent.invoke(null)则直接打印在页面中
		System.out.println(sw);
		String content=sw.toString().toUpperCase();
		getJspContext().getOut().println(content);
	}
}
