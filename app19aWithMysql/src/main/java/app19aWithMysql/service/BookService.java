package app19aWithMysql.service;

import java.util.List;

import app19aWithMysql.domain.Book;
import app19aWithMysql.domain.Category;

public interface BookService {
    
    List<Category> getAllCategories();
    Category getCategory(int id);
    List<Book> getAllBooks();
    Book save(Book book);
    String update(Book book);
    String delete(long id);
    Book get(long id);
    long getNextId();

}
