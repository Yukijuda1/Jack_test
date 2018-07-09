package liuxiaoran.servletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoPackage.bookDAO;

/**
 * Servlet implementation class historyServlet
 */
@WebServlet("/history")
public class historyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public historyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     RequestDispatcher rd=request.getRequestDispatcher("history.jsp");
/*		c3p0Pool pool=new c3p0Pool();
		Connection conn=pool.getConnection();*/
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		Cookie[] cookies=request.getCookies();
		//���Cookie���飬�����ڸ�Cookie��name��ɾ��ԭ����Cookie������µĵ�Cookie������棬����ֵ��ͬ
		List<Cookie> bookCookies=new ArrayList<>();
		List<book> bookList=new ArrayList<>();
		if(cookies!=null && cookies.length>0) {
		for(Cookie cookie:cookies){
		   if(cookie.getName().startsWith("bookid_")){
			   bookCookies.add(cookie);
		   }
		}
		if(bookCookies.size()>=10) {
			for(int i=0;i<bookCookies.size()-10;i++) {
				bookCookies.get(i).setMaxAge(0);//ɾ�������cookies��һ��ֻ����5��bookid_xxx ��cookie
				response.addCookie(bookCookies.get(i));//ע������MaxAge��Ҫ�ش�һ��
			}
		for(int i=bookCookies.size()-1;i>=(bookCookies.size()-10);i--) {
			String strid=bookCookies.get(i).getValue();
			Integer id=Integer.valueOf(strid); 
			book b=dao.getBook(id);
			bookList.add(b);
			}
		}
		else {
			for(int i=bookCookies.size()-1;i>=0;i--) {
				String strid=bookCookies.get(i).getValue();
				Integer id=Integer.valueOf(strid); 
				book b=dao.getBook(id);
				bookList.add(b);
				}
		}
	     request.setAttribute("bookList", bookList);
         rd.forward(request, response);
		
		
	}

	}
}
