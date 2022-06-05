package com.offer;

import java.util.LinkedHashMap;
import java.util.Map;

public class No_50_firstUniqChar {
    public char firstUniqChar(String s) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(int i =0;i< s.length(); i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue()==1){
                return entry.getKey();
            }
        }

        return ' ';
    }
}
