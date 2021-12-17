package com.hhf.spring5.bean;

public class Orders {
    private String oname;
    public  Orders(){
        System.out.println("1.调用无参构造方法，创建bean实例");
    }
    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("2. 调用set方法设置属性"+"-----"+oname);
    }
    //创建初始化方法
    public void  initMethod(){
        System.out.println("3. 调用bean的初始化方法");
    }
    //销毁方法
    public void  destryMethod(){
        System.out.println("5. 调用bean的销魂方法");
    }
}
