package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author HP
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 方法一：
 * 使用采用小顶堆 hashmap存入每一个数字的次数，再放入小顶堆
 *
 * 方法二：
 * 能够使用小顶堆的的话则也能使用快排的变形 如题215
 */
public class No_347_topKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length==1){
            return nums;
        }
        Map<Integer,Integer> numsMap = new HashMap<>();
        for (int num :
                nums) {
            if (!numsMap.containsKey(num)){
                numsMap.put(num,1);
            }else {
                numsMap.put(num,numsMap.get(num)+1);
            }

        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->{
            return numsMap.get(a)-numsMap.get(b);
        });
        for (Map.Entry<Integer, Integer> entry :
                numsMap.entrySet()) {
            if (pq.size()<k){
                pq.add(entry.getKey());
            }else {
                if (numsMap.get(pq.peek())<numsMap.get(entry.getKey())){
                    pq.poll();
                    pq.add(entry.getKey());
                }
            }
        }
        int[] array = new int[pq.size()];
        int i =0;
        while (!pq.isEmpty()){
            array[i] = pq.poll();
            i++;
        }
        return array;
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,1,1,2,2,3};
        int[] res = topKFrequent(nums,2);
        float f =1.0f;
        int i =(int)f;
        int ii =0;
        float ff =ii;
        System.out.println(Arrays.toString(res));
        Integer i1 =1;
        Integer h1 =1;
        System.out.println(i1==h1);
        Class<No_0_Singleton> no_0_singletonClass = No_0_Singleton.class;
        System.out.println(no_0_singletonClass.getName());

    }
}
