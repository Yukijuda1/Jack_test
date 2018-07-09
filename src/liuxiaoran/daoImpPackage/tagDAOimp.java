package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.tag;
import liuxiaoran.daoPackage.tagDAO;

public class tagDAOimp extends jdbcDAOimp<tag> implements tagDAO {
private Connection conn;
public tagDAOimp(Connection conn) {
	this.conn=conn;
}
public tagDAOimp() {
	// TODO Auto-generated constructor stub
}
	
	@Override
	public List<tag> getTags(Integer book_id) {
		// TODO Auto-generated method stub
		List<tag> tags=null;
		String sql="select * from tag where book_id=?";
		try {
			tags=getObjectList(conn, sql, book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tags.size()==0)
		return null;
		else
			return tags;
	}

	@Override
	public void addTag(Integer book_id, String content, Timestamp createtime) {
		// TODO Auto-generated method stub
		String sql="insert into tag(book_id,content,createtime) values(?,?,?)";
		Object[] args=new Object[] {book_id,content,createtime};
		try {
			update(conn, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTag(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from tag where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void praise(Integer id) {
		// TODO Auto-generated method stub
		String sql="update tag set praisecount=praisecount+1 where id=?";
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
		String sql="update tag set praisecount=praisecount-1 where id=?";
		try {
			update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	

}
