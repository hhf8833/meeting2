package test1;

public class MyThread implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("------");
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("111");
            }
        }
    }
}
