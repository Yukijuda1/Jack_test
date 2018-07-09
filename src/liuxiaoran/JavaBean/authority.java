package liuxiaoran.JavaBean;

public class authority {
private Integer id;
private Integer user_id;
private String auth_name;
public authority() {}

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
public String getAuth_name() {
	return auth_name;
}
public void setAuth_name(String auth_name) {
	this.auth_name = auth_name;
}
@Override
public String toString() {
	return "authority [id=" + id + ", user_id=" + user_id + ", auth_name=" + auth_name + "]";
}

}
