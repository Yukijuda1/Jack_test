package liuxiaoran.filterPackage;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Prama", "no-cache");
		chain.doFilter(request, response);
	}

}
