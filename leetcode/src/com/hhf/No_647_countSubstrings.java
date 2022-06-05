package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 *
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串
 */
public class No_647_countSubstrings {
    static int res;
    public  static  int countSubstrings(String s) {
        // 中心扩展法
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < 2 * n -1 ; i++) {
            int left = i/2;
            int right = left+i%2;
            while(left>=0 &&right<n && s.charAt(left) == s.charAt(right)){
                left--;
                right++;
                ans++;
            }
        }
        return ans;
    }
    public static int countSubstrings2(String s) {

        if (s == null || s.length() < 1) {
            return 0;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return res;
    }

    public static void expandAroundCenter(String s, int left,  int right) {
            while (left >= 0 && right < s.length() && s.charAt(left)== s.charAt(right)) {
            res++;
            --left;
            ++right;
        }
    }
    @Test
    public void test(){
        String str = "aaa";
        int res = countSubstrings2(str);
        System.out.println(res);
        List<Object> objects = Arrays.asList(1,2,3);
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(integers);
        StringBuffer stringBuffer;

    }
}
