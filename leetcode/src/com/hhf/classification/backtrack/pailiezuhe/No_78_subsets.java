package com.hhf.classification.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 组合不重复 不可复选
 */
public class No_78_subsets {
    List<List<Integer>> lists = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums,0);
        return lists;
    }

    private void backtrack(int[] nums, int start) {

        lists.add(new ArrayList<>(list));


        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums,i+1);
            list.remove(list.size()-1);
        }

    }
}
