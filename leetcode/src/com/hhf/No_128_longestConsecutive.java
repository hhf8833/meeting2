package com.hhf;

import java.util.HashSet;

/**
 *128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *暴力法时间复杂度太高为n的平方，考虑使用hahs表的方法
 * 将数组放入hash表中，以当前的元素x为首，向后查找，如果存在x-1的则该元素不能使用，不是起始位置
 */
public class No_128_longestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        HashSet<Integer> hash = new HashSet<>();
        //将数据全放在hash里面
        for (int e :nums){
            hash.add(e);
        }
        int maxPath =Integer.MIN_VALUE;
        for (int x :hash){
            if (!hash.contains(x-1)){
                int y =x;
                while (hash.contains(y+1)){
                    y++;
                }
                maxPath = Math.max(maxPath,y-x+1);
            }
/*
            for (Iterator it= hash.iterator();
                it.hasNext();){
                 it.next();
            }
*/
        }
        return maxPath;
    }
}
