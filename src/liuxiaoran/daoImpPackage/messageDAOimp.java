package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.message;
import liuxiaoran.JavaBean.user;
import liuxiaoran.daoPackage.messageDAO;

public class messageDAOimp extends jdbcDAOimp<message> implements messageDAO{
private Connection conn;
public messageDAOimp(Connection conn) {
	// TODO Auto-generated constructor stub
	this.conn=conn;
}
public messageDAOimp() {}
	@Override
	public List<message> getMessages(Integer send_id) {
		// TODO Auto-generated method stub
		List<message> messageList=null;
		String sql="select * from message where send_id=?";
		try {
			messageList=getObjectList(conn, sql, send_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(messageList.size()==0) {
			return null;
		}else
		return messageList;
	}
	@Override
	public void addMessage(Integer receive_id, Integer send_id, String content,Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="insert into message(receive_id,send_id,content,createtime,isread,result) values(?,?,?,?,?,?)";
		Object[] args=new Object[] {receive_id,send_id,content,createtime,0,0};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<message> getReadMessages(Integer receive_id) {
		// TODO Auto-generated method stub
		List<message> messageList=null;
		String sql="select * from message where receive_id=? and isread=1";
		try {
			messageList=getObjectList(conn, sql,receive_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(messageList.size()==0) {
			return null;
		}else
		return messageList;
	}
	@Override
	public List<message> getNotReadMessages(Integer receive_id) {
		// TODO Auto-generated method stub
		List<message> messageList=null;
		String sql="select * from message where receive_id=? and isread=0";
		try {
			messageList=getObjectList(conn, sql,receive_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(messageList.size()==0) {
			return null;
		}else
		return messageList;
	}
	@Override
	public void deleteMessage(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from message where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void agree(Integer id,Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="update message set isread=1,result=1,createtime=? where id=?";
		Object[] args=new Object[] {createtime,id};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void disagree(Integer id,Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="update message set isread=1,result=0,createtime=? where id=?";
		Object[] args=new Object[] {createtime,id};
		try {
			update(conn, sql,args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public message getMessage(Integer id) {
		// TODO Auto-generated method stub
		String sql="select * from message where id=?";
		message m=null;
		try {
			m=getObject(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

}
