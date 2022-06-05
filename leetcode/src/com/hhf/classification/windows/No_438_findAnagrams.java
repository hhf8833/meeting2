package com.hhf.classification.windows;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class No_438_findAnagrams {
    /*
    该方法不行，因为每次在当前list里面修改，list是引用传递，导致里面的数为空，每次都要重新赋值，过于麻烦
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res =new ArrayList<>();
        int lenp=p.length();
        int lens=s.length();
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < lenp; i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i),0)+1);
        }

        for (int i = 0; i < lens-lenp+1; i++) {
            int count = lenp;
            HashMap<Character,Integer> currMap = new HashMap<>();
            currMap =map;
            for (int j = i; j <i+lenp ; j++) {
                if (currMap.containsKey(s.charAt(j)) && currMap.get(s.charAt(j))>0){
                    currMap.put(s.charAt(j),currMap.get(s.charAt(j))-1);
                    count--;
                    if (count ==0){
                        res.add(i);
                        break;
                    }
                }
            }
        }
        return res;
    }*/
    //该方法每次比较都是直接比较整个数组
    public List<Integer> findAnagrams(String s, String p) {
        int lens = s.length(),lenp = p.length();
        if (lenp>lens){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        //初始化先将两个字符串前lenp个字符进行比较
        for (int i = 0; i < lenp; i++) {
            sCnt[s.charAt(i)-'a']++;
            pCnt[p.charAt(i)-'a']++;
        }
        if (Arrays.equals(sCnt,pCnt)){
            res.add(0);
        }
        for (int i = lenp; i < lens; i++) {
            sCnt[s.charAt(i-lenp)-'a']--;
            sCnt[s.charAt(i)-'a']++;
            if (Arrays.equals(sCnt,pCnt)){
                res.add(i-lenp+1);
            }
        }
        return res;
    }
//  滑动窗口
    public List<Integer> findAnagrams2(String s, String p) {
        int lens = s.length(),lenp = p.length();
        if (lenp>lens){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] needArr = new int[26];

        for (int i = 0; i < lenp; i++) {
            needArr[p.charAt(i)-'a']++;
        }
        int left =0;
        for (int right = 0; right < lens; right++) {
            int curRight = s.charAt(right)-'a';
            needArr[curRight]--;
            //这里sCnt[curRight] > pCnt[curRight]
            // 大于成立即s字符串中当前Right对应的字符（在数组中用curRight表示），
            // 1.在s中有但在p中没有，该情况对应的窗口是没有的异位词的，因此要不断地收缩s的左边界直至窗口为0，即直到left和right重合
            // 2.又或者该位置字符在s中重复了,要不断地收缩s的左边界可能直至窗口为0，也可能只收缩一点点就又符合了
            while (needArr[s.charAt(right)-'a']<0){
                needArr[s.charAt(left)-'a']++;
                left++;
            }
            if (right-left+1==lenp){
                res.add(left);
            }
        }
        return res;
    }
    @Test
    public void test(){
        String s="cbacbabacd";
        String p= "abc";
        List<Integer> res = findAnagrams2(s,p);
        System.out.println(res);
    }
}
