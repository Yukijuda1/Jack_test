package liuxiaoran.daoPackage;

import java.sql.Timestamp;
import java.util.List;
import liuxiaoran.JavaBean.content;

public interface contentDAO extends DAO<content>{
List<content> getContents(Integer book_id);
content getContent(Integer book_id,Integer no);
void addContent(Integer book_id,Integer no,String contenturl,Timestamp createtime);
void delete(Integer book_id);
void delete(Integer book_id,Integer no);
void alert(Integer book_id,Integer no,String contenturl,Timestamp createtime);
}
