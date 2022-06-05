package com.offer;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public  class Test2 {
/*    static class Te{
        static Te n1 = new Te();
        Te n2 = new Te();
    }*/

    public static void main(String[] args) {
       // Te te= new Te();
//        System.out.println(te.n2);
        int A = 1;
        Integer B = 1;
        System.out.println(A == B);
        Map<String,Integer> map=new HashMap<>();
        map.put("a",1);
        map.put("c",3);
        map.put("b",5);
        map.put("f",7);
        map.put("e",6);
        map.put("d",8);
        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list,(a,b)->{
            return  a.getValue()-b.getValue();
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getValue());
        }
    }
}
