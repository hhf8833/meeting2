package com.hhf;

/**
 * @author HP
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 * 同类问题
 * L200. 岛屿数量 （Easy）
 * 463. 岛屿的周长 （Easy）
 * 695. 岛屿的最大面积 （Medium）
 * 827. 最大人工岛 （Hard）
 *
 * 解法：图的dfs遍历，上述问题都是根据dfs图模板的改编，
 * dfs要判断下一个网格是否已经被访问过，如果是的话要做出相对的记录，避免下次再次被遍历，可能会导致其陷入无限循环
 */
public class No_200_numIslands {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //当发现为1的时候意味着有新岛屿，要dfs给他遍历完，遍历途中会将岛屿改为2，避免重复遍历
                //发现一个岛屿后就res++ 代表这个岛屿处理完了
                if (grid[i][j]=='1'){
                    nums(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }
    public void nums(char[][] grid,int i ,int j){
        //出界情况
        if (!isArea(grid, i, j)){
            return;
        }
        //是海洋或者已经遍历过
        if (grid[i][j] == '0' || grid[i][j] == '2'){
            return;
        }
        grid[i][j] = '2';
        nums(grid,i+1,j);
        nums(grid,i-1,j);
        nums(grid,i,j+1);
        nums(grid,i,j-1);
    }
    public boolean isArea(char[][] grid,int i, int j){
        return 0<=i && i < grid.length && 0 <= j && j <grid[0].length;
    }
}
