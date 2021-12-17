package com.hhf.spring5.service;

import com.hhf.spring5.dao.BookDao;
import com.hhf.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    //注入dao
    @Autowired
    @Qualifier(value = "bookImpl")
    private BookDao bookDao;

    //添加方法
    public void addBook(Book book){
        bookDao.add(book);
    }
    public void updateBook(Book book){
        bookDao.updateBook(book);
    }
    public void deleteBook(String id){
        bookDao.delete(id);
    }
    public int findCount(){
        return bookDao.selectCount();
    }
    //查询返回对象
    public Book findOne(String id){
        return bookDao.findBookInfo(id);
    }

    //查询返回集合
    public List<Book> findAll(){
        return bookDao.findAllBook();
    }
    //批量添加
    public void batchAdd(List<Object[]> objects){
         bookDao.batchAddBook(objects);
    }
    //批量修改
    public void batchUpdate(List<Object[]> objects){
        bookDao.batchUpdateBook(objects);
    }

}
