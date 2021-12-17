package com.hhf.controller;

import com.hhf.pojo.Books;
import com.hhf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")//因为接口可能有多个实现类,如果用auto的话系统可能不知道是哪一个
    private BookService bookService;

    //查看全部图书
    @RequestMapping(value = "/allBook",method = RequestMethod.GET)
    public String listBook(Model model){
        List<Books> books = bookService.queryAllBooks();
        model.addAttribute("books",books);
        return "allbook";
    }
    //增加图书
    @RequestMapping(value = "/allBook" , method = RequestMethod.POST)
    public String addBook(Books book){
        bookService.addBook(book);
        return "redirect:/book/allBook";
    }
    //删除图书
    @RequestMapping(value = "/allBook/{id}",method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") Integer id ){
        bookService.deleteBook(id);
        return "redirect:/book/allBook";
    }
    //修改图书是基于 查询之后在此基础上进行修改
    @RequestMapping(value = "/allBook",method = RequestMethod.PUT)
    public String updateBook(Books book){
        System.out.println(book);
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }
    //查询图书
    @RequestMapping(value = "/allBook/{id}",method = RequestMethod.GET)
    public String queryBookById(@PathVariable("id") Integer id,Model model){
        Books book = bookService.queryBookById(id);
        model.addAttribute("book",book);
        return "updateBook";
    }
}
