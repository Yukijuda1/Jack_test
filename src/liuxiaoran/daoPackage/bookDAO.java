package liuxiaoran.daoPackage;


import java.util.List;

import liuxiaoran.JavaBean.book;

public interface bookDAO extends DAO<book>{
	List<book> queryBookInfo(String name,String type,String author);
	List<book> queryBookInfo(String search);
     List<book> getAllBook();
	 List<book> getBooksByTypes(String ... types);
	 List<book> getBooksByAuthors(String ... authors);
	book getBook(Integer id);
	book getBook(String name);
	book getBookbyNo(String no);
	boolean checkByName(String name);
	boolean checkByNo(Integer no);
	void borrow(String name);
	void borrow(Integer id);
	void back(Integer id);
	void alertAll(Object ...args);
	void alertPictureurl(Integer book_id,String pictureurl);
	void addBook(Object ... args);
	boolean deleteBookInfo(Integer id);
	 void updateBookInfo(Integer id);

}
