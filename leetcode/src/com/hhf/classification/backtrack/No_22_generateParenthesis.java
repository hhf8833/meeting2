package com.hhf.classification.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 *方法：  dfs  左括号的数量严格大于右括号的数量也即 剩余可使用的括号数量为左剩余要严格小于右剩余
 */
public class No_22_generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0,0,n,"",res);
        return res;
    }
    public  void dfs(int left ,int right, int n ,String curstr,List<String> res){
        //出口
        if (left == n && right == n){
            res.add(curstr);
        }
        //剪枝
        if (left<right){
            return;
        }
        if (left < n ){
            dfs(left+1,right,n,curstr+"(",res);
        }
        if (right < n ){
            dfs(left,right+1,n,curstr+")",res);
        }
    }
}
