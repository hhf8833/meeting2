package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author HP
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 方法一
 * //采用优先队列，遇到队列头的为元素不在当前数组范围的话就弹出直到队首为范围内，
 * 即便队列中有不在范围内的也没事，只要它不在堆头，那么就不会影响数组值，而即便在头我们有判断会对其弹出
 *
 * 方法二；单调递减队列
 * 元素遍历，队列尾元素比当前遍历的元素小或等的话就将其依次出队，直到没有元素或者大于时候，将其入队
 * 之后检查队首元素是否在有效范围内，如果没有就出队，再看当前遍历的位置是否已经超过k了，超过的话就将当前队首（前面判断过肯定在范围内）写入结果数组中
 */
public class No_239_maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> pqueue = new PriorityQueue<>( (pair1,pair2)->{
            return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
        });
        int n = nums.length;
        int[] res =new int[n-k+1];
        for (int i = 0; i < k; i++) {
            pqueue.add(new int[]{nums[i],i});
        }
        res[0]=pqueue.peek()[0];
        for (int i = k; i < n; i++) {
            pqueue.add(new int[]{nums[i],i});
            while (pqueue.peek()[1]<=(i-k)){
                pqueue.poll();
            }
            res[i-k+1]=pqueue.peek()[0];
        }
        return res;
    }
    public static  int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> queue =new LinkedList();
        int n = nums.length;
        int[] res =new int[n-k+1];
        for (int i = 0; i < n; i++) {
            //只要队列尾部比遍历数小
            while (!queue.isEmpty() && nums[queue.peekLast()] <=nums[i]){
                queue.pollLast();
            }
            //不小就加入形成单调递减队列
            queue.addLast(i);
            //队首没在范围内就不要了
            if (queue.peekFirst()<=i-k){
                queue.pollFirst();
            }
            //如果当前的已经超过k的话就要将当前队列头加入结果数组了
            if (i+1>=k){
                res[i-k+1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums =new int[]{1,3,-1,-3,5,3,6,7};
        int [] res = maxSlidingWindow2(nums,3);
        System.out.println(Arrays.toString(res));
    }
}
