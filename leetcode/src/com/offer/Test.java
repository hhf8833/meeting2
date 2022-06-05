package com.offer;

import com.hhf.No_0_Singleton;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    static No_0_Singleton No = new No_0_Singleton();
    No_0_Singleton no1 = new No_0_Singleton();
    public static void main(String[] args) {
/*        int ARRAYLENGTH = 8;  //指定数组长度
        int a[] = new int[ARRAYLENGTH];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数组，并以回车结束：");
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        //直接打印数组a出来的是数组的首地址，必须用toString方法
        System.out.println("数组a为:" + Arrays.toString(a));*/
        String s = "a";
        s.trim();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = new char[]{'a','b','b','v'};
        String s1 = String.valueOf(chars);
        System.out.println(s1);
        int[] nums = new int[]{1,5,3,6,3,2,8};
        Arrays.sort(nums);
        for (int i = 0,j=nums.length-1; i < j; i++,j--) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;

        }
        System.out.println(Arrays.toString(nums));
/*        //ConcurrentHashMap<Integer,Integer> map = new ConcurrentLinkedDequeHashMap<>(3);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        for (Map.Entry<Integer, Integer> entry:
        map.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }*/
        System.out.println(Test.No);
        System.out.println(Test.No);

    }

}

