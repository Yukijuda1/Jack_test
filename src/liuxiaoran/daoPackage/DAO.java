package liuxiaoran.daoPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * 访问数据的DAO接口
 * @param T:DAO处理的实体类的类型
 *
 */
public interface DAO<T> {
	/**
	 * 返回一个T的对象
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	T getObject(Connection conn,String sql,Object ...args) throws SQLException;
	/**
	 * 返回T的List集合
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	List<T> getObjectList(Connection conn,String sql,Object ...args ) throws SQLException;
	/**
	 * INSERT,UPDATE,DELETE
	 * @param conn:数据库连接
	 * @param sql:sql语句
	 * @param args:填充占位符的可变参数
	 * @throws SQLException 
	 */
void update(Connection conn,String sql,Object ... args) throws SQLException; 
/**
 * 返回具体的一个值，如：总值，平均值，某个列的值
 * @param conn
 * @param sql
 * @param args
 * @return
 * @throws SQLException 
 */
<E> E getforValues(Connection conn,String sql,Object ...args ) throws SQLException;
/**
 * 批量处理
 * @param conn
 * @param sql
 * @param args:填充占位符的Object[]的可变参数
 * @throws SQLException 
 */
void batch(Connection conn,String sql,Object[]  ... args) throws SQLException;
}
