package com.stylefeng.guns.rest.common;

public class CurrentUser {
    //InheritableThreadLocal  即便线程切换也会保存之前的数据
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }

    public static String getCurrentUser(){
        return threadLocal.get();
    }
}
