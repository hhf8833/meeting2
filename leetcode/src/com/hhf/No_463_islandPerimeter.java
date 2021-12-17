package com.hhf;

/**
 * @author HP
 * 463. 岛屿的周长
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 同类问题
 *  * L200. 岛屿数量 （Easy）
 *  * 463. 岛屿的周长 （Easy）
 *  * 695. 岛屿的最大面积 （Medium）
 *  * 827. 最大人工岛 （Hard）
 *
 *  求周长的时候看的是边界和岛屿与水部分 是的话就加一代表的是周长的一部分
 */
public class No_463_islandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    res+=perimeter(grid,i,j);
                }
            }
        }
        return res;
    }
    public int perimeter(int[][] grid,int i ,int j){
        if (!isArea(grid,i,j)){
            return 1;
        }
        if (grid[i][j] == 0){
            return 1;
        }
        if (grid[i][j] ==2){
            return 0;
        }
        grid[i][j]++;
        return perimeter(grid,i+1,j)+perimeter(grid,i-1,j)+perimeter(grid,i,j-1)+perimeter(grid,i,j+1);
    }
    public Boolean isArea(int[][] grid,int i ,int j){
        return 0<=i && i< grid.length &&0<=j &&j< grid[0].length;
    }
}
