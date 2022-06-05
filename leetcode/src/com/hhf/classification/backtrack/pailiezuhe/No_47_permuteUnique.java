package com.hhf.classification.backtrack.pailiezuhe;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No_47_permuteUnique {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        //需要先排序，不然下面回溯的时候 nums[j-1] == nums[j] 这一步没法判断
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums,0,used);
        return res;
    }

    private void backtrack(int[] nums, int i, boolean[] used) {
        if (nums.length ==i){
            res.add(new LinkedList<>(path));
        }

        for (int j = 0; j < nums.length; j++) {
            //剪枝
            if (used[j]){
                continue;
            }
            if (j>0 && nums[j-1] == nums[j] && !used[j-1]){
                continue;
            }
            used[j] = true;
            path.add(nums[j]);
            backtrack(nums,i+1,used);
            used[j]=false;
            path.removeLast();
        }

    }
}
