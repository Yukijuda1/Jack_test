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
	//�ø���ǩ�ڵ��Ա�ǩ�������������������ӡ��򲻻�����Ա�ǩ�ı�ǩ��
	
	//����ǩ�ı�ǩ��д��һ��������
	//����ǩ���ܻ���ӱ�ǩ�Ķ���
}
}
