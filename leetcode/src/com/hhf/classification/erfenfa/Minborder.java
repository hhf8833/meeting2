package com.hhf.classification.erfenfa;

import javax.xml.bind.annotation.XmlInlineBinaryData;

/**
 * @author HP
 * 最小边界问题   右边界、最小左边界
 */
public class Minborder {

    public int rightborder(int[] nums,int target){
        //左右都闭区间
        int left =0, right =nums.length-1;
        //出去条件 left == right+1
        while (left <right){
            int mid = left+(right - left)/2;
            if (target < nums[mid]){
                right = mid;
            }else if (target>nums[mid]){
                left = mid+1;
            }else if (target== nums[mid]){
                //注意 ：相等的时候也+1了
                left=mid+1;
            }
        }
        //right 可能越界 target 0 ，12345  最后right在-1位置

        return left-1;
    }

    public int leftBorder(int[] nums,int target){
        int left =0, right =nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (target<nums[mid]){
                right = mid;
            }else if (target > nums[mid]){
                left = mid+1;
            }else if (target == nums[mid]){
                right =mid;
            }
        }
        return left;
    }
}
