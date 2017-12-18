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
public class BookServiceImpl extends JdbcDaoSupport implements BookService {// ����Ҫ�����ݿ���н�����ʱ�򣬼���jdbcģ���࣬����ü���

	/*
	 * this implementation is not thread-safe
	 */
	private List<Category> categories;

	/**
	 * ��ʼ������
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
	 * (non-Javadoc) ��ȡָ������ı���
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
	 * (non-Javadoc) ����ȫ��ͼ����б�
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
		/*�����ַ���������*/
		//return this.getJdbcTemplate().query(sql, new UserMapper());
	}

	/*
	 * ʹ��rowMapper 19���õ��� ��Ϊquery��������ֱ�ӷŻ�һ�����飬��������ֻ��ͨ��rowMapper��ֵ��User;
	 * RowMapper���Խ������е�ÿһ�з�װ���û�������࣬�����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
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
	 * (non-Javadoc) ����ͼ�飬����ͼ��
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
	 * @see app19a.service.BookService#get(long) ��idȡ�ö�Ӧͼ��
	 */
	public Book get(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from Books where id=?";
		// ��spring4.0�����ϰ汾����BeanPropertyRowMapper ȡ�� ParameterizedBeanPropertyRowMapper
		return this.getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app19a.service.BookService#update(app19a.domain.Book) ����ͼ����Ϣ
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
	 * @see app19a.service.BookService#getNextId() �����µ�ͼ��ţ�ԭ��������ϼ�1
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
	 * ɾ���鼮�ķ���
	 */
	public String delete(long id) {
		// TODO Auto-generated method stub
		String sql="delete from books where id=?";
		this.getJdbcTemplate().update(sql,id);
		return "SUCCESS";
	}
}
