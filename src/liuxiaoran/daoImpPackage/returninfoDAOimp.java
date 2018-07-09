package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.returninfo;
import liuxiaoran.daoPackage.returninfoDAO;

public class returninfoDAOimp extends jdbcDAOimp<returninfo> implements returninfoDAO{
private Connection conn;
public  returninfoDAOimp(Connection conn) {
	// TODO Auto-generated constructor stub
	this.conn=conn;
}
public returninfoDAOimp() {}
	@Override
	public List<returninfo> getReturnBooks(Integer user_id) {
		// TODO Auto-generated method stub
		List<returninfo> returnBooks=null;
		String sql="select book.name,book.author,book.type,book.pictureurl,book.description,returninfo.book_id,returninfo.createtime from book,returninfo where returninfo.user_id=? and returninfo.book_id=book.id";
		try {
			returnBooks=getObjectList(conn, sql, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       if(returnBooks.size()==0) {
    	   return null;
       }else
		return returnBooks;
	}
	@Override
	public void in(Integer book_id, Integer user_id, Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="insert into returninfo(user_id,book_id,createtime) values(?,?,?)";
		Object[] args=new Object[] {user_id,book_id,createtime};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
