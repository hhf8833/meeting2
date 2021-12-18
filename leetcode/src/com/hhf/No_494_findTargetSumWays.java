package com.hhf;

/**
 * @author HP
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 *
 * 方法一：dfs爆搜，因为只有二十个数值，每个数值要么加要么减
 *
 * 方法二：动态规划，考虑到存在负值的情况所以dp[i][j]中的j的范围应该是（-sum（|num[i]|），sum（|num[i]|）），
 *        但是数组范围不能是负值，所以要都加上sum来整体移位，i的范围从[1,n]
 *        转移方程是dp[i][j] = dp[i-1][j-nums[i]]+dp[i-1][j+nums[i]]
 *       注意：f[0][0]=1 为初始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。
 *              dp[i][j]中存的是次数，所以要加上从j-nums[i]来的和从j+nums[i]来的
 *
 * 方法三 推荐：对方法二问题进行转化可以知道要求出t，需要nums的一部分进行加一部分进行减才能求出，那么我们只要求出sums中的减的部分（m>=0）的数量，
 *        再将其余的全加上也是能够达到tzhi的，sum-m求出需要正值的部分再减去m即为t，sum-2m=t，--> m=(sum-t)/2;确保sum-t是偶数
 *        这样问题就转化位类似背包问题了，当前dp[i][j] =dp[i-1][j]+dp[i-1][j-nums[i-1]]，
 *        即每个数值有「选」和「不选」（因为第i-1个已经够了所以可以不选）两种决策
 */
public class No_494_findTargetSumWays {
    int ans =0;
    public int findTargetSumWays(int[] nums, int t) {
        dfs(nums, t, 0, 0);
        return ans;
    }
    public void dfs(int[] nums , int t,int curIndex ,int curSum){
        if (curIndex ==nums.length){
            ans+= curSum==t ? 1 : 0;
            return;
        }
        dfs(nums,t,curIndex+1,curSum+nums[curIndex]);
        dfs(nums,t,curIndex+1,curSum-nums[curIndex]);
    }

    public int findTargetSumWays2(int[] nums, int t) {
        int n = nums.length;
        int sum =0;
        for (int i = 0; i <nums.length ; i++) {
            sum+=Math.abs(nums[i]);
        }
        if (Math.abs(t)>sum){
            //当要求的数比所有nums绝对值的和还要大，则证明到不了，直接返回
            return 0;
        }
        int[][] dp =  new  int[n+1][2*sum +1];
        dp[0][sum] =1;
        for (int i = 1; i <= n; i++) {
            for (int j = -sum; j <= sum; j++) {
                //注意虽然dp[i]是从1开始，但是nums不是的所以下面对应的是nums[i-1]
                if (j-nums[i-1] +sum>=0){
                    dp[i][j+sum] += dp[i-1][j-nums[i-1]+sum];
                }
                if (j+nums[i-1]+sum<=2*sum){
                    dp[i][j+sum] += dp[i-1][j+nums[i-1]+sum];
                }
            }
        }
        return dp[n][t+sum];
    }

    public int findTargetSumWays3(int[] nums, int t) {
        int n = nums.length;
        int sum =0;
        for (int i = 0; i <nums.length ; i++) {
            sum+=Math.abs(nums[i]);
        }
        if (Math.abs(t)>sum || (t-sum)%2 != 0){
            //当要求的数比所有nums绝对值的和还要大，则证明到不了，直接返回
            return 0;
        }
        int m=(sum-t)/2;
        int[][] dp =  new  int[n+1][m+1];
        dp[0][0] =1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //注意虽然dp[i]是从1开始，但是nums不是的所以下面对应的是nums[i-1]
                //dp[i][j] =dp[i-1][j]+dp[i-1][j-nums[i-1]]
                dp[i][j] +=dp[i-1][j];
                if (j-nums[i-1]>=0){
                    dp[i][j] +=dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][m];
    }
}
