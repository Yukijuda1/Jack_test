package liuxiaoran.daoImpPackage;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import liuxiaoran.JavaBean.user;
import liuxiaoran.daoPackage.userDAO;

public class userDAOimp extends jdbcDAOimp<user> implements userDAO{
private Connection conn;
public userDAOimp(Connection conn) {
	this.conn=conn;
}
public void addUser(String username,String password,String email) {
	Boolean b=checkUser(username);
	String sql="insert into user(username,password,email) values(?,?,?)";
	String[] args=new String[] {username,password,email};
	if(b==true) {
    try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
public Boolean checkUser(String username) {
	String sql="select username from user where username=? ";
	String checkname = null;
    try {
	checkname=(String) getforValues(conn, sql, username);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if(checkname==null) 
    	return true;
    else
	return false ;
}
public user getUser(Integer id) {
	user u=null;
	String sql="select * from user where id=?";
	try {
		u=getObject(conn, sql, id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return u;
}
@Override
public user getUser(String username) {
	// TODO Auto-generated method stub
	user u=null;
	String sql="select * from user where username=?";
	try {
		u=getObject(conn, sql, username);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return u;
}
@Override
public user getUser(String username, String password) {
	// TODO Auto-generated method stub
//	Blob headpicture=null;
	user u=null;
	String sql="select * from user where username=? and password=?";
	String[] args=new String[] {username,password};
	try {
		u=getObject(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return u;
}
@Override
public void updatePicture(String filePath, Integer id) {
	// TODO Auto-generated method stub
	String sql="update user set pictureurl=? where id=?";
	Object[] args=new Object[] {filePath,id};
	try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void updatePassword(String username, String password) {
	// TODO Auto-generated method stub
	String sql="update user set password=? where username=?";
	String[] args=new String[] {password,username};
	try {
		update(conn, sql, args);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
