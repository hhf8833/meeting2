package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 根据中序确定根的问题，递归的建立左右子树，然后返回当前的root节点，让节点连成树
 * 采用hashmap装入中序序列，方便查找
 *
 */
public class No_105_buildTree {

    private Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if ((preorder.length != inorder.length) || (preorder.length == 0)){
            return null;
        }
        map =new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,inorder.length-1,0,inorder.length-1);
    }
    public TreeNode myBuildTree(int[] preorder, int[] inorder,int preLeft,int preRight,int inLeft ,int inRight) {
        if (preLeft>preRight){
            return null;
        }
        //建立当前根节点
        TreeNode node = new TreeNode(preorder[preLeft]);
        //查找中序中当前根的位置
        int preRoot = preorder[preLeft];
        int indexRoot = map.get(preRoot);
        int preTreeSize = indexRoot-inLeft;
        node.left = myBuildTree(preorder,inorder,preLeft+1,preLeft+preTreeSize,inLeft,indexRoot-1);
        node.right = myBuildTree(preorder,inorder,preLeft+preTreeSize+1,preRight,indexRoot+1,inRight);
        return node;
    }
}
