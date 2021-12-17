package com.hhf.spring5;

import java.util.SplittableRandom;

/**
 * @author HP
 */
public class Book {
    private String bname;
    private String bauthor;
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    //创建属性对应的 set 方法
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
