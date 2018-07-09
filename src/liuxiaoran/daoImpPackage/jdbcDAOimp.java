package liuxiaoran.daoImpPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import liuxiaoran.daoPackage.DAO;
import liuxiaoran.tools.ReflectionUtils;

public class jdbcDAOimp<T> implements DAO<T>{
private QueryRunner qureyRunner=null;
private Class<T> type;
public jdbcDAOimp() {
	this.qureyRunner=new QueryRunner();
	this.type=ReflectionUtils.getSuperGenericType(getClass());
}

	@Override
	public T getObject(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return qureyRunner.query(conn, sql,  new BeanHandler<>(type),args);
	}

	@Override
	public List<T> getObjectList(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return qureyRunner.query(conn, sql, new BeanListHandler<>(type),args);
	}

	@Override
	public void update(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
	           qureyRunner.update(conn, sql, args);
	}

	@Override
	public void batch(Connection conn, String sql, Object[] ... args) throws SQLException {
		// TODO Auto-generated method stub
		qureyRunner.batch(conn, sql, args);
	}

	@Override
	public Object getforValues(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub 
		return qureyRunner.query(conn, sql, new ScalarHandler<>(),args);
	}

}
