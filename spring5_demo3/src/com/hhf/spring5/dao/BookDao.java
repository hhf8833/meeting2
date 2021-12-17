package com.hhf.spring5.dao;

import com.hhf.spring5.entity.Book;

import java.awt.print.Book;
import java.util.List;

public interface BookDao {
    void add(Book book);

    void updateBook(Book book);

    void delete(String id);

    int selectCount();

    Book findBookInfo(String id);

    List<Book> findAllBook();

    void batchAddBook(List<Object[]> objects);

    void batchUpdateBook(List<Object[]> objects);
}

