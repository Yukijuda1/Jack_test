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
			//�Ѽ�����Ķ���ŵ�pageContext��
			getJspBody().invoke(null);
			//����ǩ�����������ҳ����
		}
	}
	else
		getJspContext().getOut().print("û�ж���Ŷ~");
	}
}
