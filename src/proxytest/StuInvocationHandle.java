package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class StuInvocationHandle<T> implements InvocationHandler {

    T target;
    public  StuInvocationHandle(T target){
        this.target =target;
    }
    //重写，对person的方法进行增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行"+method.getName()+"方法，传递的参数"+ Arrays.toString(args));
        TimeUtil.start();
        method.invoke(target,args);
        TimeUtil.end(method);
        return null;
    }
}
