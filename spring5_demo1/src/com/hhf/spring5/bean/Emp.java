package com.hhf.spring5.bean;

/**
 * @author HP
 * 员工类
 */
public class Emp {
    private String ename;
    private String egender;
    //员工属于部门
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setEgender(String egender) {
        this.egender = egender;
    }


}
