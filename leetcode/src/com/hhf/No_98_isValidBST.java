package com.hhf;


/*98. 验证二叉搜索树
        给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

        有效 二叉搜索树定义如下：

        节点的左子树只包含 小于 当前节点的数。
        节点的右子树只包含 大于 当前节点的数。
        所有左子树和右子树自身必须也是二叉搜索树。*/
/*
方法一：递归
引入上界和下界，因为左子树是要小于根的，因此上界是根，下界为无限小，即无限小<左子树<根 右子树同理
方法二：中序遍历，遍历的时候判断前一个结点是否是小于当前节点即可
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class No_98_isValidBST {
    TreeNode maxVal;

    /*//递归法
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }
    public boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root==null){
            return true;
        }
        if (root.val<=lower ||root.val>=upper){
            return false;
        }
        return isValidBST(root.left,lower,root.val) && isValidBST(root.right,root.val,upper);
    }*/
    //中序遍历
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(u);
        q.add(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.add(u.left);
            q.add(v.right);

            q.add(u.right);
            q.add(v.left);
        }
        return true;
    }


}