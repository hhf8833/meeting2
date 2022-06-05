package com.hhf;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * @author HP
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 *
 * dfs加动态规划
 * 每一个节点都是偷或者不偷不偷为0 偷为1
 * f(i)[0]第i个节点不偷的话，那他的金额为他的子树偷或者不偷两者较大的左右子树和，即左max（f(i+1)[1]，f(i+1)[0]）+ 右 max（f(i+1)[1]，f(i+1)[0]）
 * f(i)[1]第i个节点偷的话 那金额为他子树不偷的和跟当前的值的和，即f(i+1)[0]左+f(i+1)[0]右
 */
public class No_337_rob {
    public int rob(TreeNode root) {

        if (root ==null){
            return 0;
        }
        return Math.max(dfs(root)[0],dfs(root)[1]);
    }
    public int[] dfs (TreeNode root){
        if (root ==null){
            return new int[2];
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] nowRob = new  int[2];
        nowRob[0] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        nowRob[1] = left[0]+right[0]+root.val;
        return nowRob;
    }
}
