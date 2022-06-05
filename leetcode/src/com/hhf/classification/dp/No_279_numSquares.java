package com.hhf.classification.dp;

/**
 * @author HP
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 * 动态规划的思想
 * 设置一个n+1大的dp数组，想要求i是由多少个平方和组成，要把前i-1需要的最小个数依次求出来放入dp，然后使用转移方程dp[i]=min(dp[i],dp[i-j*j]+1)
 * 其中min(dp[i],dp[i-j*j]+1)，dp[i]表示i这个数最坏的情况即全是1构成，j*j指1到j的平方和，与i的差则是看i-j*j位置的dp是需要几个，再加上1指的是把j*j这个数加上
 */
public class No_279_numSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i]=i;
            for (int j = 1; i-j*j >=0 ; j++) {
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}
