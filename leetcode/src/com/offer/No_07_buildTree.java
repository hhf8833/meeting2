package com.offer;

import org.junit.jupiter.api.Test;

/**
 * @author HP
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class No_07_buildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 ||inorder.length ==0 ){
            return null;
        }
        return travel(0,preorder,0,preorder.length-1,inorder);
    }
    public TreeNode travel(int i , int[] preorder ,int start,int end,int[] inorder ){
        if(i==preorder.length ||start > end){
            return null;
        }
        int mid =0;
        TreeNode node = new TreeNode(preorder[i]);
        for(int j = 0 ; j < inorder.length ; j++){
            if(node.val ==inorder[j]){
                mid = j;
                break;
            }
        }
        node.left = travel(i+1,preorder,start,mid-1,inorder);
        node.right = travel(mid+1,preorder,mid+1,end,inorder);
        return node;
    }
    @Test
    public  void test(){
        No_07_buildTree buildTree = new No_07_buildTree();
//        int [] preorder = new int[]{3,9,20,15,7}, inorder = new int[]{9,3,15,20,7};
        int [] preorder = new int[]{1,2,3}, inorder = new int[]{2,3,1};

        TreeNode root = buildTree.buildTree(preorder,inorder);
        System.out.println(root.toString());
    }
}
