package liuxiaoran.JavaBean;

public class user {
	private Integer id;
private String username;
private String password;
private String pictureurl;
private String email;
public user() {}
public user(String username,String password) {
	this.username=username;
	this.password=password;
}
public String getUsername() {
	return username;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
public String getPictureurl() {
	return pictureurl;
}
public void setPictureurl(String pictureurl) {
	this.pictureurl = pictureurl;
}
@Override
public String toString() {
	return "user [id=" + id + ", username=" + username + ", password=" + password + ", pictureurl=" + pictureurl
			+ ", email=" + email + "]";
}







}
