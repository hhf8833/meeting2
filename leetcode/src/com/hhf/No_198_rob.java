package com.hhf;

/**
 * @author HP
 * 198. 打家劫舍   213 337
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 动态规划，打劫第i家的时候，是根据第i-1 i-2 家决定的，dp[i]=max(num[i]+dp[i-2],dp[i-1])
 * 注意这里初始化问题，要用dp【0】表示第零家即还没开始打劫，然后dp 1.。。。n代表 n家
 * 因此公式应该是dp[i]=max(num[i-1]+dp[i-2],dp[i-1])，因为num是从0开始
 */
public class No_198_rob {
    /**
     * 该方法用了动态规划但是没有优化空间
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n=nums.length;
        if (n==0){
            return  0;
        }

        int[] dp =new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i]=Math.max(nums[i-1]+dp[i-2],dp[i-1]);
        }
        return dp[n];
    }

    /**
     * 优化空间，因为每次dp都只有用了i的后一个和前一个，其他的之后就没有用了
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int n=nums.length;
        if (n==0){
            return  0;
        }

        int dp1 = 0;
        int dp2 = nums[0];
        for (int i = 2; i <= n; i++) {
            int temp =dp2;
            dp2=Math.max(nums[i-1]+dp1,dp2);
            dp1=temp;
        }
        return Math.max(dp1,dp2);
    }
}
