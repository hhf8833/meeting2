package com.hhf.classification.qianzhuihe;

import java.util.Arrays;
import java.util.Scanner;

public class meituan1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(nums));
        int res = result(nums);
        System.out.println(res);
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
