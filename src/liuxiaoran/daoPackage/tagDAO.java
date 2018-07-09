package liuxiaoran.daoPackage;

import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.tag;

public interface tagDAO extends DAO<tag>{
public List<tag> getTags(Integer book_id);
public void addTag(Integer book_id,String content,Timestamp createtime);
public void deleteTag(Integer id);
public void praise(Integer id);
public void dispraise(Integer id);
}
