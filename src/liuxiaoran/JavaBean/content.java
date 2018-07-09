package liuxiaoran.JavaBean;

import java.sql.Timestamp;

public class content implements Comparable<content>{
private Integer id;
private Integer book_id;
private Integer no;
public Integer getNo() {
	return no;
}
public void setNo(Integer no) {
	this.no = no;
}
private String contenturl;
private Timestamp createtime;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getBook_id() {
	return book_id;
}
public void setBook_id(Integer book_id) {
	this.book_id = book_id;
}
public String getContenturl() {
	return contenturl;
}
public void setContenturl(String contenturl) {
	this.contenturl = contenturl;
}
public Timestamp getCreatetime() {
	return createtime;
}
public void setCreatetime(Timestamp createtime) {
	this.createtime = createtime;
}
@Override
public String toString() {
	return "content [id=" + id + ", book_id=" + book_id + ", no=" + no + ", contenturl=" + contenturl + ", createtime="
			+ createtime + "]";
}
@Override
public int compareTo(content o) {
	// TODO Auto-generated method stub
	Integer i=this.getNo()-o.getNo();//∞¥±‡∫≈≈≈–Ú
	return i;
}


}
