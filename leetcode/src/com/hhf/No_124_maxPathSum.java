package com.hhf;

/**
 * @author HP
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *方法：
 * 采用递归的思想，后序遍历改装，求出左子树和右子树的最大值，，这个值都需要和0比较，比0小的话不如不要，求出左子树+右子树和根的和看跟最大值哪个大要哪个
 * 这里难就难在返回的时候和最大值不同都表达的是什么意思
 */
public class No_124_maxPathSum {
    int maxValue = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root ==null){
            return 0;
        }
        order(root);
        return maxValue;
    }
    public int order(TreeNode root){
        if (root ==null){
            return 0;
        }
        int left = Math.max(0,order(root.left));
        int right = Math.max(0,order(root.right));
        maxValue = Math.max(maxValue,left+right+root.val);
        return root.val+Math.max(left,right);
    }
}
