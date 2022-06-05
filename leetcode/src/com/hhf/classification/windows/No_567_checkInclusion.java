package com.hhf.classification.windows;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * @author HP
 */
public class No_567_checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length()>s2.length()){
            return false;
        }
        int[] needArr = new int[26];
        for (char c :
             s1.toCharArray()) {
            needArr[c-'a']++;
        }
        int i =0,j=0;
        while(j<s2.length()){

            needArr[s2.charAt(j)-'a']--;

            while (i<=j && needArr[s2.charAt(j)-'a']<0){
                needArr[s2.charAt(i)-'a']++;
                i++;
            }

            if (j-i+1==s1.length()){
                return true;
            }
            j++;
        }
        return false;
    }
}
