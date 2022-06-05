package com.hhf;

import java.util.HashSet;

public class No_0_TreeCommonAncester {
    /**   https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ
     *  236 题「二叉树的最近公共祖先」
     * @param root
     * @param val1
     * @param val2
     * @return
     */
    // 定义：在以 root 为根的二叉树中寻找值为 val1 或 val2 的节点
    TreeNode find(TreeNode root, int val1, int val2) {
        // base case
        if (root == null) {
            return null;
        }
        // 前序位置，看看 root 是不是目标值
        if (root.val == val1 || root.val == val2) {
            return root;
        }
        // 去左右子树寻找
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);
        // 后序位置，已经知道左右子树是否存在目标值

        return left != null ? left : right;
    }

    /**
     * 1676 题「二叉树的最近公共祖先 IV」：依然给你输入一棵不含重复值的二叉树，但这次不是给你输入p和q两个节点了，而是给你输入一个包含若干节点的列表nodes（这些节点都存在于二叉树中），让你算这些节点的最近公共祖先。
     */
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // 将列表转化成哈希集合，便于判断元素是否存在
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }

        return find(root, values);
    }

    // 在二叉树中寻找 values 的最近公共祖先节点
    TreeNode find(TreeNode root, HashSet<Integer> values) {
        if (root == null) {
            return null;
        }
        // 前序位置
        if (values.contains(root.val)){
            return root;
        }

        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);
        // 后序位置，已经知道左右子树是否存在目标值
        if (left != null && right != null) {
            // 当前节点是 LCA 节点
            return root;
        }

        return left != null ? left : right;
    }
    /**
     *
     * 1644 题「二叉树的最近公共祖先 II」 存在有节点不在树中的情况
     * 给你输入一棵不含重复值的二叉树的，以及两个节点p和q，如果p或q不存在于树中，则返回空指针，否则的话返回p和q的最近公共祖先节点。
     */
    boolean foundP =false,foundQ = false;
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find2(root, p.val, q.val);
        if (!foundP || !foundQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }
    TreeNode find2(TreeNode root,int pval, int qval){
        if (root == null){
            return null;
        }
        TreeNode left = find2(root.left,pval,qval);
        TreeNode right = find2(root.right,pval,qval);

        if (left !=null && right!=null){
            return root;
        }
        if (root.val ==qval ||root.val == pval){
            if (root.val ==qval) {
                foundQ=true;
            }
            if (root.val == pval){
                foundP = true;
            }
            return root;
        }
        return left ==null ? right : left;
    }

    /**
     * 第 235 题「二叉搜索树的最近公共祖先」：
     *
     * 给你输入一棵不含重复值的二叉搜索树，以及存在于树中的两个节点p和q，请你计算p和q的最近公共祖先节点。
     */
    public TreeNode lowestCommonAncestor235(TreeNode root, TreeNode p, TreeNode q) {
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);
        return find3(root,val1,val2);
    }

    private TreeNode find3(TreeNode root, int val1, int val2) {
        if (root == null){
            return null;
        }
        if (val1 > root.val){
            return find3(root.right,val1,val2);
        }
        if (val2 < root.val){
            return find3(root.left,val1,val2);
        }
        return root;
    }
    /***
     * 力扣第 1650 题「二叉树的最近公共祖先 III」，这次输入的二叉树节点比较特殊，包含指向父节点的指针：
     *
     * class Node {
     *     int val;
     *     Node left;
     *     Node right;
     *     Node parent;
     * };
     * 给你输入一棵存在于二叉树中的两个节点p和q，请你返回它们的最近公共祖先，函数签名如下：
     *
     * Node lowestCommonAncestor(Node p, Node q);
     * 由于节点中包含父节点的指针，所以二叉树的根节点就没必要输入了。
     */
    class Node {
          int val;
          Node left;
          Node right;
          Node parent;
     };
    Node lowestCommonAncestor(Node p, Node q) {
        // 施展链表双指针技巧
        Node a = p, b = q;
        while (a != b) {
            // a 走一步，如果走到根节点，转到 q 节点
            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }
            // b 走一步，如果走到根节点，转到 p 节点
            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }
        return a;
    }
}
