package leetcode;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HP
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 方法一 动态规划
 * 求第i个元素的最长子序列，求j∈[0,i)中所有nums【j】<nums[i]的dp【j】+1的最大值和dp【i】比较
 *
 * 方法二  动态规划+二分法  （比较迷，先不看）
 */
public class No_300_lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

}
