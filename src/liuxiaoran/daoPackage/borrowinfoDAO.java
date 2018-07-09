package liuxiaoran.daoPackage;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.borrowinfo;

public interface borrowinfoDAO extends DAO<borrowinfo>{
List<borrowinfo> getBorrowBooks(Integer user_id);
void add(Integer user_id,Integer book_id,Timestamp createtime,String code);
borrowinfo getBorrowBook(String code);
borrowinfo getBorrowinfo(Integer id);
borrowinfo getBorrowInfo(String code);
void out(Integer id,Timestamp finishtime);
void delete(Integer id);
}
