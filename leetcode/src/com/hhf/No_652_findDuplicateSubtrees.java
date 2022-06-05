package com.hhf;

import com.offer.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HP
 * 652. 寻找重复的子树
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 *
 */
public class No_652_findDuplicateSubtrees {
    Map<String,Integer> map = new HashMap<>();
    List<TreeNode> list = new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return list;
    }

    private String traverse(TreeNode root) {
        if (root == null){
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);
        String cur = left+","+right+","+root.val;
        if (map.getOrDefault(cur,0) ==1){
            list.add(root);
        }
        map.put(cur,map.getOrDefault(cur,0)+1);
        return cur;
    }
}
