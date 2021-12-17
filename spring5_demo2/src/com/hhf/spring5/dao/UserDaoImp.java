package com.hhf.spring5.dao;

import org.springframework.stereotype.Repository;

/**
 * @author HP
 */
@Repository
public class UserDaoImp implements UserDao{
    @Override
    public void add() {
        System.out.println("dao add ...");
    }
}
