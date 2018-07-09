package liuxiaoran.tools;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0Pool {
private static final String DriverClass="com.mysql.jdbc.Driver";
private static final String JdbcUrl="jdbc:mysql://localhost:3306/liuxiaoran";
private static final String User="root";
private static final String Password="123456";
private static ComboPooledDataSource cpds=new ComboPooledDataSource();//注意这个属性要为static
private c3p0Pool() {
	try {
		cpds.setDriverClass(DriverClass);
	} catch (PropertyVetoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	cpds.setAcquireIncrement(10);
	cpds.setInitialPoolSize(30);
	cpds.setMinPoolSize(10);
	cpds.setMaxPoolSize(50);
	cpds.setMaxStatements(20);
	cpds.setMaxStatementsPerConnection(5);
    cpds.setJdbcUrl(JdbcUrl);
    cpds.setUser(User);
    cpds.setPassword(Password);
}
private static c3p0Pool instance=new c3p0Pool(){};

public static c3p0Pool getInstance() {
	return instance;
}
public  Connection getConnection() {
	Connection conn=null;
	try {
		conn=cpds.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
}
//public void Close(Connection conn) {
//	try {
//		conn.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
}
