package com.hhf.classification.backtrack;

/**
 * @author HP
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class No_130_solve {
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col; j++) {
                //将边界的0以及与其相连的0都变为 #
                boolean flag = i==0 || j==0 || i==row-1 ||j==col-1;
                if (flag && board[i][j] == '0'){
                    dfs(i,j,board);
                }
            }
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col; j++) {
                if ( board[i][j] =='#'){
                    board[i][j] ='0';
                }else if ( board[i][j] =='0'){
                    board[i][j]='X';
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        if (i<0||j<0 ||i>=board.length ||j>=board[0].length ||board[i][j] =='#'||board[i][j] =='X'){
            return;
        }
        board[i][j] ='#';
        dfs(i+1,j,board);
        dfs(i-1,j,board);
        dfs(i,j+1,board);
        dfs(i,j-1,board);
    }
}
