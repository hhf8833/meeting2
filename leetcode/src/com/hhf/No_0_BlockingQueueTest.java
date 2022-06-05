package com.hhf;

import java.util.concurrent.*;

public class No_0_BlockingQueueTest {

    private int maxCount =10;
    private BlockingQueue<String> product = new LinkedBlockingQueue<>(maxCount);
    public void produce(String p){
        try {
            product.put(p);
            System.out.println("放入一个产品,总库存为"+product.size());
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    public String  consume(){
        String res =null;
        try{
            res = product.take();
            System.out.println("消费了一个产品，总库存为"+product.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public class producer implements Runnable{


        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                produce("产品"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public class consumer implements Runnable{


        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                String consume = consume();
                System.out.println("消耗了产品"+consume);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        No_0_BlockingQueueTest tes = new No_0_BlockingQueueTest();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,4,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        //poolExecutor.execute(tes.new producer());
        poolExecutor.execute(tes.new producer());
        poolExecutor.execute(tes.new consumer());
        poolExecutor.execute(tes.new consumer());

    }
}
