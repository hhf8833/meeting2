package com.hhf.classification.qianzhuihe;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class meituan1 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = sc.nextInt();
//        }
//        System.out.println(Arrays.toString(nums));
//        int res = result(nums);
//        System.out.println(res);
        Map<Integer,Integer> map = new TreeMap<>();
        map.put(5,2);
        map.put(3,2);
        map.put(2,2);
        map.put(7,2);
        for (Map.Entry<Integer,Integer> entry:
             map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }


    public static int result(int[] nums){
        int n = nums.length, res=0;

        for (int i = 0; i < n; i++) {
            int x=1;
            for (int j = i; j < n; j++) {
                x*=nums[i];
                if (x == 1 ){
                    res++;
                }
            }
        }
        return res;
    }
}
