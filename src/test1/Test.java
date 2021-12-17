package test1;

import test2.TargetObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException
            , InvocationTargetException, NoSuchFieldException {
        System.out.println("11111");
        Class<?> taget = Class.forName("test2.TargetObject");
        TargetObject targetObject = (TargetObject) taget.newInstance();
        Method[] methods =taget.getDeclaredMethods();
        for (Method method :
                methods) {
            System.out.println(method.getName());
        }
        //调用public方法，先获得类对象，在类对象实例里面找到对应的方法并获取，调用的时候要把把实例化过的该类对象传入
        Method method =taget.getDeclaredMethod("publicMethod", String.class);
        method.invoke(targetObject,"javaAAA");
        //对指定参数修改
        Field field = taget.getDeclaredField("value");
        //取消安全检查机制
        field.setAccessible(true);
        field.set(targetObject,"javaAAA");

        Integer i1 = new Integer(40);

        Integer i2 = new Integer(40);
        System.out.println(i1.equals(i2));
    }

/*    public void test(final int b) {
         int a = 10;
        new Thread(){
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(b);
            };
        }.start();
    }*/

    public static AtomicInteger race =new AtomicInteger(0);
        public static  void increase(){
            //race++ 并非有原子操作，取值 加一，写值
            race.getAndIncrement();
        }
}