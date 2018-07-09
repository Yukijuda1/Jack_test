package liuxiaoran.filterPackage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityFilter extends HttpFilter{
	private String needAuthority;
@Override
protected void init() {
	// TODO Auto-generated method stub
	this.needAuthority=getFilterConfig().getServletContext().getInitParameter("needAuthority");
}
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String a=request.getRequestURI();
		if(a.contains(".css") || a.contains(".png") || a.contains(".jpg")) {
			chain.doFilter(request, response);
		}else {
//			System.out.println("AuthorityWorking");
		String servletPath=request.getServletPath();
		String auth_name=(String) request.getSession().getAttribute("auth_name");
		List<String> urls=Arrays.asList(needAuthority.split(","));
		 boolean b=false;
			for(String url:urls) {
			  if(url.equals(servletPath)) {
			  b=true;
			  break;
			  }
		  }
		  if(b) {
			  if(auth_name.equals("manager")) {
					chain.doFilter(request, response);
				}
			  else
			        response.sendRedirect(request.getServletContext().getContextPath()+"/authError.jsp");
		  }
		  else
			  chain.doFilter(request, response);
		}
	}
}
