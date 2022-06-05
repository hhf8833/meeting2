package com.hhf;

//泛型类
public class No_0_TEKV <T>{
    private  T key;
    public No_0_TEKV(T key){
        this.key = key;
    }
    //泛型接口
    public interface Gen<T>{
        public T method();
    }

    //不指定类型
    class GenImpl1<T> implements Gen<T>{

        @Override
        public T method() {
            return null;
        }
    }

    //指定类型
    class GenImpl2 implements Gen<String>{

        @Override
        public String method() {
            return null;
        }
    }

    //泛型方法
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}
