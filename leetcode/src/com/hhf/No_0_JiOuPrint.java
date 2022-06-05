package com.hhf;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class No_0_JiOuPrint{
    private static volatile int i =0;
    private static Object monitor = new Object();
    private static ThreadPoolExecutor poolExecutor= new ThreadPoolExecutor(
            2,2,10L,TimeUnit.SECONDS,new LinkedBlockingQueue<>() );

    public static void main(String[] args) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (monitor){
                        if ((i &1) ==0){
                            i++;
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(i);
                        }
                        try {
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();*/
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (monitor){
                        if ((i &1) ==0){
                            i++;
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(i);
                        }
                        try {
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        poolExecutor.execute(runnable);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //或者全部使用monitor
                    synchronized (monitor){
                        if ((i &1) ==1){
                            i++;
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(i);
                        }
                        try {
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        ReentrantLock reentrantLock = new ReentrantLock(true);
    }
}

