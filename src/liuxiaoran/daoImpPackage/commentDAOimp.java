package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.comment;
import liuxiaoran.daoPackage.commentDAO;

public class commentDAOimp extends jdbcDAOimp<comment> implements commentDAO{
	private Connection conn;
	public commentDAOimp(Connection conn) {
		this.conn=conn;
	}
	public commentDAOimp() {}
	
	@Override
	public comment getComment(Integer id) {
		// TODO Auto-generated method stub
		String sql="select c.*,u.username,u.pictureurl from comment c inner join user u on c.user_id=u.id where c.id=?";
			comment c=null;
			try {
				c = getObject(conn, sql, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return c;
	}

	@Override
	public List<comment> getCommentsByBook_id(Integer book_id) {
		// TODO Auto-generated method stub
		List<comment> comments=null;
		String sql="select c.*,u.username,u.pictureurl from comment c inner join user u on c.user_id=u.id where c.book_id=?";
		try {
			comments=getObjectList(conn, sql, book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(comments.size()==0) {
			return null;
		}
		else
		return comments;
	}

	@Override
	public List<comment> getCommentsByUser_id(Integer user_id) {
		// TODO Auto-generated method stub
		List<comment> comments=null;
		String sql="select c.*,u.username,u.pictureurl from comment c inner join user u on c.user_id=u.id where c.user_id=?";
		try {
			comments=getObjectList(conn, sql, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(comments.size()==0) {
			return null;
		}
		else
		return comments;
	}
	@Override
	public void addComment(Integer user_id, Integer book_id, String content, Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="insert into comment(user_id,book_id,content,createtime) values(?,?,?,?)";
		Object[] args=new Object[] {user_id,book_id,content,createtime};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void praise(Integer id) {
		// TODO Auto-generated method stub
		String sql="update comment set praisecount=praisecount+1 where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void dispraise(Integer id) {
		// TODO Auto-generated method stub
		String sql="update comment set praisecount=praisecount-1 where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteComment(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from comment where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
