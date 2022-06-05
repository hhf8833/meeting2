package com.hhf;

/**
 * @author HP
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 双指针或者单调栈
 */
public class No_42_trap {
    public int trap(int[] height) {
        int lMax=height[0],rMax=height[height.length-1];
        int left = 0,right = height.length-1;
        int res =0;
        while(left<right){
            lMax = Math.max(lMax,height[left]);
            rMax = Math.max(rMax,height[right]);
            if(lMax < rMax){
                res += lMax- height[left];
                left++;
            }else{
                res += rMax- height[right];
                right--;
            }
        }
        return res;
    }
}
