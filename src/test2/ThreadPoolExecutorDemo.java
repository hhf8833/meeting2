package test2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE = 5 ;
    private  static  final int MAX_POOL_SIZE = 10 ;
    private  static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyRunnable("名字"+i);
            System.out.println(worker.toString());
            executor.execute(worker);
        }
        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");    //系统的最大空间
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");  //系统的空闲空间
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");  //当前可用的总空间
        //该句注释掉之后，线程池不会自动关闭
        executor.shutdown();
        while (!executor.isTerminated()){

        }
        System.out.println("所有的线程都被关闭");
    }
}
