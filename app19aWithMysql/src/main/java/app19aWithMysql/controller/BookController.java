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
    private BookService bookService;//����ǰ���һ��ʵ��ע�뵽��������

    private static final Log logger = LogFactory.getLog(BookController.class);

    @RequestMapping(value = "/book_input")
    public String inputBook(Model model) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);//��������ѡ���ڵ�ѡ����
        model.addAttribute("book", new Book());
        return "BookAddForm";
    }

    @RequestMapping(value = "/book_edit/{id}")
    public String editBook(Model model, @PathVariable int id) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);//��������ѡ���ڵ�ѡ����
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
    public String saveBook(@ModelAttribute Book book) {//�˴���ע���ʾ��ǰ�˱���ģ�ʹ���,��Ҫ��ǰ̨�ı��ڵ�modelattribute����ͬ��
        bookService.save(book);
        return "redirect:/book_list";//ɾ��֮���ض���ص��б�
    }

    @RequestMapping(value = "/book_update")
    public String updateBook(@ModelAttribute Book book) {//�˴���ע���ʾ��ǰ�˱���ģ�ʹ��룬��Ҫ��ǰ̨�ı��ڵ�modelattribute����ͬ��
        bookService.update(book);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_list")
    public String listBooks(Model model) {
        logger.info("book_list");
        List<Book> books = bookService.getAllBooks();//������ǰlist�е������鼮
        model.addAttribute("books", books);
        return "BookList";
    }
}