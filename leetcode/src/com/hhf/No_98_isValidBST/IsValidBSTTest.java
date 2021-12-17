package com.hhf.No_98_isValidBST;


/*98. 验证二叉搜索树
        给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

        有效 二叉搜索树定义如下：

        节点的左子树只包含 小于 当前节点的数。
        节点的右子树只包含 大于 当前节点的数。
        所有左子树和右子树自身必须也是二叉搜索树。*/
/*
方法一：递归
引入上界和下界，因为左子树是要小于根的，因此上界是根，下界为无限小，即左子树<根<无限小 右子树同理
方法二：中序遍历，遍历的时候判断前一个结点是否是小于当前节点即可
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class IsValidBSTTest {
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
    public boolean isValidBST(TreeNode root) {
        if (root ==null){
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double proValue=-Double.MAX_VALUE;
        while (!stack.isEmpty() ||root !=null){
            //中序，所以要将所有的左节点push进去
            while (root !=null){
                stack.push(root);
                root=root.left;
            }
            if (!stack.isEmpty()){
                TreeNode node = stack.pop();
                if (node.val<=proValue){
                    return false;
                }
                proValue= node.val;
                root=node.right;
            }
        }
        return true;

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