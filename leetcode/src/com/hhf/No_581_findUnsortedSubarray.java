package com.hhf;

import java.util.Arrays;

/**
 * @author HP
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 *
 * 方法一：排序，先排序后，再将数据依次比较找到左端和右端，求出大小
 *
 * 方法二：
 * 数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，但满足最小值大于左段的最大值，最大值小于右段的最小值。
 * 从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，
 * 我们要求的end就是遍历中最后一个小于max元素的位置；左段和右段因为max是始终小于nums[i]的，所以max在不断改变而，end是不改变的
 * 同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都是大于min的，要求的begin也就是最后一个大于min元素的位置。
 *
 */
public class No_581_findUnsortedSubarray {


    public int findUnsortedSubarray(int[] nums) {
        if (isSorted(nums)){
            return 0;
        }
        int n =nums.length;
        int[] newNums = new int[n];
        System.arraycopy(nums,0,newNums,0,n);
        Arrays.sort(newNums);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(newNums));
        int res =0;
        int left=0,right =n-1;
        while (nums[left]==newNums[left]){
            left++;
        }
        while (nums[right] == newNums[right]){
            right--;
        }
        res = right-left+1;
        return res;
    }
   public boolean isSorted(int[] nums){
       for (int i = 1; i < nums.length; i++) {
           if (nums[i-1]>nums[i]){
               return false;
           }
       }
       return true;
   }

    public int findUnsortedSubarray2(int[] nums) {
        if (isSorted(nums)){
            return 0;
        }
        int n =nums.length;
        int res =0;
        int left=0,right =-1;
        int max = nums[0],min =nums[n-1];
        for (int i = 0; i < n; i++) {
            if (nums[i]<max){
                right =i;
            }else {
                max = nums[i];
            }
        }
        for (int j = n-1; j >=0 ; j--) {
            if (nums[j]>min){
                left = j;
            }else {
                min = nums[j];
            }
        }
        res = right-left+1;
        return res;
    }
}
