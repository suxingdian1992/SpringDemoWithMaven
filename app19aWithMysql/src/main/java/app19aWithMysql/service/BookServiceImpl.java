package app19aWithMysql.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import app19aWithMysql.domain.Book;
import app19aWithMysql.domain.Category;

@Service
public class BookServiceImpl extends JdbcDaoSupport implements BookService {// 当需要和数据库进行交互的时候，集成jdbc模版类，并获得即可

	/*
	 * this implementation is not thread-safe
	 */
	private List<Category> categories;

	/**
	 * 初始化方法
	 */
	public BookServiceImpl() {
		categories = new ArrayList<Category>();
		Category category1 = new Category(1, "Computing");
		Category category2 = new Category(2, "Travel");
		Category category3 = new Category(3, "Health");
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app19a.service.BookService#getAllCategories()
	 */
	public List<Category> getAllCategories() {
		return categories;
	}

	/*
	 * (non-Javadoc) 获取指定种类的编码
	 */
	public Category getCategory(int id) {
		for (Category category : categories) {
			if (id == category.getId()) {
				return category;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc) 返回全部图书的列表
	 */
	public List<Book> getAllBooks() {
		String sql = "select * from books";
		List<Map<String,Object>> bookMaps = this.getJdbcTemplate().queryForList(sql);
		List<Book> books=new ArrayList<Book>();
		for (Map<String, Object> bookMap : bookMaps) {
			Book book =new Book();
			book.setId((Integer) bookMap.get("id"));
			book.setTitle((String) bookMap.get("title"));
			book.setAuthor((String) bookMap.get("author"));
			book.setIsbn((String) bookMap.get("isbn"));
			book.setCategory((String) bookMap.get("category"));
			books.add(book);
		}
		return books;
		/*俩个种方案，均可*/
		//return this.getJdbcTemplate().query(sql, new UserMapper());
	}

	/*
	 * 使用rowMapper 19行用到， 因为query方法不能直接放回一个数组，所以我们只能通过rowMapper赋值给User;
	 * RowMapper可以将数据中的每一行封装成用户定义的类，在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	private static final class UserMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Book book = new Book();
			book.setId(rs.getLong("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setIsbn(rs.getString("isbn"));
			book.setCategory(rs.getString("category"));
			return book;
		}
	}

	/*
	 * (non-Javadoc) 保存图书，新增图书
	 */
	public Book save(Book book) {
		String sql=" insert into books (id,title,author,isbn,category) values(?,?,?,?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{
				getNextId(),book.getTitle(),book.getAuthor(),book.getIsbn(),book.getCategory()
		});
		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app19a.service.BookService#get(long) 由id取得对应图书
	 */
	public Book get(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from Books where id=?";
		// 在spring4.0及以上版本中用BeanPropertyRowMapper 取代 ParameterizedBeanPropertyRowMapper
		return this.getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app19a.service.BookService#update(app19a.domain.Book) 更新图书信息
	 */
	public String update(Book book) {
		// TODO Auto-generated method stub
		String sql = "update books set title=?,author=?,isbn=?,category=? where id=?";
		this.getJdbcTemplate().update(sql, book.getTitle(),book.getAuthor(),book.getIsbn(),book.getCategory(),book.getId());
		return "SUCCESS";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app19a.service.BookService#getNextId() 生成新的图书号，原号码基础上加1
	 */
	public long getNextId() {
		// needs to be locked
		String sql = "select max(id) as id from Books";
		Map<String, Object> maxid=this.getJdbcTemplate().queryForMap(sql);
		System.out.println(maxid.get("id"));
		return (Integer)maxid.get("id")+1;
	}

	/* (non-Javadoc)
	 * @see app19aWithMysql.service.BookService#delete(long)
	 * 删除书籍的方法
	 */
	public String delete(long id) {
		// TODO Auto-generated method stub
		String sql="delete from books where id=?";
		this.getJdbcTemplate().update(sql,id);
		return "SUCCESS";
	}
}
