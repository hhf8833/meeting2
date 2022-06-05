package com.hhf;

import java.util.HashSet;
import java.util.Set;

public class No_0_Singleton {

    private static volatile No_0_JiOuPrint mySingleton;

    public static No_0_JiOuPrint getMySingleton(){
        if (mySingleton ==null){
            synchronized (No_0_Singleton.class){
                //这里再次判断是否为空是因为防止AB线程同时在加锁前发现mySingleton是空，从而多次进行实例化
                if (mySingleton == null){
                    //懒汉模式
                    return mySingleton = new No_0_JiOuPrint();
                }
            }

        }
        return mySingleton;
    }
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
    // 饿汉模式
    public static final class Singleton {
        private static Singleton instance=new Singleton();// 自行创建实例
        private Singleton(){}// 构造函数
        public static Singleton getInstance(){// 通过该函数向整个系统提供实例
            return instance;
        }
    }
    // 懒汉模式
    public static final class Singleton2 {
        private static Singleton instance= null;// 不实例化
        private Singleton2(){}// 构造函数
        public static Singleton getInstance(){// 通过该函数向整个系统提供实例
            if(null == instance){// 当 instance 为 null 时，则实例化对象，否则直接返回对象
                instance = new Singleton();// 实例化对象
            }
            return instance;// 返回已存在的对象
        }
    }
}
