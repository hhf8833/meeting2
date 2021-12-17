package com.hhf;

/**
 * @author HP
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * 分为第i天有股票和没股票
 * 没股票【0】 前一天买的今天卖了，或者就是本来就没有，找到其最大的  即为最大利益dp[i][0] = max(dp[i-1][1]+price[i],dp[i-1][0])
 * 有股票【1】 昨天就有了今天继承，昨天没有今天刚买的 dp[i][1] = max(dp[i-1][1],dp[i-1][0]-price[i])
 *
 *
 * 法二：
 * 因为每次只用了dp的前一项，所以可以只保留前一项的而不需要保存所有的
 */
public class No_122_maxProfit {
/*    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1]= -prices[0];
        int dp0=0,dp1=-prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+prices[i],dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);

        }
        return dp[n-1][0];
    }*/
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0=0,dp1=-prices[0];
        for (int i = 1; i < n; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;


        }
        return dp0;
    }
}
