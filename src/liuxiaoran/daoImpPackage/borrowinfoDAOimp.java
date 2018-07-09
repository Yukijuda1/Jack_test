package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.book;
import liuxiaoran.JavaBean.borrowinfo;
import liuxiaoran.daoPackage.bookDAO;
import liuxiaoran.daoPackage.borrowinfoDAO;

public class borrowinfoDAOimp extends jdbcDAOimp<borrowinfo> implements borrowinfoDAO{
	private Connection conn;
	public borrowinfoDAOimp(Connection conn) {
		this.conn=conn;
	}
/*	private bookDAO bookDAO=new bookDAOimp(conn);*/
	public borrowinfoDAOimp() {}
	@Override
	public List<borrowinfo> getBorrowBooks(Integer user_id) {
		// TODO Auto-generated method stub
		List<borrowinfo> bookList=null;
		String sql="select book.name,book.author,book.type,book.pictureurl,book.description,book.location,borrowinfo.book_id,borrowinfo.createtime,borrowinfo.code,borrowinfo.finishtime,borrowinfo.result from book,borrowinfo where borrowinfo.user_id=? and borrowinfo.book_id=book.id";
		try {
			bookList=getObjectList(conn, sql, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bookList.size()==0) {
			return null;
		}else
		return bookList;
   
	}
	@Override
	public void add(Integer user_id, Integer book_id,Timestamp createtime,String code) {
		// TODO Auto-generated method stub
		String sql="insert into borrowinfo(user_id,book_id,createtime,code,result) values(?,?,?,?,?)";
		Object[] args=new Object[] {user_id,book_id,createtime,code,0};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public borrowinfo getBorrowBook(String code) {
		// TODO Auto-generated method stub
		borrowinfo bi=null;
		String sql="select book.name,book.author,book.type,book.pictureurl,book.description,book.location,borrowinfo.createtime,borrowinfo.code from book,borrowinfo "
				+ "where borrowinfo.code=? and borrowinfo.book_id=book.id";
		try {
			bi=getObject(conn, sql, code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bi;
	}
	@Override
	public borrowinfo getBorrowInfo(String code) {
		// TODO Auto-generated method stub
		borrowinfo bi=null;
		String sql="select * from borrowinfo where code=?";
		try {
			bi=getObject(conn, sql, code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bi;
	}
	@Override
	public borrowinfo getBorrowinfo(Integer id) {
		// TODO Auto-generated method stub
		borrowinfo bi=null;
		String sql="select * from borrowinfo where id=?";
		try {
			bi=getObject(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bi;
	}
	@Override
	public void out(Integer id, Timestamp finishtime) {
		// TODO Auto-generated method stub
		String sql="update borrowinfo set finishtime=?,result=1 where id=?";
		Object[] args=new Object[] {finishtime,id};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from borrowinfo where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
