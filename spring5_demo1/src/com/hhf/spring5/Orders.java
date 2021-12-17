package com.hhf.spring5;

/**
 * @author HP
 * 有属性类的构造方法
 */
public class Orders {
    private String oname;
    private String address;

    public Orders(String oname, String address) {
        this.oname = oname;
        this.address = address;
    }
}
