package com.hhf.proxytest;

public class Student implements Person{

    private String stu;
    public Student(String stu){
        this.stu = stu;
    }
    @Override
    public void giveMoney() {

        System.out.println(stu+":上缴100元钱");
    }
}
