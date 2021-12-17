package com.hhf;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @author HP
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *方法一：将左子树整体插入右子树，将左子树变为空，然后不断循环直至左子树没有了
 * 方法二：利用栈将前序遍历改变下，设置一个记录前一个节点的指针，还有入栈的时候应该先将右节点入再入左节点，这样即便前一个节点左右指向改变，
 * 也不会影响，因为已经入栈
 */
public class No_114_flatten {
    //方法一
/*    public void flatten(TreeNode root) {
        while (root !=null){
            if (root.left == null){
                root=root.right;
            }else {
                TreeNode node=root.left;
                //得到左子树最右侧的节点
                while (node.right != null){
                    node=node.right;
                }
                node.right=root.right;
                root.right = root.left;
                //这里要让left变为空不让就是死循环了
                root.left = null;
            }
        }
    }*/
    public void flatten(TreeNode root) {
        if (root == null){
            return ;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        //将root先加入进去
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(pre !=null){
                pre.left = null;
                pre.right = cur;
            }
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
                //add是队列的函数，插入队列末尾，而push是栈的函数，插入栈
//                stack.add();
            }
            pre = cur;
        }
    }
}
