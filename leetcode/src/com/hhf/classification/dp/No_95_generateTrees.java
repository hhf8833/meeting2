package com.hhf.classification.dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.prefs.NodeChangeEvent;

public class No_95_generateTrees {
    public List<TreeNode> generateTrees(int n) {
        //因为最开始的0作为辅助值
       // int[] G= new int[n+1];
        LinkedList<TreeNode>[] dp = new LinkedList[n+1];
        //作为辅助，以防乘积为0
        dp[0]= new LinkedList<>();
        if (n==0){
            return dp[0];
        }
        dp[0].add(null);
        // G[1]=1;
        //第一层循环代表  G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)，需要次数
        for (int i = 1; i <= n ; i++) {
            dp[i] = new LinkedList<>();

            for (int j = 0; j < i ; j++) {
                //G[i] += G[j]*G[i-j-1];
                for (int k = 0; k < dp[j].size(); k++) {
                    for (int l = 0; l < dp[i - j - 1].size(); l++) {
                        TreeNode root = new TreeNode(i);
                        TreeNode left = dp[j].get(k);
                        TreeNode right = dp[j].get(l);
                        root.left =left;
                        root.right = right;
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
}
