package com.hhf.classification.backtrack.pailiezuhe;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 元素无重，可复选
 */
public class No_39_combinationSum {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    //boolean[] used ;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //used  = new boolean[candidates.length];
        backtrack(candidates,target,0,0);
        return res;
    }

    private void backtrack(int[] candidates, int target,int start, int curSum) {
        if (curSum==target){
            res.add(new LinkedList<>(list));
            return;
        }
        if (curSum > target){
            return;
        }
        for (int i = start; i <candidates.length ; i++) {
            /*if (used[i]){
                continue;
            }*/
            //used[i] = true;
            list.add(candidates[i]);
            backtrack(candidates,target,i,curSum+candidates[i]);
           // used[i] =false;
            list.removeLast();
        }
    }
}
