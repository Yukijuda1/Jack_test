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
			//���ʾ�̬I��Դ����������
			chain.doFilter(request, response);
		}
		else {
		Connection conn=null;
//		c3p0Pool pool=new c3p0Pool();

		try {
			//1����ȡ����
			conn=c3p0Pool.getInstance().getConnection();//����������л�ȡ��Connection������Ŀ������������
			//2����������
			conn.setAutoCommit(false);
			//3����ThreadLocal��conn�뵱ǰ�̰߳�
			ConnectionContext.getInstance().bind(conn);
			//4��������ת����Servlet
		chain.doFilter(request, response);
		//5���ύ����
		conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			//6���ع�����
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
			//7�������
		ConnectionContext.getInstance().remove();
		//8���ر�����
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
