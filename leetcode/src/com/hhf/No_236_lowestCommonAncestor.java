package com.hhf;

/**
 * @author HP
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 * //后序遍历
 * 想要找到p、q的公共祖先，后序遍历递归找出 还要确保是在两侧
 * 终止条件是叶子节点也不是或者找到返回当前节点
 *
 * 递归条件分为以下四个情况
 * 先求出左右节点递归上来的值
 * 1.左，右都不为空说明是q p是在两侧，返回当前节点即为公共祖先节点
 * 2.都为空，则返回null
 * 3.有一个为空 左为空，则右不空，pq不可能在左，所以返回右，同时具体又细分为，1）右的两个子节点都不空 2）有一个空，一个不空
 * 4.同理 有一个为空 右为空，则左不空
 */
public class No_236_lowestCommonAncestor {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //当root为p或q的时候也是符合的,
        // 另外、当两个节点在一边的时候，即q是p的父节点，遍历到q的时候就会返回了不会在向下遍历,同时p也已经包含在里面，也是右公共祖先的
        if (root ==null || root==p ||root ==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left ==null && right == null){
            return null;
        }
        if (left != null && right !=null){
            return root;
        }
        if (left == null){
            return right;
        }
//        if (right == null)
            return left;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        root.left=root2;
        root2.left=root3;
        TreeNode res =No_236_lowestCommonAncestor.lowestCommonAncestor(root,root2,root3);
        System.out.println(res.val);
    }
}
