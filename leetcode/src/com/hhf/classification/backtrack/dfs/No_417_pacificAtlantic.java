package com.hhf.classification.backtrack.dfs;

import jdk.nashorn.internal.ir.SplitReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 *
 * dfs逆向思考，只是能扫描到的即为从小的节点dfs过来的它也必能到达，也可以想象是拐弯到达的
 */
public class No_417_pacificAtlantic {
    int m ,n;
    int[][] heights;
    private int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;n = heights[0].length;
        this.heights =heights;
        boolean[][] canToPacific = new boolean[m][n];
        boolean[][] canToAtlantic = new boolean[m][n];

        //从左边界逆向查找流入太平洋的
        for (int i = 0; i < m; i++) {
            dfs(i,0,canToPacific);
        }
        //从上边界逆向查找流入太平洋的
        for (int i = 0; i < n; i++) {
            dfs(0,i,canToPacific);
        }
        //从右边界逆向查找流入大西洋的
        for (int i = 0; i < m; i++) {
            dfs(i,n-1,canToAtlantic);
        }
        //从下边界逆向查找流入大西洋的
        for (int i = 0; i < n; i++) {
            dfs(m-1,i,canToAtlantic);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //注意这里别比较错误 ！！！！！！！
                if (canToPacific[i][j] && canToAtlantic[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j, boolean[][] ocean) {
        if (ocean[i][j]){
            //该位置遍历过了就返回
            return;
        }
        //只要能进入dfs，则都是从小节点一步一步不断的到该大节点的，因此必为true
        ocean[i][j] =true;

        for (int[] dir : directions) {
            int newX = dir[0]+i,newY= dir[1]+j;
            //这里已经判断了新节点是大于当前节点的，所以 for循环上面才能直接为true
            if (newX>=0 && newX <m && newY>=0 && newY < n && heights[newX][newY] >= heights[i][j]){
                dfs(newX,newY,ocean);
            }
        }
    }


}
