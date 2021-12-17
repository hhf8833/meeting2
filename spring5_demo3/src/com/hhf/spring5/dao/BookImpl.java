package com.hhf.spring5.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookImpl implements BookDao{
    //注入jdbctemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into t_user values(?,?,?)";
        Object[] args = {book.getBookId(),book.getBookname(),book.getBstatus()};
        int update =jdbcTemplate.update(sql,args);
        System.out.println(update);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update t_user set username=?,ustatus=? where user_id=?";
        Object[] args = {book.getBookname(),book.getBstatus(),book.getBookId()};
        int update = jdbcTemplate.update(sql, args);
        System.out.println("updateBook:"+update);

    }

    @Override
    public void delete(String id) {
        String sql = "delete from t_user where user_id=?";
        int update = jdbcTemplate.update(sql, id);
        System.out.println("删除:"+update);
    }

    @Override
    public int selectCount() {
        String sql = "select count(*) from t_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public Book findBookInfo(String id) {
        String sql = "select * from t_user where user_id=?";
        //RowMapper是一个接口 ，BeanPropertyRowMapper是他的实现类完成数据的封装
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
        return book;
    }

    @Override
    public List<Book> findAllBook() {
        String sql = "select * from t_user ";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return books;
    }

    @Override
    public void batchAddBook(List<Object[]> objects) {
        String sql ="insert into t_user values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, objects);
        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void batchUpdateBook(List<Object[]> objects) {
        String sql = "update t_user set username=?,ustatus=? where user_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, objects);
        System.out.println(Arrays.toString(ints));
    }

}
