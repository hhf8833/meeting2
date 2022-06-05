package com.hhf;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class No_0_ScheduledTask {
    public static void main(String[] args) {

        TimerTask repeatedTask = new TimerTask() {
            @Override
            @SneakyThrows
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };

        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:"+new Date());
        /**
         * scheduleAtFixedRate(commod,initialDelay,period,unit)
         *
         * initialDelay是说系统启动后，需要等待多久才开始执行。
         *
         * period为固定周期时间，按照一定频率来重复执行任务。
         *
         * scheduleWithFixedDelay  必须等待上一个任务结束才开始计时period。
         */
        respScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);//2000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task run:"+new Date());
            }
        },2,60, TimeUnit.SECONDS);}
}
