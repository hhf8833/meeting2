package com.hhf.classification.backtrack.pailiezuhe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 注意：因为数据是存在重复的，要剪枝
 */
public class No_90_subsetsWithDup {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums,0);
        return res;
    }

    private void backtrack(int[] nums, int start) {

        res.add(new LinkedList<>(list));


        for (int i = start; i < nums.length; i++) {
            //注意这里的i >start很关键，它确保了重复时叶子节点能够被遍历
            if(i > start && nums[start-1]==nums[start]){
                continue;
            }
            list.add(nums[i]);
            backtrack(nums,i+1);
            list.removeLast();

        }
    }
}
