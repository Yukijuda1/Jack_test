package liuxiaoran.servletPackage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liuxiaoran.JavaBean.ConnectionContext;
import liuxiaoran.JavaBean.book;
import liuxiaoran.JavaBean.borrowinfo;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoImpPackage.bookDAOimp;
import liuxiaoran.daoImpPackage.borrowinfoDAOimp;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.borrowinfoDAO;
import liuxiaoran.tools.sendCode;
import liuxiaoran.tools.sendEmail;
/**
 * Servlet implementation class borrowServlet
 */
@WebServlet("/borrow")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if(method.equals("check")) {
		String name=request.getParameter("name");
		String resultData=null;
		bookDAO dao=new bookDAOimp(ConnectionContext.getInstance().get());
		book b=dao.getBook(name);
		if(b!=null) {
			if(b.getNumber()<=0) {
				resultData = "no";
			}
			else {
				resultData="yes";
			}
		}
		else {
			resultData="no";
		}
		response.getWriter().print(resultData);
	}
		else if(method.equals("email")) {
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			int code = (int)(Math.random()*(9999-1000+1))+1000;
			sendCode send=new sendCode();
			try {
				send.send(email, username, code);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(code);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String username=request.getParameter("username");*/
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
	String email=request.getParameter("email");//接受邮箱是因为要发送借书码。
			bookDAO book_dao=new bookDAOimp(ConnectionContext.getInstance().get());
			book b=book_dao.getBook(name);
			if(b!=null) {
				if(b.getNumber()>0) {
					user u=(user) request.getSession().getAttribute("user");
					Integer user_id=u.getId();
					Integer book_id=b.getId();
					Timestamp createtime=new Timestamp(new Date().getTime());
					String chars = "abcdefghijklmnopqrstuvwxyz";
					String code="code"+new Random().nextInt(100)+chars.charAt((int)(Math.random() * 26))+chars.charAt((int)(Math.random() * 26))+chars.charAt((int)(Math.random() * 26));
					borrowinfoDAO borrowinfo_dao=new borrowinfoDAOimp(ConnectionContext.getInstance().get());
					borrowinfo_dao.add(user_id, book_id, createtime,code);//向borrowinfo表中存入借书记录
			        borrowinfo bi=borrowinfo_dao.getBorrowBook(code);//取出刚刚存入的borrowinfo对象
			        String location=bi.getLocation();
					sendEmail send=new sendEmail();
					try {
						send.send(email, u.getUsername(), code,location);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//发送确认邮件
					response.getWriter().print("yes");
				}
				else {
//					request.setAttribute("message", 1);//目前藏书量为0
					response.getWriter().print("no");
				}
			}
			else {
//				request.setAttribute("message", 1);//书目信息可能被删除
				response.getWriter().print("no");
			}
	}

}
