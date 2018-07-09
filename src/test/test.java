package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

import liuxiaoran.daoImpPackage.commentDAOimp;
import liuxiaoran.daoImpPackage.tagDAOimp;
import liuxiaoran.daoPackage.commentDAO;
import liuxiaoran.daoPackage.tagDAO;
import liuxiaoran.tools.c3p0Pool;

public class test {
public static void main(String[] args) {
	Connection conn=c3p0Pool.getInstance().getConnection();
tagDAO dao=new tagDAOimp(conn);
//	Timestamp createtime=new Timestamp(new Date().getTime());
System.out.println(dao.getTags(35));
}
}
