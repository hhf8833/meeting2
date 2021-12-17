package com.hhf;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class No_102_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root ==null){
            return null;
        }
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //先获得当前层所有node的数量，以便当前层poll完，再进行下一层
            int n=queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node =queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            lists.add(list);
            System.out.println(list);
        }
        return lists;
    }
}
