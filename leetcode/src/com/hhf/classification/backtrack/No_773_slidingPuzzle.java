package com.hhf.classification.backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author HP
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 */
public class No_773_slidingPuzzle {
    Set<String>  visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        queue.add(start);
        visited.add(start);
        int step = 0;
        int[][] niegher = new int[][]{
                {1,3},
                {0,2,4},
                {1,5},
                {0,4},
                {1,3,5},
                {2,4}
        };
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if ("123450".equals(cur)){
                    return step;
                }
                int zero = 0;
                for (char c:
                cur.toCharArray()){
                   if (c !='0'){
                       zero++;
                   }else {
                       break;
                   }
                }
                for (int index:
                     niegher[zero]) {
                    String swap = swap(cur, zero, index);
                    if (!visited.contains(swap)){
                        queue.add(swap);
                        visited.add(swap);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(String cur, int zero, int index) {
        char[] chars = cur.toCharArray();
        char temp = chars[zero];
        chars[zero] =chars[index];
        chars[index] = temp;
        return String.valueOf(chars);
    }
}
