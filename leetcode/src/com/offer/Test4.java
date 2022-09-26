package com.offer;


import org.junit.jupiter.api.Test;
import org.omg.CORBA.portable.ValueInputStream;


import java.util.*;
import java.util.stream.Stream;

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
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream = list.stream();
//        stream.mapToInt()
        System.out.println((int)'a');
        Test6 test6 = new Test6();
        Test6.Inn inn = test6.new Inn();

        System.out.println(3);

        int l = 0xFFFFFFF1;
        System.out.println(~l);

        int[] test = test(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11});
        System.out.println(Arrays.toString(test));
        BitSet bitSet = new BitSet(1000000000);
        int[] ints = {12, 24, 8, 32};
        for (int j = 0; j < ints.length; j++) {
            bitSet.set(ints[j]);
        }
        int cur = 1000000000;
        while (cur>=0){
            if (bitSet.get(cur)){
                System.out.println(cur);
            }
            cur--;
        }

        System.gc();
        System.runFinalization();

    }
    @Test
    public void test1(){
        int[]nums = new int[]{5,8,3,7,4,2,9,6,1};
        partition(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    private void partition(int[] nums, int start, int end) {
        if (start>=end){
            return;
        }
        int index = nums[start];
        int i =start;
        for (int j = start+1; j <= end; j++) {
            if (nums[j] < index){
                i++;
                int tmp =nums[j]; nums[j] = nums[i]; nums[i] = tmp;
            }
        }
        int tmp =nums[i]; nums[i] = index; nums[start] = tmp;
        partition(nums,start,i-1);
        partition(nums,i+1,end);
    }

    public static int[] test(int[] A,int[] B){
        Arrays.sort(A);
        boolean[] visited = new boolean[A.length];
        int[]  res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            boolean flag = false;
            for (int j = 0; j < A.length; j++) {
                if (A[j] > B[i] && !visited[j]){
                    res[i] = A[j];
                    visited[j] = true;
                    flag =true;
                    break;
                }
            }
            if (!flag){
                for (int j = 0; j < A.length; j++) {
                    if (!visited[j]){
                        res[i] = A[j];
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
        return res;
    }

}
