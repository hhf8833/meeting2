package com.offer;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;
import java.util.List;

public class Test4 {

    @Test
    public void test(){
       // String s = intToRoman(3);
        //System.out.println(s);
        System.out.println("abcd".substring(0,3));
        int i =5;
        char c = (char) (i+'0');
        System.out.println(c);
        char[] chars = new char[]{'.','.','1','m','.'};
        String s = String.valueOf(chars);
        System.out.println(s);
    }
}
