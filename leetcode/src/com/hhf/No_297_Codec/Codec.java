package com.hhf.No_297_Codec;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * @author HP
 *297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *
 *
 * 方法一：dfs
 *  前序遍历将其加入到字符串中并用”，“隔开
 */
public class Codec {

/*    // Encodes a tree to a single string. DFS
    public String serialize(TreeNode root) {
        if (root ==null){
            return "x,";
        }
        //String s = new String(new StringBuffer(root.val).append(",").append(serialize(root.left)).append(serialize(root.right)));
        return root.val+","+serialize(root.left)+serialize(root.right);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array =data.split(",");
        Queue<String > dq = new LinkedList<>(Arrays.asList(array));
        return buildTree(dq);
    }
    public TreeNode buildTree(Queue<String> dq){
        String s = dq.poll();
        if ("x".equals(s)){
            return null;
        }

        TreeNode  root = new TreeNode(Integer.parseInt(s));
        root.left=buildTree(dq);
        root.right=buildTree(dq);
        return root;
    }*/
    //bfs
    public String serialize(TreeNode root) {

        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node == null){
                sb.append("x,");
            }else {
                sb.append(node.val+",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.一个数组，一个队列用于存弹出数据
    public TreeNode deserialize(String data) {
        if (data==""){
            return null;
        }
        String[] array = data.split(",");
        Queue<String> nodes = new ArrayDeque<>(Arrays.asList(array));
        TreeNode root = new TreeNode(Integer.parseInt(nodes.poll()));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            String left = nodes.poll();
            String right = nodes.poll();
            if (!"x".equals(left)){
                node.left = new TreeNode(Integer.parseInt(left));
                queue.offer(node.left);
            }
            if (!"x".equals(right)){
                node.right = new TreeNode(Integer.parseInt(right));
                queue.offer(node.right);
            }
        }
        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));