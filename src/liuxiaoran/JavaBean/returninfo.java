package liuxiaoran.JavaBean;

import java.sql.Timestamp;

public class returninfo {
	private Integer id;
	private Integer user_id;
	private Integer book_id;
	private Timestamp createtime;
	private String name;
	private String author;
	private String type;
	private String 	pictureurl;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "returninfo [id=" + id + ", user_id=" + user_id + ", book_id=" + book_id + ", createtime=" + createtime
				+ ", name=" + name + ", author=" + author + ", type=" + type + ", pictureurl=" + pictureurl
				+ ", description=" + description + "]";
	}


	
	
}
