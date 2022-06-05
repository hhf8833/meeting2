package com.hhf.classification.dp.erweiarray;

/**
 * @author HP
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	    3
 * -5	    -10	    1
 * 10	    30	    -5 (P)
 */
public class No_174_calculateMinimumHP {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length,n = dungeon[0].length;
        int[][] dp = new int[m+1][n+1];
        //这里的边界值m,n的位置全部设为最大值
        for (int i = m; i >=0 ; i--) {
            for (int j = n; j >=0 ; j--) {
                if (i == m || j == n){
                    dp[i][j] = Integer.MAX_VALUE;
                }else if (i==m-1 && j==n-1){
                    //注意这里的初始值
                    dp[i][j] = dungeon[i][j] < 0 ? - dungeon[i][j]+1 : 1;
                } else{
                    //注意 dp[i][j]是由下面的减去dungeon[i][j] ，dungeon大于0的时候是负血包，小于0的时候是正血包
                    int temp = Math.min(dp[i+1][j],dp[i][j+1]) - dungeon[i][j];
                    dp[i][j] = temp < 0 ? 1 : temp;
                }

            }
        }
        return dp[0][0];
    }
}
