package liuxiaoran.JavaBean;

import java.sql.Timestamp;

public class comment {
private Integer id;
private Integer user_id;
private Integer book_id;
private String content;
private Timestamp createtime;
private Integer praisecount;

private String username;//х║ги
private String pictureurl;//х║ги
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public Integer getBook_id() {
	return book_id;
}
public void setBook_id(Integer book_id) {
	this.book_id = book_id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Timestamp getCreatetime() {
	return createtime;
}
public void setCreatetime(Timestamp createtime) {
	this.createtime = createtime;
}
public Integer getPraisecount() {
	return praisecount;
}
public void setPraisecount(Integer praisecount) {
	this.praisecount = praisecount;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPictureurl() {
	return pictureurl;
}
public void setPictureurl(String pictureurl) {
	this.pictureurl = pictureurl;
}
@Override
public String toString() {
	return "comment [id=" + id + ", user_id=" + user_id + ", book_id=" + book_id + ", content=" + content
			+ ", createtime=" + createtime + ", praisecount=" + praisecount + ", username=" + username + ", pictureurl="
			+ pictureurl + "]";
}

}
