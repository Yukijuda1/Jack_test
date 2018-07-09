package liuxiaoran.JavaBean;

import java.sql.Timestamp;

public class message {
private Integer id;
private Integer receive_id;
private Integer send_id;
private String content;
private Timestamp createtime;
private int isread;
private int result;
public int getResult() {
	return result;
}
public void setResult(int result) {
	this.result = result;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
private String username;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getReceive_id() {
	return receive_id;
}
public void setReceive_id(Integer receive_id) {
	this.receive_id = receive_id;
}
public Integer getSend_id() {
	return send_id;
}
public void setSend_id(Integer send_id) {
	this.send_id = send_id;
}
public Timestamp getCreatetime() {
	return createtime;
}
public void setCreatetime(Timestamp createtime) {
	this.createtime = createtime;
}
public int getIsread() {
	return isread;
}
public void setIsread(int isread) {
	this.isread = isread;
}
@Override
public String toString() {
	return "message [id=" + id + ", receive_id=" + receive_id + ", send_id=" + send_id + ", content=" + content
			+ ", createtime=" + createtime + ", isread=" + isread + ", result=" + result + ", username=" + username
			+ "]";
}




}
