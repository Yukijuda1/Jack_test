package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import liuxiaoran.JavaBean.authority;
import liuxiaoran.daoPackage.authorityDAO;

public class authorityDAOimp extends jdbcDAOimp<authority> implements authorityDAO{
	private Connection conn;
	public authorityDAOimp(Connection conn) {
		this.conn=conn;
	}
	public authorityDAOimp() {}
	@Override
	public String getAuth_name(Integer user_id) {
		// TODO Auto-generated method stub
		String sql="select auth_name from authority where user_id=?";
		String auth_name = null;
		try {
			auth_name=(String) getforValues(conn, sql, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auth_name;
	}

	@Override
	public void becomeManager(Integer user_id) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		String sql="update authority set auth_name='manager' where user_id=?";
		try {
			update(conn, sql, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addAuthority(Integer user_id) {
		// TODO Auto-generated method stub
			String sql="insert into authority(user_id) value(?)";
			try {
				update(conn, sql, user_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	@Override
	public List<authority> getManagers() {
		// TODO Auto-generated method stub
		List<authority> managers=null;
		String sql="select * from authority where auth_name='manager'";
		try {
			managers=getObjectList(conn, sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(managers.size()==0) {
			return null;
		}
		else
			return managers;
	}


}
