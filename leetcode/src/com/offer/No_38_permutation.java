package com.offer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No_38_permutation {
    List<String> list = new ArrayList<String>(){{add("asd");}};
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return list.toArray(new String[list.size()]);
    }
    public void dfs(int x){
        if (x == c.length-1){
            list.add(new String(c));
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])){
                continue;
            }
            set.add(c[i]);
            swap(i,x);
            dfs(x+1);
            swap(i,x);
        }
    }
    public void swap(int a ,int b ){
        char temp = c[a];
        c[a] = c[b] ;
        c[b] = temp;
    }

    @Test
    public  void  test(){
        No_38_permutation ppp = new No_38_permutation();
        ppp.permutation("abbc");
    }
}
