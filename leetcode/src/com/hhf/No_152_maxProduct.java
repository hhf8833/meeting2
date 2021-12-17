package com.hhf;

import java.util.Arrays;

public class No_152_maxProduct {
    public static int maxProduct(int[] nums) {
        if (nums.length ==0 ){
            return 0;
        }
        int temp = 1;
        int maxValue = Integer.MIN_VALUE;
        for(int i =0;i<nums.length;i++ ){
            int res;
            res= Math.max(nums[i] * temp,nums[i]);

            maxValue = Math.max(maxValue,res);
            temp=nums[i];

        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[]nums = new int[]{ -3,-1,-1};
        int[] maxF = new int[nums.length];
        System.arraycopy(nums,0,maxF,0,nums.length);
        int res = maxProduct(nums);
        System.out.println(Arrays.toString(maxF));
    }
}
