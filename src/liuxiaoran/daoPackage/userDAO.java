package liuxiaoran.daoPackage;
import liuxiaoran.JavaBean.user;

public interface userDAO extends DAO<user>{
	void addUser(String username,String password,String email);
	Boolean checkUser(String username);
//	Blob getheadpicture(Integer id);
//	Blob getheadpicture(String username);
	user getUser(Integer id);
	user getUser(String username);
	user getUser(String username,String password);
	void updatePicture(String filePath,Integer id);
	void updatePassword(String username,String password);
}
