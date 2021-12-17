package com.hhf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HP
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定 s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：拆分时可以重复使用字典中的单词。
 *
 * 方法一，动态规划
 * 求单词的0-i是否是合法的单词，引入一个j去划分0-i，只要0，j-1为合法的（这里要用dp去做保存dp【j】的情况），那么当j，i也合法的话则说明该单词是可以拆分的
 * 这里要主要的一点是dp【0】代表的是空串是合法的，为了方便转移方程
 * 另外还有i是从1开始循环的，因为substring（x，y）y截取的是从0到y-1的位置的元素 这里要注意
 *
 * 方法二：dfs
 * 循环找出第一个符合的字符串，然后再不断递归的找出其余部分，期间设置一个记录符用来判断这些是否被用过，用过就直接得出结果，减少重复的计算
 */
public class No_139_wordBreak {
/*    public boolean wordBreak(String s, List<String> wordDict) {
        //将字典放在hash表中，这样方便查找,速度要比放在list中更快
        Set<String> wordDicSet =new HashSet<>(wordDict);
        //初始化转移方程
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;
        //对s进行逐一拆分因为substring结束位置是i-1 所以这里要从1开始
        for (int i = 1; i <= s.length(); i++) {
            //从0开始不断向后找到符合的前一部分和后一部分
            for (int j = 0; j < i; j++) {
                if ( dp[j] && wordDicSet.contains(s.substring(j,i)) ){
                    //这里的dp[i]表示的是0到i-1
                    dp[i] = true ;
                    //
                    break;
                }
            }
        }
        return dp[s.length()];
    }*/
    public boolean wordBreak(String s, List<String> wordDict) {
        //将字典放在hash表中，这样方便查找,速度要比放在list中更快
        Set<String> wordDicSet =new HashSet<>(wordDict);
        //辅助数组，用来判断是否已经遍历过
        int[] visited = new int[s.length()+1];
        return dfs(0,s,wordDicSet,visited);
    }
    public boolean dfs(int start ,String s ,Set<String> wordDicSet ,int[] visted){
        if (start==s.length()){
            return true;
        }
        if (visted[start] == 1){
            return true;
        }
        if (visted[start] == -1){
            return false;
        }
        for (int end = start+1; end <= s.length(); end++) {
            String preStr = s.substring(start,end);
            if (wordDicSet.contains(preStr) && dfs(end,s,wordDicSet,visted)){
                visted[start]=1;
                return  true;
            }
        }
        visted[start] = -1;
        return false;
    }
}
