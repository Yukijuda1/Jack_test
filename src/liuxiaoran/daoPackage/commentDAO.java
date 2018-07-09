package liuxiaoran.daoPackage;

import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.comment;

public interface commentDAO extends DAO<comment>{
public comment getComment(Integer id);
public List<comment> getCommentsByBook_id(Integer book_id);
public List<comment> getCommentsByUser_id(Integer user_id);
public void addComment(Integer user_id,Integer book_id,String content,Timestamp createtime);
public void praise(Integer id);
public void dispraise(Integer id);
public void deleteComment(Integer id);
}
