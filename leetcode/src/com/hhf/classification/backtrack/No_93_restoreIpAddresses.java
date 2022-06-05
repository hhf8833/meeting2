package com.hhf.classification.backtrack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *
 */
public class No_93_restoreIpAddresses {
    private List<String> res =new ArrayList<>();
    private Deque<String> deque = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        dfs(s,0,s.length(),0);
        return res;
    }

    public void dfs(String s,int start,int len,int curCount){
        if(len == start){
            if(curCount ==4){
                res.add(String.join(".",deque));
                return;
            }
        }
        for(int i =start; i < start+3;i++){
            //现在最长的长度 要小于这时候截完后面的长度，则不能截
            if ((4-curCount)*3 < len-i){
                continue;
            }
            //判断截出来的是否合法
            if (isLegal(s,start,i+1)){
                String substring = s.substring(start, i+1);
                deque.offerLast(substring);
                dfs(s,i+1,len,curCount+1);
                deque.removeLast();
            }
        }
    }

    private boolean isLegal(String s, int start, int end) {
        int len = end - start;
        String substring = s.substring(start, end);
        //注意这里要让len大于1，不然遇到0000这种，就不行了
        if (len >1 && substring.charAt(0) == '0'){
            return false;
        }
        int res = Integer.parseInt(substring);

        return res>=0 && res<=255;
    }


}
