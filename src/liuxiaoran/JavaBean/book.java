package liuxiaoran.JavaBean;

public class book {
	private Integer id;
private String name;
private String no;
private Integer number;
private String location;
private String author;
private String description;
private String type;
private String pictureurl;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNo() {
	return no;
}
public void setNo(String no) {
	this.no = no;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
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
@Override
public String toString() {
	return "book [id=" + id + ", name=" + name + ", no=" + no + ", number=" + number + ", location=" + location
			+ ", author=" + author + ", description=" + description + ", type=" + type + ", pictureurl=" + pictureurl
			+ "]";
}




}
