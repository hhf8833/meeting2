package com.hhf.classification.dp.pakage;

/**
 * @author HP
 * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
 *
 * */
public class ZeroOnePakage {

    public int maxValue(int w,int n ,int[] wt,int[] val){
        int[][]dp = new int[n+1][w+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j-wt[i-1]<0){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-wt[i-1]]+val[i-1]);
                }
            }
        }
        return dp[n][w];
    }
}
