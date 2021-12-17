package com.hhf.spring5.aopanno;

import org.springframework.stereotype.Component;

/**
 * @author HP
 * 被增强类
 */
@Component
public class User {
    public void add() {
        System.out.println("add.......");
    }
}
