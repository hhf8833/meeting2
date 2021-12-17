package test1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method " + method.getName());
        return result;
    }
    public static void main(String[] args){
//        MyThread myThread = new MyThread();
//        new Thread(myThread).start();
        new Thread(){
            @Override
            public void run() {
                int a;
                while (true){
                    System.out.println("a");
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        System.out.println("111");
                    }
                }
            }
        }.start();
        try (BufferedReader bufferReader=new BufferedReader(new InputStreamReader(System.in))){

            while (true){

                    String s = bufferReader.readLine();
                    System.out.println(s);
                    if (s.equals("exit"))
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
        }

    }
    

}
