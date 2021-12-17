package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HP
 * 301. 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 * dfs 使用22题生成括号的方法，左括号要严格大于右括号。
 * len 记录「爆搜」过程中的最大子串，然后只保留长度等于 len 的子串。也即删除多余括号后的元素
 * 首先需要知道元素中能狗配对的括号的总数量
 * u当前遍历到的位置，cur当前已存在的字符串数据，score分数，当小于0的时候意思就为左括号小于右括号不符合要剪枝
 */
public class No_301_removeInvalidParentheses {
    int n ,max ,len;
    String s;
    Set<String> set;
    public List<String> removeInvalidParentheses(String s) {
        this.s=s;
        n= s.length();
        set = new HashSet<>();
        int left =0,right = 0;
        for (char c:
             s.toCharArray()) {
            if (c == '('){
                left++;
            }else if (c == ')'){
                right++;
            }
        }
        //得到需要生成的括号对数量
        max = Math.min(left,right);
        dfs(0,"",0);
        return  new ArrayList<>(set);
    }
    public void dfs (int u, String curStr , int score){
        if (score <0 || score > max){
            return;
        }
        if (u == n){
            if (score == 0 && curStr.length()>= len){
                if (curStr.length()> len) {
                    set.clear();
                }
                len = curStr.length();

                set.add(curStr);
            }
            return;
        }
        char c = s.charAt(u);
        if (c == '('){
            dfs(u+1,curStr+String.valueOf(c),score+1);
            dfs(u+1,curStr,score);
        }else if (c == ')'){
            dfs(u+1,curStr+String.valueOf(c),score-1);
            dfs(u+1,curStr,score);
        }else {
            dfs(u+1,curStr+String.valueOf(c),score);
        }
    }
}
