package test;

import java.sql.Connection;
import java.sql.SQLException;

import liuxiaoran.daoImpPackage.borrowinfoDAOimp;
import liuxiaoran.daoPackage.borrowinfoDAO;
import liuxiaoran.tools.c3p0Pool;

public class test {
public static void main(String[] args) throws SQLException {

/*String content=getContent.getTXT("C:\\Users\\95\\Desktop\\明日开发.txt");
System.out.println(content);*/
	Connection conn=c3p0Pool.getInstance().getConnection();
borrowinfoDAO dao=new borrowinfoDAOimp(conn);
System.out.println(dao.getBorrowBook("code152154986391651"));

conn.close();
	
}
}