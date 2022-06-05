package com.hhf.classification.windows;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author HP
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class Offer59_maxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length ==0){
            return new int[0];
        }
        int n =nums.length;
        int[] res = new int[n-k+1];
        Deque<Integer> queue = new LinkedList<>();
        //Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            //map.put(i,nums[i]);
            while (!queue.isEmpty() && queue.peekFirst() < i-k+1){
                queue.pollFirst();
            }
            if (i>=k-1){
                res[i-k+1] =  nums[queue.peekFirst()];
            }
        }
        return res;
    }
    @Test
    public  void  test(){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k =3;
        maxSlidingWindow(nums,k);
    }
}
