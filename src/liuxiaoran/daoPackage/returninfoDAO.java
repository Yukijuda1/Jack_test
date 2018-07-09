package liuxiaoran.daoPackage;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.returninfo;

public interface returninfoDAO extends DAO<returninfo>{
List<returninfo> getReturnBooks(Integer user_id);
void in(Integer book_id,Integer user_id,Timestamp createtime);
}
