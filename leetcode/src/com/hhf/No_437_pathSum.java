package com.hhf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 方法1：最朴素的dfs
 * 假设每次访问的节点为根向下查找是否有符合的，dfs1为遍历节点，同时以其为根向下遍历
 *
 * 方法二：前缀和
 * 逆向思维，从根遍历到的该节点有多少个符合targetSum的路径，hash保存该节点的前缀和，key为当前节点的前缀和，value为该前缀和出现的次数
 *
 * 有了前缀和，我们只要在每一条路径上求解：两个节点之差等于 targetSum，即可。
 *
 * 为了更快速的找到某个数值在路径中是否出现过，我们可以使用哈希表记录路径中每个数值出现的次数，
 * 比如，上图中哈希表中记录了 10 这个数值，当遍历到 18 时，发现哈希表中有 18 - 8 = 10，总次数加上 10 出现的次数即可。
 *
 * 另外，为了处理包含根节点的情况，我们需要在哈希表中存储一个 (0,1) 的键值对。
 *
 * 而且，我们并不需要一开始就把前缀和树计算出来，我们可以边遍历边计算，这里使用回溯来处理。
 */
public class No_437_pathSum {
    int res,target;
    public int pathSum(TreeNode root, int targetSum) {
        target=targetSum;
        dfs1(root);
        return res;
    }
    public void dfs1(TreeNode root ){
        if (root==null){
            return;
        }
        dfs2(root,root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    public void dfs2(TreeNode root , int currSum){
        if (currSum == target){
            res++;
        }
        if (root.left!= null){
            dfs2(root.left,currSum+root.left.val);
        }
        if (root.right!= null){
            dfs2(root.right,currSum+root.right.val);
        }
    }
}

class Solution2 {
    int res =0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        //因为可能存在currSum-targetSum为根节点的情况
        map.put(0,1);
        dfs(root,map,0,targetSum);
        return res;
    }
    public void dfs(TreeNode root , Map<Integer,Integer> map, int currSum,int targetSum){
        if (root ==null){
            return;
        }
        currSum+=root.val;
        //如果存在currSum-targetSum对应的前缀和，则将其得出并加1，么有的话看为0
        res += map.getOrDefault(currSum-targetSum,0);
        map.put(currSum,map.getOrDefault(currSum,0)+1);
        dfs(root.left,map,currSum,targetSum);
        dfs(root.right,map,currSum,targetSum);
        //回溯将map变为最原始的状态
        map.put(currSum,map.getOrDefault(currSum,0)-1);
    }

}
