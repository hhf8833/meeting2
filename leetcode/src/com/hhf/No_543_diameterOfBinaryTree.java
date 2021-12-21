package com.hhf;

/**
 * @author HP
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
public class No_543_diameterOfBinaryTree {
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        res=1;
        dfs(root);
        return res-1;
    }
    public int dfs(TreeNode root){
        if (root ==null){
            return 0;
        }
        int leftLens =dfs(root.left);
        int rightLens =dfs(root.right);
        res=Math.max(res,leftLens+rightLens+1);
        return Math.max(leftLens,rightLens)+1;
    }

}
