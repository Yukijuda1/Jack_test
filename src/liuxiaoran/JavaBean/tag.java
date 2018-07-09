package liuxiaoran.JavaBean;

import java.sql.Timestamp;

public class tag {
	private Integer id;
	private Integer book_id;
	private String content;
	private Timestamp createtime;
	private Integer praisecount;
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
	@Override
	public String toString() {
		return "tag [id=" + id + ", book_id=" + book_id + ", content=" + content + ", createtime=" + createtime
				+ ", praisecount=" + praisecount + "]";
	}
	
}
