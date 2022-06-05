package com.hhf.classification.dp.erweiarray;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author HP
 * 514. 自由之路
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 *
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 *
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 *
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 *
 * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 */
public class No_514_findRotateSteps {
    public int findRotateSteps(String ring, String key) {
        int m = ring.length(), n =key.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i =0; i < m; i++) {
            char c =ring.charAt(i);
            map.putIfAbsent(c,new LinkedList<>());
            map.get(c).add(i);
        }
        System.out.println(map);
        int[][] dp = new int[m+1][n+1];
        //这里为什么要倒着的原因 ：最后return dp方便
        for (int j = n-1; j >=0 ; j--) {
            for (int i = 0; i < m; i++) {
                //从当前i位置到j的最小距离
                char curKey = key.charAt(j);
                List<Integer> list = map.get(curKey);
                int result = Integer.MAX_VALUE;
                for (int k = 0; k < list.size(); k++) {
                    int clockwise = Math.abs(i-list.get(k));
                    int anticlockwise = m-clockwise;
                    result = Math.min(result,Math.min(clockwise,anticlockwise)+dp[list.get(k)][j+1]+1);
                }
                dp[i][j] = result;
            }
        }
        return dp[0][0];
    }
}
