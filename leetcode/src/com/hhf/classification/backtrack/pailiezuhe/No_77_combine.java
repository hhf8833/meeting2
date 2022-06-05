package com.hhf.classification.backtrack.pailiezuhe;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 组合，元素不可重复不可复选
 */
public class No_77_combine {
    List<List<Integer>> lists = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n,k);
        return lists;
    }

    private void backtrack(int start, int n, int k) {
        if (k == list.size()){
            lists.add(new LinkedList<>(list));
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            backtrack(i+1,n,k);
            list.removeLast();
        }
    }
}
