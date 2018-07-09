package liuxiaoran.filterPackage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginCheckFilter extends HttpFilter{
private String redirectURL;//δ��¼ʱ��תҳ�棬������web.xml��
private String needLogin;//��Ҫ��֤��¼��ҳ�棬������web.xml��
@Override
	protected void init() {
		// TODO Auto-generated method stub
	this.redirectURL=getFilterConfig().getServletContext().getInitParameter("redirectUrl");
	
	this.needLogin=getFilterConfig().getServletContext().getInitParameter("needLogin");
	}
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//��ȡ����request��url����
//		String requestUrl=request.getRequestURL().toString();//  http://localhost/example1/needLogin/test.jsp
//		String requestUri=request.getRequestURI();//  /example1/needLogin/test.jsp
		String servletPath=request.getServletPath();//   /needLogin/test.jsp
//		System.out.println(servletPath);
//		String needLogin=request.getServletContext().getInitParameter("needLogin");
//		System.out.println(needLogin);
		List<String> urls=Arrays.asList(needLogin.split(","));// Arrays.adList��Object������list��String.split�Ǹ��ݲ����ַ���ɶ���ַ���
		  boolean b=false;
		for(String url:urls) {
		  if(url.equals(servletPath)) {
		  b=true; 
		  break;
		  }
	  }
		if(b) {
			  if(request.getSession().getAttribute("user")!=null) {
					chain.doFilter(request, response);
				}
				else
					response.sendRedirect(request.getServletContext().getContextPath()+this.redirectURL);
		  }
		  else
		  chain.doFilter(request, response);
	}
	//���������صķ�Χ
//����Ҫ��¼��ҳ��ֵ�һ���ļ�����needLogin��Ȼ����web.xml������/needLogin/*
}
