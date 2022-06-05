package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 */
public class No_698_canPartitionKSubsets {
   /* //dfs，数字与桶的故事，以数字的角度，想要将这些数字依次尝试加入到桶中
       //该方法的时间复杂度太高，过不了
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums.length <k  ){
            return false;
        }
        int n = nums.length,sum=0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
        }
        //数字不能全部放入桶中
        if (sum %k !=0){
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0,j = n-1; i <j ; i++,j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j]=temp;
        }
        // k 个桶（集合），记录每个桶装的数字之和
        int[] bucket = new int[k];
        // 理论上每个桶（集合）中数字的和
        int target = sum / k;
        // 穷举，看看 nums 是否能划分成 k 个和为 target 的子集
        return backtrack(nums, 0, bucket, target);
    }

    private static boolean backtrack(
            int[] nums, int index, int[] bucket, int target) {

        if (index == nums.length) {
            // 检查所有桶的数字之和是否都是 target
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            // nums 成功平分成 k 个子集
            return true;
        }

        // 穷举 nums[index] 可能装入的桶
        for (int i = 0; i < bucket.length; i++) {
            // 剪枝，桶装装满了
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            // 将 nums[index] 装入 bucket[i]
            bucket[i] += nums[index];
            // 递归穷举下一个数字的选择
            if (backtrack(nums, index + 1, bucket, target)) {
                return true;
            }
            // 撤销选择
            bucket[i] -= nums[index];
        }

        // nums[index] 装入哪个桶都不行
        return false;
    }*/
   //dfs，数字与桶的故事，以桶的角度，放满第一个桶再进行下一个
   public static boolean canPartitionKSubsets(int[] nums, int k) {
       if(nums.length <k  ){
           return false;
       }
       int n = nums.length,sum=0;
       for (int i = 0; i < n; i++) {
           sum+=nums[i];
       }
       //数字不能全部放入桶中
       if (sum %k !=0){
           return false;
       }
       Arrays.sort(nums);
       for (int i = 0,j = n-1; i <j ; i++,j--) {
           int temp = nums[i];
           nums[i] = nums[j];
           nums[j]=temp;
       }
       // k 个桶（集合），记录每个桶装的数字之和
       int[] bucket = new int[k];
       // 理论上每个桶（集合）中数字的和
       int target = sum / k;

       // 穷举，看看 nums 是否能划分成 k 个和为 target 的子集
       return backtrack(nums,0, bucket,k,0, target,0);
   }
    static Map<Integer,Boolean> emo = new HashMap<>();
    //used本来是利用hashmap将曾经遍历到的字符串不合适的给记录下来，但是string耗时，所以使用位运算来解决
    private static boolean backtrack(int[] nums,int start, int[] bucket, int k, int used, int target, int curSum) {
        if (k==0){
            return true;
        }
        if (curSum==target){
            //该桶装满了之后就要去装下一个桶了，因为有used在，所以不用担心nums会重复
            boolean res = backtrack(nums, 0,bucket, k - 1, used, target, 0);
            emo.put(used,res);
            return res;
        }
        //现在需要依次将nums加入进去看行不行
        for (int i = start; i < nums.length; i++) {
            //剪枝，看当前这个数被用过没
            if (((used >>i) &1) ==1){
                continue ;
            }
            curSum+=nums[i];
            used |= 1<<i;
            if (backtrack(nums,i+1,bucket,k,used,target,curSum)){
                return true;
            }
            used ^= 1<<i;
            curSum-=nums[i];
        }


        // nums[index] 装入哪个桶都不行
        return false;
    }

    @Test
    public void test(){
        int[] nums = new int[]{3,3,10,2,6,5,10,6,8,3,2,1,6,10,7,2}  ;
        int k =6;
        System.out.println(canPartitionKSubsets(nums,k));
    }
}
