package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Person person =new Student("张三");
        InvocationHandler handler = new StuInvocationHandle<Person>(person);
        Person stuProxy =(Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class<?>[]{Person.class},handler);
        stuProxy.giveMoney();
        TimeUtil.start();
        int[][] numsTwo =new int[10000][10000];
        TimeUtil.end(numsTwo);
        TimeUtil.start();
        int[] nums =new int[10000];
        TimeUtil.end(numsTwo);
    }
}
