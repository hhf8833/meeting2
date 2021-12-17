package com.hhf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */
public class No_229_majorityElement {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
/*        if(nums.length <= 2){
            for (int e :
                    nums) {
                res.add(e);
            }
            return res;
        }*/
        int candidate1 = 0,count1=0;
        int candidate2 = 0,count2=0;
        //分配
        for (int num:
             nums) {
            if(count1>0 && candidate1 ==num ){
                count1++;
            }else if (count2>0 && candidate2==num ) {
                count2++;
            }else if ( count1==0){
                candidate1=num;
                count1++;
            }else if( count2 == 0){
                candidate2 =num;
                count2++;
            }else {
                //1，2和num都相同
                count1--;
                count2--;
            }

        }
        //计数
        int resCount1 = 0;
        int resCount2 = 0;
        for (int num:
             nums) {
            if (num == candidate1){
                resCount1++;
            }
            if (num==candidate2){
                resCount2++;
            }
        }
        if (count1>0 && resCount1>nums.length/3){
            res.add(candidate1);
        }
        if (count2>0 && resCount2>nums.length/3){
            res.add(candidate2);
        }
        return res;
    }

    public static void main(String[] args) {
        No_229_majorityElement maj = new No_229_majorityElement();
        int[] nums = new int[]{0,3,4,0};
        List<Integer> res = maj.majorityElement(nums);
        System.out.println(res);
    }
}
