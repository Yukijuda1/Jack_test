package liuxiaoran.filterPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.tools.c3p0Pool;

public class ConnWrongFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String a=request.getRequestURI();
		if(a.contains(".css") || a.contains(".js") || a.contains(".png") || a.contains(".jpg")) {
			//访问静态I资源不开启事务
			chain.doFilter(request, response);
		}
		else {
		Connection conn=null;
//		c3p0Pool pool=new c3p0Pool();

		try {
			//1、获取连接
			conn=c3p0Pool.getInstance().getConnection();//事务处理代码中获取的Connection是在里的开启事务的连接
			//2、开启事务
			conn.setAutoCommit(false);
			//3、用ThreadLocal把conn与当前线程绑定
			ConnectionContext.getInstance().bind(conn);
			//4、把请求转发给Servlet
		chain.doFilter(request, response);
		//5、提交事务
		conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			//6、回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			HttpServletResponse resp=(HttpServletResponse) response;
			HttpServletRequest req=(HttpServletRequest) request;
			resp.sendRedirect(req.getContextPath()+"/affairsWrong.jsp");
		}finally {
			//7、解除绑定
		ConnectionContext.getInstance().remove();
		//8、关闭连接
          try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	}

}
