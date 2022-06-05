package com.hhf.classification.dp.pakage;

import org.junit.jupiter.api.Test;

/**
 * @author HP
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 *
 * 该题不能使用322 200 279 的dp解法，因为他们是内层递归时，之前用过的数字还能用，而这个题，之前用过的已经用不了了
 *背包问题
 * dp[i][j] = 1) dp[i-1][j] ，在添加一个num的时候，状态肯定是从i-1个数来的,如果 dp[i−1][j] 为真，直接计算下一个状态
 *            2) true (nums[i]==j)
 *            3) dp[i-1][j-nums[i]] (nums[i]<==>j)
 *
 * 优化：，当前行总是参考了它上面一行 「头顶上」 那个位置和「左上角」某个位置的值。
 * 因此，我们可以只开一个一维数组，从后向前依次填表即可，前面的都是上一行留下的
 */
public class No_416_canPartition {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装入或不装入背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    //子集背包问题
    public static boolean canPartition2(int[] nums) {
        int n =nums.length;
        int sum =0;
        for (int num :
                nums) {
            sum+=num;
        }
        //因为是要把数组内的所有数值分成两半所以如果是奇数的话则不能分，返回
        if ((sum&1) ==1){
            return false;
        }
        int target = sum/2;
        boolean[]dp = new boolean[target+1];
        dp[0] =true;

        for(int j = 0;j<n;j++){
            for(int i =target; i >=0;i--){
                if(i-nums[j] >=0){
                    dp[i]= dp[i] || dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
    @Test
    public void test(){
        int[] nums = new int[]{1,5,11,5};
       // boolean flag = canPartition(nums);

    }
}
