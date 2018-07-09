package tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class forEachMap extends SimpleTagSupport{
private Map<?,?> items;
private String var;
public void setItems(Map<?, ?> items) {
	this.items = items;
}
public void setVar(String var) {
	this.var = var;
}
@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		for(Map.Entry<?,?> entry:this.items.entrySet()) {
			getJspContext().setAttribute(var, entry);
			getJspBody().invoke(null);
		}
	}
}
