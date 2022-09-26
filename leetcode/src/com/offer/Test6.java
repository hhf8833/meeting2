package com.offer;


import java.util.ArrayList;
import java.util.List;

public class Test6 {
    static int num = 0;
    String name = "qqqqqq";
    static String name2 = "wwwwwwwwwww";
    static Test6 parentClass = new Test6();
    Test6(){
        System.out.println("这里是构造函数*************");
    }
    {
        System.out.println("name1:" + name);
        System.out.println("这里是块1============");
    }
    static {
        num += 1;
        System.out.println("parentClass.parentClass.parentClass.name:");
        System.out.println("这里是静态块*************" + num);
    }
    class Inn{
        public Inn(){
            System.out.println("sss");
        }
    }

    public static void main(String[] args) {
        Test6 test6 = new Test6();

    }


}
