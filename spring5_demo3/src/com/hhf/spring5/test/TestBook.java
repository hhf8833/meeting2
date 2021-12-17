package com.hhf.spring5.test;

import com.hhf.spring5.entity.Book;
import com.hhf.spring5.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestBook {
    @Test
    public void testJdbc(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean1.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
/*        Book book = new Book();
        //book.setBookId("1");
        book.setBookname("java");
        book.setBstatus("a");
        bookService.addBook(book);*/
/*        book.setBookId("1");
        book.setBookname("javaupdate");
        book.setBstatus("acac");
        bookService.updateBook(book);*/

       // bookService.deleteBook("2");

        int count =bookService.findCount();
        System.out.println("表中有"+count+"条数据");

        Book book2 = bookService.findOne("1");
        System.out.println(book2);

        List<Book> all = bookService.findAll();
        System.out.println(all);
    }
    @Test
    public void testJdbc2(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean1.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
/*        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {"4","python","1111"};
        Object[] o2 = {"5","c++","2222"};
        Object[] o3 = {"6","go","3333"};
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);
        bookService.batchAdd(batchArgs);*/
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {"go111","1111","4"};
        Object[] o2 = {"c++111","2222","5"};
        Object[] o3 = {"go111","3333","3"};
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);
        bookService.batchUpdate(batchArgs);
    }
}
