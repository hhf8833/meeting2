package com.hhf.classification.backtrack;

/**
 * @author HP
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class No_37_solveSudoku {
    public void solveSudoku(char[][] board) {
        backtrack(board,0,0);

    }

    private boolean backtrack(char[][] board, int curRow,int curCol) {
        //当遍历到最后一列，要从下一行再开始
        if (curCol == 9){
            return backtrack(board,curRow+1,0);

        }
        if (curRow ==9){
            return true;
        }

        if (board[curRow][curCol] !='.'){
            return backtrack(board,curRow,curCol+1);
        }

        for (char i = '1'; i <= '9'; i++) {
            if (!isValid(board,curRow,curCol,i)){
                continue;
            }
            board[curRow][curCol] = i;
            if (backtrack(board,curRow,curCol+1)){
                return true;
            }
            board[curRow][curCol] = '.';
        }
        return false;
    }

    private boolean isValid(char[][] board, int curRow, int curCol, char curChar) {
        for (int i = 0; i < 9; i++) {
            //当前行
            if (board[curRow][i] == curChar){
                return false;
            }
            //当前列
            if (board[i][curCol] == curChar){
                return false;
            }
            //小的矩阵
            if (board[(curRow/3)*3+i/3][(curCol/3)*3+i/3] ==curChar){
                return false;
            }
        }
        return true;
    }
}
