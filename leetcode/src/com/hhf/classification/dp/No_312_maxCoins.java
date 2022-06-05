package com.hhf.classification.dp;

/**
 * @author HP
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 方法 dp
 * 利用小区间去求出大区间 区间l从1一直到n
 * 在区间l内，从第i个到j个，i可以从0到n，j是根据i和区间l进行变换，即j-i+1=l 从而j= l+i-1
 * 同时在区间l内也即（i，j）范围内，可以任意选择爆破点，不断求出哪个爆破点的值是最大的
 * 这里要注意：需要在两端加上两个虚拟的值为1的节点，方便计算
 * https://www.bilibili.com/video/BV1Cb4y1r7P1?p=2&spm_id_from=pageDriver
 *
 * 戳破（i，j）开区间内气球能够获得的最大金币
 */
public class No_312_maxCoins {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums =  new int[n+2];
        newNums[0]=1;
        newNums[n+1]=1;
        for (int i = 1; i <=n ; i++) {
            newNums[i]=nums[i-1];
        }
        int[][] dp = new int[n+2][n+2];
        //第一层代表区间i到j的区间大小可以是从3到n+2
        for (int l = 3; l <= n+2; l++) {
            //第二层表示是i可以走的范围
            for (int i = 0; i <=n+2-l; i++) {
                int j = l+i-1;
                //选择爆破点从 i->j，因为开区间所以K从i+1到j-1
                for (int k = i+1; k <j ; k++) {
                    //dp[i][k]+dp[k][j] 代表的都是开区间
                    dp[i][j] = Math.max(dp[i][j],dp[i][k]+dp[k][j]+newNums[i]*newNums[k]*newNums[j]);
                }
            }
        }
        return dp[0][n+1];
    }
}
