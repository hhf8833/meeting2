package com.hhf.spring5.entity;

public class Book {
    private String bookId;
    private String bookname;
    private String bstatus;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bstatus='" + bstatus + '\'' +
                '}';
    }
}
