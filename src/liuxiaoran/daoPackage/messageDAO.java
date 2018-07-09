package liuxiaoran.daoPackage;
import java.sql.Timestamp;
import java.util.List;

import liuxiaoran.JavaBean.message;

public interface messageDAO extends DAO<message> {
List<message> getMessages(Integer send_id);
List<message> getReadMessages(Integer receive_id);
List<message> getNotReadMessages(Integer receive_id);
void addMessage(Integer receive_id,Integer send_id,String content,Timestamp createtime);
void deleteMessage(Integer id);
void agree(Integer id,Timestamp createtime);
void disagree(Integer id,Timestamp createtime);
message getMessage(Integer id);
}
