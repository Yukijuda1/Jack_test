package liuxiaoran.filterPackage;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 自定义HttpFilter
 * @author 95
 *
 */
public abstract class HttpFilter implements Filter {
 private FilterConfig filterConfig;
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		doFilter(request, response, chain);
	}
	/**
	 * 子类重写该方法，为http特制
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws IOException, ServletException;
 /**
  *子类中 不建议重写
  */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
		init();
	}
  /**
   * 供子类继承的初始化方法，可以通过getFilterConfig获得FilterConfin对象
   */
  protected void init() {}
  public FilterConfig getFilterConfig() {
	return filterConfig;
}
	@Override
		public void destroy() {
			// TODO Auto-generated method stub
			Filter.super.destroy();
		}
}
