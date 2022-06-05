package com.hhf.classification.erfenfa;

/**
 * 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * 示例 1：
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 二分法：考虑x f(x) target
 */
public class No_857_minEatingSpeed {
    public int minEatingSpeed(int[] piles, int h) {
        //速度是自变量
        int left = 1,right=0;
        for (int i = 0; i < piles.length; i++) {
            right= Math.max(right,piles[i]);
        }
        //得到了速度的最大值right，就要考虑左边界还是右边界，因为速度越大，需要的f（x)时间就越少，所以是递减用左边界,
        //其次，不要盲目去套框架，因为是递减的，所以speedNeedTime(piles,mid)越大，速度是越小的，因此要让速度变大
        while (left <=right){
            int mid= left+(right-left)/2;
            if (speedNeedTime(piles,mid) <h){
                right = mid-1;
            }else if (speedNeedTime(piles,mid)> h ){
                left = mid+1;
            }else if (speedNeedTime(piles,mid) == h){
                right =mid-1;
            }
        }
        return left;
    }

    //当前速度需要多长时间
    public int speedNeedTime(int[] piles,int speed){
        int hours =0;
        for (int i = 0; i < piles.length; i++) {
            hours+=piles[i]/speed;
            if (piles[i] %speed !=0){
                hours++;
            }
        }
        return hours;
    }
}
