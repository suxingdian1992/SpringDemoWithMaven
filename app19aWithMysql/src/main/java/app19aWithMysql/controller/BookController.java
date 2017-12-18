package app19aWithMysql.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app19aWithMysql.domain.Book;
import app19aWithMysql.domain.Category;
import app19aWithMysql.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;//将当前类的一个实现注入到控制器中

    private static final Log logger = LogFactory.getLog(BookController.class);

    @RequestMapping(value = "/book_input")
    public String inputBook(Model model) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);//制造下拉选单内的选择项
        model.addAttribute("book", new Book());
        return "BookAddForm";
    }

    @RequestMapping(value = "/book_edit/{id}")
    public String editBook(Model model, @PathVariable int id) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);//制造下拉选单内的选择项
        Book book = bookService.get(id);
        model.addAttribute("book", book);
        return "BookEditForm";
    }
    
    @RequestMapping(value = "/book_delete/{id}")
    public String deleteBook(Model model, @PathVariable int id) {
        String result = bookService.delete(id);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_save")
    public String saveBook(@ModelAttribute Book book) {//此处的注解表示由前端表单绑定模型传入,需要和前台的表单内的modelattribute属性同名
        bookService.save(book);
        return "redirect:/book_list";//删除之后重定向回到列表
    }

    @RequestMapping(value = "/book_update")
    public String updateBook(@ModelAttribute Book book) {//此处的注解表示由前端表单绑定模型传入，需要和前台的表单内的modelattribute属性同名
        bookService.update(book);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_list")
    public String listBooks(Model model) {
        logger.info("book_list");
        List<Book> books = bookService.getAllBooks();//遍历当前list中的所有书籍
        model.addAttribute("books", books);
        return "BookList";
    }
}