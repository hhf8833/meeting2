package com.hhf.classification.qianzhuihe;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 *
 *方法一：暴力法，以数组的每一位向后列举找出所有的
 * 
 * 法二：
 * 前缀和加hash表，要比第437题的前缀和简单，
 */
public class No_560_subarraySum {
    public static int subarraySum(int[] nums, int k) {
        int res = 0;
        int sum =0;
        int n = nums.length;
        //k为前缀和，v为出现次数
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
            res+=map.getOrDefault(sum-k,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums =new int[]{3,4,7,2,-3,1,4,2};
        int k = 7;
        int res = subarraySum(nums,k);
        System.out.println(res);
    }
}
