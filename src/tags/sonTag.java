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
	JspTag parent=getParent();//��Ҫ��ͨ��getParent()������ø���ǩ����Ȼ��ǿת����
	//���������Ҫ�̳�SimpleTagSupport������ʵ��SimpleTag��getParent()����
    parentTag pt=(tags.parentTag) parent;
    String name=pt.getName();
    getJspContext().getOut().print("name:"+name);
}
}
