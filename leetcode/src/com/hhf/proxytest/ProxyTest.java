package com.hhf.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;

public class ProxyTest {
    public static void main(String[] args) {
        Person person =new Student("张三");
        //jdk动态代理，要代理的类必须要实现一个接口才行，这是jdk代理的弊端
        InvocationHandler handler = new StuInvocationHandle<Person>(person);
        /*
        loader :类加载器，用于加载代理对象。
        interfaces : 被代理类实现的一些接口；
        h : 实现了 InvocationHandler 接口的对象；
         */
        Person stuProxy =(Person) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class<?>[]{Person.class},handler);
        stuProxy.giveMoney();
        TimeUtil.start();
        int[][] numsTwo =new int[10000][10000];
        TimeUtil.end(numsTwo);
        TimeUtil.start();
        int[] nums =new int[10000];
        TimeUtil.end(numsTwo);

        System.out.println( "A man, a plan, a canal: Panama".toLowerCase());
        System.out.println( "0P".toLowerCase());
        System.out.println( "A man, a plan, a canal: Panama".toUpperCase());
        StringBuilder sb = new StringBuilder();


    }
}
