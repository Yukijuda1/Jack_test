package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.content;
import liuxiaoran.daoPackage.contentDAO;

public class contentDAOimp extends jdbcDAOimp<content> implements contentDAO{
	private Connection conn;
	public contentDAOimp(Connection conn) {
		this.conn=conn;
	}
	public contentDAOimp() {}
	@Override
	public List<content> getContents(Integer book_id) {
		// TODO Auto-generated method stub
		List<content> contentList=null;
		String sql="select * from content where book_id=?";
		try {
			contentList=getObjectList(conn, sql, book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contentList!=null) {
			return contentList;
		}else
		return null;
	}
	@Override
	public content getContent(Integer book_id, Integer no) {
		// TODO Auto-generated method stub
		String sql="select * from content where book_id=? and no=?";
		Integer[] args=new Integer[] {book_id,no};
		content c=null;
		try {
			c=getObject(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	@Override
	public void addContent(Integer book_id, Integer no, String contenturl, Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="insert into content(book_id,no,contenturl,createtime) values(?,?,?,?)";
		Object[] args=new Object[] {book_id,no,contenturl,createtime};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Integer book_id) {
		String sql="delete from content where book_id=?";
		try {
			update(conn, sql, book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void delete(Integer book_id, Integer no) {
		// TODO Auto-generated method stub
		String sql="delete from content where book_id=? and no=?";
		Integer[] args=new Integer[] {book_id,no};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void alert(Integer book_id, Integer no, String contenturl, Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="update content set contenturl=?,createtime=? where book_id=? and no=?";
		Object[] args=new Object[] {contenturl,createtime,book_id,no};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
