package leetcode;

import java.util.Arrays;

/**
 * @author HP
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的
 *
 *
 * 使用递归dfs的方式，传统的dfs再遍历的时候会有很多重复的因此需要一个数组 memo【n】来记录当前0~n的第i个中需要最少的钱数
 *
 *  法二：动态规划，amount从0开始不断找需要的早小数量
 */
public class No_322_coinChange {
    int[] memory;
    public int coinChange(int[] coins, int amount) {
        memory = new int[amount+1];
        return dfs(coins,amount);
    }
    public int dfs(int[] coins , int amount){
        if (amount<0){
            return -1;
        }
        if (amount == 0){
            return 0;
        }
        //读取记录中当前amount需要的钱最小个数
        if (memory[amount] != 0){
            return memory[amount];
        }
        int count = 0;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            count = dfs(coins,amount-coins[i]);
            //求出当前amout需要的最小数量，使用coin的每一个可能返回的数量，来找出最小的,因为count是递归返回的不包含这一层的，要+1把coin【i】加上
            //这里别忘了判断如果count<0说明当前amount在coin【i】没有合适的硬币匹配要扔掉!!!!
            if (count>=0 ){
                minCount= Math.min(minCount,count+1);
            }
        }
        memory[amount] = minCount == Integer.MAX_VALUE ? -1 :minCount;
        return memory[amount];
    }
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        int max = amount+1;
        Arrays.fill(dp,max);
        dp[0]= 0 ;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i-coins[j]>=0){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return  dp[amount] >amount ? -1 : dp[amount];
    }
}
