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
//		PageContext pageContext=(PageContext) getJspContext();//SimpleTagSupport��SimpleTag��ʵ����
		//�̳�����ֻ��Ҫ��дdoTag�ȿɣ�PageContext��������ڲ�����getJspContext()���
//		System.out.println("doTag");
//		��дʵ�ʱ�ǩ���߼�
//		JspWriter out=pageContext.getOut();
//		System.out.println(value+"&"+count);
//pageContext���Ի�ȡjspҳ������8����������
//		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
//		String name=request.getParameter("name");
//		out.print(name);
		
		JspFragment bodyContent=getJspBody();
		//��ǩ������ͨ��setJspBody��������
		StringWriter sw=new StringWriter();
		System.out.println(sw);
		bodyContent.invoke(sw);//�ѱ�ǩ�����ݴ�Writter�����
		//��bodyContent.invoke(null)��ֱ�Ӵ�ӡ��ҳ����
		System.out.println(sw);
		String content=sw.toString().toUpperCase();
		getJspContext().getOut().println(content);
	}
}
