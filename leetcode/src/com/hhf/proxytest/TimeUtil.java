package com.hhf.proxytest;

import java.lang.reflect.Method;

public class TimeUtil {
    private static ThreadLocal<Long> tl =new ThreadLocal<>();
    public static void start(){
        tl.set(System.currentTimeMillis());
    }
    public static void end(Method method){
        long finish = System.currentTimeMillis();
        System.out.println(method.getName() +"方法耗时"+(finish-tl.get())+"ms");
    }
    public static void end(Object method){
        long finish = System.currentTimeMillis();
        System.out.println(method.getClass().getName() +"方法耗时"+(finish-tl.get())+"ms");
    }
}
