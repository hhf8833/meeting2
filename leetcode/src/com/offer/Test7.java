package com.offer;

import java.util.*;



public class Test7 {
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> tmp = new ArrayList<>();

    public static void trace(int[] nums,int cur,int curSum){
        if (tmp.size()>=2 && (curSum & 1)==0){
            res.add(new ArrayList<>(tmp));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = cur; i < nums.length; i++) {
            if (!tmp.isEmpty() && tmp.get(tmp.size()-1) > nums[i]){
                continue;
            }
            if (set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            tmp.add(nums[i]);
            trace(nums,i+1,curSum+ nums[i]);
            tmp.remove(tmp.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        Arrays.sort(nums);
        trace(nums,0,0);
        System.out.println(res);
        String string = Integer.toBinaryString(25);

        System.out.println(string);
        int index =0;
        int res =0;
        while (index < string.length()){
            int len =0;
            while (index < string.length() &&string.charAt(index) =='0'){
                len++;
                index++;
            }
            res = Math.max(res,len);
            index++;
        }
        System.out.println(res);
        Character.isUpperCase('A');
        Character.isLowerCase('b');
//        Character.toUpperCase();
//        Character.toLowerCase();
        int[][] gri = new int[][]{};
        System.out.println(Arrays.toString(gri) + " " + gri[0].length);
    }

}
