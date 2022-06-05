package com.offer.ali;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class No_2021FourStar_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         n = sc.nextInt();
         m = sc.nextInt();
        sc.nextLine();
        char[][] board =new char[n][m];
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) =='S'){
                    start[0]=i;
                    start[1]=j;
                }
                if (s.charAt(j) =='E'){
                    end[0]=i;
                    end[1]=j;
                }
                board[i][j]=s.charAt(j);
            }
        }

        zoulu(board,start,end);
    }
    static boolean[][] used ;
    static int res =Integer.MAX_VALUE;
    static int n;
    static int m ;
    private static void zoulu(char[][] board, int[] start, int[] end) {
         used= new boolean[n][m];
         bfs(board,start[0],start[1],end);
         if (res==Integer.MAX_VALUE){
             System.out.println(-1);
         }else {
             System.out.println(res);
         }
    }

    private static void bfs(char[][] board, int i, int j, int[] end) {
        int[][] directions =new int[][]{{-1,0},{1,0},{0,1},{0,-1},{n-1,m-1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j,0,0});
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x=poll[0],y=poll[1],time=poll[2],fly=poll[3];

            for (int k = 0; k < directions.length; k++) {
                int newX,newY,newTime,newFly;

                if (k==4){
                    if (fly>5){
                        break;
                    }
                    newX=directions[k][0]-x;
                    newY=directions[k][1]-y;
                    newFly = fly+1;
                }else {
                    newX=directions[k][0]+x;
                    newY=directions[k][1]+y;
                    newFly = fly;
                }
                if (newX<0||newX>=n||newY<0||newY>=m || used[newX][newY] || board[newX][newY] =='#' ){
                    continue;
                }
                newTime=time+1;
                if (newX ==end[0] && newY == end[1]){
                    res =Math.min(res,newTime);
                    return;
                }
                queue.offer(new int[]{newX, newY, newTime, newFly});
                used[newX][newY] = true;
            }


        }
    }

}
