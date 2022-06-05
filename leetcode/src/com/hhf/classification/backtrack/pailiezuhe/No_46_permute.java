package com.hhf.classification.backtrack.pailiezuhe;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 全排列  元素不可重复，不可复选
 */
public class No_46_permute {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    boolean[] flag;
    public List<List<Integer>> permute(int[] nums) {
        flag= new boolean[nums.length];

        backtrack(nums,0);
        return res;
    }

    private void backtrack(int[] nums, int count) {
        if (count == nums.length){
            res.add(new LinkedList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flag[i]){
                continue;
            }
            flag[i] =true;
            list.add(nums[i]);
            backtrack(nums,i+1);
            flag[i] = false;
            list.removeLast();
        }
    }
}
