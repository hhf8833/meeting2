package com.offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class No_56_singleNumbers {
    public static int[] singleNumbers(int[] nums) {
        int x=0,y=0,m=1 ,n=0;
        //对数组进行异或，相同值抵消 找到两个不同的结果(a^b)
        for(int num : nums){
            n ^=num;
        }
        //找出n为1的位数
        while((n & m) !=1){
            m <<= 1;
        }
        //根据为1的位数对数组进行划分两个子数组，两个子数组异或的结果即为最终结果
        for(int num : nums){
            if((m & num )!= 0){
                x ^=num;
            }else{
                y ^=num;
            }
        }
        return new int[]{x,y};
    }

    @Test
    public void test(){
        int[] ints = singleNumbers(new int[]{1, 2, 5, 2});
        System.out.println(Arrays.toString(ints));
    }
}
