package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No_763_partitionLabels {
    //该方法比较麻烦，利用字典将所有s的每一个字符数量都求出来，之后再次遍历利用count判断当前的区间字符是否都遍历完
    public static List<Integer> partitionLabels(String s) {
        int[] dic = new int[26];
        for(char c : s.toCharArray()){
            dic[c-'a']++;
        }
        Map<Character,Integer> map = new HashMap<>();
        //map.put(s.charAt(0),1);
        int count =0,start=0;
        List<Integer> list = new ArrayList();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.get(c) ==null){
                count++;
            }
            map.put(c,map.getOrDefault(c,0)+1);

            if(map.get(c)==dic[c-'a']){
                count--;
            }
            if(count ==0){
                list.add(i-start+1);
                start = i+1;
            }

        }
        return list;
    }
    @Test
    public void test(){
        partitionLabels("ababcbacadefegdehijhklij");
    }
}
