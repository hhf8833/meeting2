package leetcode;

/**
 * @author HP
 *    123. 买卖股票的最佳时机 III
 *     给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 *     设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 *     注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */
public class No_123_maxProfit {
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
