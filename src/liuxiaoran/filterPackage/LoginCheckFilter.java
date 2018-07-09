package liuxiaoran.filterPackage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginCheckFilter extends HttpFilter{
private String redirectURL;//未登录时跳转页面，配置在web.xml中
private String needLogin;//需要验证登录的页面，配置在web.xml中
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
		//获取请求request的url！！
//		String requestUrl=request.getRequestURL().toString();//  http://localhost/example1/needLogin/test.jsp
//		String requestUri=request.getRequestURI();//  /example1/needLogin/test.jsp
		String servletPath=request.getServletPath();//   /needLogin/test.jsp
//		System.out.println(servletPath);
//		String needLogin=request.getServletContext().getInitParameter("needLogin");
//		System.out.println(needLogin);
		List<String> urls=Arrays.asList(needLogin.split(","));// Arrays.adList将Object数组变成list。String.split是根据参数字符拆成多个字符串
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
	//问题是拦截的范围
//给需要登录的页面分到一个文件夹中needLogin。然后在web.xml中配置/needLogin/*
}
