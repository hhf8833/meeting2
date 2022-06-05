package com.hhf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class No_15_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for(int i =0;i<nums.length-2;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int left =i,right = nums.length-1;
            while(left < right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum ==0){
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(list);
                }else if(sum >0){
                    while(right > left && nums[right]==nums[right-1]){
                        right--;
                    }
                }else{
                    while(right > left && nums[left]==nums[left + 1]){
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
