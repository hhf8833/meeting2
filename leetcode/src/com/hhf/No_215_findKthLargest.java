package com.hhf;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author HP
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 第k大元素所在位置就是快排排序数组时的第n-k的位置
 *
 * 方法一：快排改编
 * 这里优化是 在while true的时候，里面开始位置结束位置要换
 * 还有优化空间就是随机化
 *
 * 方法二：优先队列（默认最小堆，队列头为最小值）
 *  维护一个k值大小的最小堆，当堆顶比新遍历的元素大，则下一个，小的话就将该元素加入堆中，如果堆满则要删去堆顶
 */
public class No_215_findKthLargest {
    private static Random random = new Random(System.currentTimeMillis());
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int resIndex = n-k;
        int start = 0;
        int end = n-1;
        while (true){
            int index =partition(nums,start,end);
            if (index==resIndex){
                return nums[resIndex];
            }else if(index<resIndex){
                start=index+1;
            }else {
                end = index-1;
            }
        }
    }
    public int partition(int[] nums , int start ,int end ){
        //随机化选择pivot
/*        if (end <start){
            int randomIndex = random.nextInt(end-start)+start+1;
            int temp = nums[start];nums[start]= nums[randomIndex];nums[randomIndex]=temp;
        }*/
        //这种随机化速度比上面的更快
        int randomIndex = (start+end)/2;
        int temp = nums[start];nums[start]= nums[randomIndex];nums[randomIndex]=temp;
        int j = start;
        int pivot = nums[start];
        for (int i = start+1; i <= end; i++) {
            if (nums[i]<pivot){
                j++;
                 temp = nums[i];nums[i]= nums[j];nums[j]=temp;
            }
        }
         temp = nums[j];nums[j]= nums[start];nums[start]=temp;
        return j;
    }
    //优先队列
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num :
                nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

}
