package com.hhf.classification.backtrack.bfs;


import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HP
 */
public class No_1091_shortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix1(int[][] grids) {
        if (grids == null || grids.length == 0 || grids[0].length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                if (grids[cr][cc] == 1) {
                    continue;
                }
                if (cr == m - 1 && cc == n - 1) {
                    return pathLength;
                }
                grids[cr][cc] = 1; // 标记
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }
    public int m,n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] directions = new int[][]{{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<Integer[]> queue = new ArrayDeque<>();
        if(grid[0][0] ==0){
            return -1;
        }
        // i,j
        queue.offer(new Integer[]{0,0});
        while(!queue.isEmpty()){
            res++;
            int size = queue.size();
            for(int j =0 ; j < size; j++){
                Integer[] poll = queue.poll();
                int x =poll[0],y=poll[1];
                if(grid[x][y] == 1 || visited[x][y]){
                    continue;
                }
                if(x ==m-1 && y ==n-1){
                    return res;
                }
                visited[x][y] = true;
                for(int i =0;i < directions.length;i++){
                    int newX=x+directions[i][0],newY=y+directions[i][1];
                    if(!isVaild(newX,newY)){
                        continue;
                    }
                    queue.add(new Integer[]{newX,newY});
                }
            }

        }
        return -1;
    }
    public boolean isVaild(int i ,int j){
        if(i <0 || j<0 || i>=m || j>=n){
            return false;
        }
        return true;
    }
}
