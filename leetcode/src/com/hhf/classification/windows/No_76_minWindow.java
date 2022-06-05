package com.hhf.classification.windows;

/**
 * @author HP
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 滑动窗口
 * 左右指针， 先 右指针一直向右找到所有的t串之后，左指针再向右缩小范围直至遇到第一个串后结束，求出最小距离
 *                  然后左指针再向右一次即除掉一个t串再让右指针去找符合的，指针到最后结束；
 *                  新生成一个数组表示字符，这里每遍历一个值，就将该值减一，初始的时候让t的对应字符为一，其他的为零
 *         优化：加上一个统计的变量count用来记还剩多少个t没有包含在窗口中
 */
public class No_76_minWindow {
    public  String minWindow(String s, String t) {
        if (s.length()==0||t.length()==0){
            return "";
        }
        //用来表示对应字符数量
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int left = 0,right =0,minlen = Integer.MAX_VALUE,needCount=t.length(),start =0;
        while (right<s.length()){
            if (need[s.charAt(right)]>0){
                //说明当前字符是t的字符将需求减一
                needCount--;
            }
            need[s.charAt(right)]--;
            if (needCount == 0){
                //为零说明所有的t都已经找到就要对左边进行缩减
                while (left<right && need[s.charAt(left)]<0){
                    need[s.charAt(left)]++;
                    left++;
                }
                minlen= Math.min(minlen,right-left+1);
                //这里的开始应该是minlen更换后的start，而不是每一轮都变
                if (minlen == right-left+1){
                    start =left;
                }
                need[s.charAt(left)]++;
                left++;
                needCount++;

            }
            right++;
        }
        String res = minlen ==Integer.MAX_VALUE ? "":s.substring(start,start+minlen);
        return res;
    }
    //滑动窗口统一方法
    public  String minWindow2(String s, String t) {
        if (s.length() ==0||t.length()==0){
            return "";
        }
        int[] needArr= new  int[26];
        int needCount = 0;
        for (char c :
                t.toCharArray()) {
            needArr[c-'a']++;
            needCount++;
        }
        int i =0,j=0,minLen = Integer.MAX_VALUE,startIndex =0;
        while (j<s.length()){
            if (needArr[s.charAt(j)-'a']>0){
                needCount--;
            }
            needArr[s.charAt(j)-'a']--;
            //说明当前窗口已经存在所有的t了，开始缩小窗口，取得最小值,这时的needArr已经全部是负数或者0
            if (needCount==0){
                while(i<=j && needArr[s.charAt(i)-'a']<0){
                    needArr[s.charAt(i)-'a']++;
                    i++;
                }
                minLen=Math.min(minLen,j-i+1);
                if (minLen ==j-i+1){
                    startIndex=i;
                }
                needArr[s.charAt(i)-'a']++;
                needCount++;
                i++;
            }
            j++;

        }
        String res = s.substring(i,i+minLen);
        return res =minLen==Integer.MAX_VALUE ? "":res;
    }
    public static void main(String[] args) {
        No_76_minWindow test =new No_76_minWindow();
        String s="cabwefgewcwaefgcf",t="cae";
        String res =test.minWindow(s,t);
        System.out.println(res);
    }
}
