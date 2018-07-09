package liuxiaoran.daoImpPackage;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import liuxiaoran.JavaBean.book;
import liuxiaoran.daoPackage.bookDAO;

public class bookDAOimp extends jdbcDAOimp<book> implements bookDAO{
	private Connection conn;
	public bookDAOimp(Connection conn) {
		this.conn=conn;
	}
	public bookDAOimp() {}
public List<book> queryBookInfo(String name,String type,String author) {
	List<book> bookList=null;
	String sql="select * from book where name like ? and type like ? and author like ?";
	String[] args=new String[] {"%"+name+"%","%"+type+"%","%"+author+"%"};
	try {
		bookList=getObjectList(conn, sql, args);
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
public List<book> queryBookInfo(String search) {
	// TODO Auto-generated method stub
	List<book> bookList=null;
	String sql="select * from book where no like ? or name like ? or type like ? or author like ? or description like ?";
	String[] args=new String[] {"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%"};
	try {
		bookList=getObjectList(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(bookList.size()==0) {
		return null;
	}else
	return bookList;
}
public List<book> getAllBook(){
	List<book> bookList=null;
	String sql="select * from book";
	try {
		bookList=getObjectList(conn, sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bookList;
}
public book getBook(Integer id) {
	book b=null;
	String sql="select * from book where id=?";
	try {
		b=getObject(conn, sql,id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return b;
}
public void addBook(Object ... args){
	String sql="insert into book(name,no,number,location,author,type,description,pictureurl) values(?,?,?,?,?,?,?,?)";
	try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public boolean deleteBookInfo(Integer id) {
	String sql="delete from book where id=?";
	try {
		update(conn, sql, id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
}
public void deleteBookInfo(String name) {
	String sql="delete from book where name=?";
	try {
		update(conn, sql, name);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void updateBookInfo(Integer id) {
	// TODO Auto-generated method stub
	
}
@Override
public book getBook(String name) {
	book b=null;
	String sql="select * from book where name=?";
	try {
		b=getObject(conn, sql,name);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

return b;
}
@Override
public book getBookbyNo(String no) {
	// TODO Auto-generated method stub
	book b=null;
	String sql="select * from book where no=?";
	try {
		b=getObject(conn, sql,no);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return b;
}
@Override
public boolean checkByName(String name) {
	// TODO Auto-generated method stub
	String bookName=null;
	boolean flag = false;
	String sql="select name from book where name=?";
	try {
		bookName=(String) getforValues(conn, sql, name);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
if(bookName == null) {
	flag=true;
}
	return flag;
}
@Override
public boolean checkByNo(Integer no) {
	// TODO Auto-generated method stub
	String bookName=null;
	boolean flag = false;
	String sql="select name from book where no=?";
	try {
		bookName=(String) getforValues(conn, sql, no);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
if(bookName == null) {
	flag=true;
}
	return flag;
}



@Override
public void borrow(String name) {
	// TODO Auto-generated method stub
	String sql="update book set number=number-1 where name=?";
	try {
		update(conn, sql, name);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void alertAll(Object... args) {
	// TODO Auto-generated method stub
	String sql="update book set name=?,no=?,number=?,location=?,author=?,type=?,description=? where id=?";
	try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
@Override
public void alertPictureurl(Integer book_id, String pictureurl) {
	// TODO Auto-generated method stub
	String sql="update book set pictureurl=? where id=?";
	Object[] args=new Object[] {pictureurl,book_id};
	try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void borrow(Integer id) {
	// TODO Auto-generated method stub
	String sql="update book set number=number-1 where id=?";
	try {
		update(conn, sql, id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void back(Integer id) {
	// TODO Auto-generated method stub
	String sql="update book set number=number+1 where id=?";
	try {
		update(conn, sql, id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public List<book> getBooksByTypes(String ... types) {
	// TODO Auto-generated method stub
	String sql=new String();
	List<book> books=null;
	if(types.length==1) 
		sql="select * from book where type like ?";
	else if(types.length==2) 
		sql="select * from book where type  like ? or type like ?";
	else if(types.length==3) 
		sql="select * from book where type  like ? or type like ? or type like ?";
List<Object> argsList=new ArrayList<>();
	for(Object obj:types) {
		argsList.add("%"+obj+"%");
	}
	Object[] args=argsList.toArray();
	try {
		books=getObjectList(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(books.size()==0)
	return null;
	else
		return books;
}
@Override
public List<book> getBooksByAuthors(String ... authors) {
	// TODO Auto-generated method stub
	String sql=new String();
	List<book> books=null;
	if(authors.length==1) 
		sql="select * from book where author like ?";
	else if(authors.length==2) 
		sql="select * from book where author  like ? or author like ?";
	else if(authors.length==3) 
		sql="select * from book where author  like ? or author like ? or author like ?";
List<Object> argsList=new ArrayList<>();
	for(Object obj:authors) {
		argsList.add("%"+obj+"%");
	}
	Object[] args=argsList.toArray();
	try {
		books=getObjectList(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(books.size()==0)
	return null;
	else
		return books;
}

}
