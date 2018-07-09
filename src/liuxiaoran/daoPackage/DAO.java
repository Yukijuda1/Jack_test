package liuxiaoran.daoPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * �������ݵ�DAO�ӿ�
 * @param T:DAO�����ʵ���������
 *
 */
public interface DAO<T> {
	/**
	 * ����һ��T�Ķ���
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	T getObject(Connection conn,String sql,Object ...args) throws SQLException;
	/**
	 * ����T��List����
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	List<T> getObjectList(Connection conn,String sql,Object ...args ) throws SQLException;
	/**
	 * INSERT,UPDATE,DELETE
	 * @param conn:���ݿ�����
	 * @param sql:sql���
	 * @param args:���ռλ���Ŀɱ����
	 * @throws SQLException 
	 */
void update(Connection conn,String sql,Object ... args) throws SQLException; 
/**
 * ���ؾ����һ��ֵ���磺��ֵ��ƽ��ֵ��ĳ���е�ֵ
 * @param conn
 * @param sql
 * @param args
 * @return
 * @throws SQLException 
 */
<E> E getforValues(Connection conn,String sql,Object ...args ) throws SQLException;
/**
 * ��������
 * @param conn
 * @param sql
 * @param args:���ռλ����Object[]�Ŀɱ����
 * @throws SQLException 
 */
void batch(Connection conn,String sql,Object[]  ... args) throws SQLException;
}
