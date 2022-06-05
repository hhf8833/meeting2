package com.hhf;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 969. 煎饼排序
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 *
 * 一次煎饼翻转的执行过程如下：
 *
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 *
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 */
public class No_969_pancakeSort {
    List<Integer> list = new LinkedList<>();
    public List<Integer> pancakeSort(int[] arr) {
        int n =arr.length;
        int maxValue = Integer.MIN_VALUE,maxIndex = Integer.MIN_VALUE;
        //注意仅剩下一块的时候是不用再反转了
        for(int i =n-1;i>0;i--){
            //寻找最大值
            for(int j = 0;j<=i;j++){
                if(arr[j]>maxValue){
                    maxValue = arr[j];
                    maxIndex = j;
                }
            }
            if (maxIndex == i){
                continue;
            }
            list.add(maxIndex+1);
            //第一次反转
            reverse(arr,0,maxIndex);
            //第二次反转
            list.add(i+1);
            reverse(arr,0,i);
        }
        return list;
    }
    public void reverse(int[] arr ,int start,int end){
        while(start<end){
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }
}
