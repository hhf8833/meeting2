package com.hhf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 */
public class No_51_solveNQueens {
    public List<List<String>> solveNQueens(int n) {
        //生成棋盘，
        char[][] board = new char[n][n];
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            Arrays.fill(board[i],'.');
        }
        backtrack(board,list,0);
        return list;
    }

    private void backtrack(char[][] board, List<List<String>> lists,int row) {
        //结束条件
        if (board.length ==row){
            lists.add(changeList(board));
            return;
        }
        //选择列表
        for (int col = 0; col <board[0].length; col++) {
            if (!isValid(board,row,col)){
                continue;
            }
            board[row][col] ='Q';
            backtrack(board,lists,row+1);
            board[row][col] = '.';
        }

    }

    private boolean isValid(char[][] board, int row, int col) {
        //因为是从每行进行放皇后的，所以考虑列就行，行在选择列表中是循环的，不会重复，右下，左下，列的下方均未放置，所以不用遍历
        //当前列
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] =='Q'){
                return false;
            }
        }
        //右上
        for (int i = row-1,j=col+1; i >=0 &&j < board[0].length ; i--,j++) {
            if (board[i][j] =='Q'){
                return false;
            }
        }
        //左上
        for (int i = row-1,j=col-1; i >=0 && j >=0 ; i--,j--) {
            if (board[i][j] =='Q'){
                return false;
            }
        }
        return true;
    }

    private List<String> changeList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String s = String.valueOf(board[i]);
            list.add(s);
        }
        return list;
    }
}
