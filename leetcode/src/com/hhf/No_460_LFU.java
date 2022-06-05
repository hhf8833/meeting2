package com.hhf;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class No_460_LFU {


    class LFUCache {

        HashMap<Integer,Integer> keyToVal;
        HashMap<Integer,Integer> keyToFre;
        HashMap<Integer, LinkedHashSet<Integer>> freTokeylist;
        //最小频次
        int minFreq;
        int cap;
        public LFUCache(int capacity) {
            keyToFre = new HashMap<>();
            keyToVal = new HashMap<>();
            freTokeylist = new HashMap<>();
            cap=capacity;
            minFreq =0;
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key) ){
                return -1;
            }
            updateFre(key);

            return keyToVal.get(key);
        }

        public void put(int key, int value) {
            if (this.cap <= 0) {
                return;
            }
            if (keyToVal.containsKey(key)){
                keyToVal.put(key,value);
                updateFre(key);
                return;
            }
            if (keyToVal.size()>=cap){
                removeMinFreKey();
            }
            keyToVal.put(key,value);
            keyToFre.put(key,1);
            // LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
            // hashSet.add(key);
            // freTokeylist.put(1,hashSet);
            freTokeylist.putIfAbsent(1,new LinkedHashSet<>());
            freTokeylist.get(1).add(key);
            minFreq =1;
        }
        public void updateFre(int key){
            Integer fre = keyToFre.get(key);
            keyToFre.put(key,fre+1);
            //删除老的，放入新的列表里面
            freTokeylist.get(fre).remove(key);
            freTokeylist.putIfAbsent(fre + 1, new LinkedHashSet<>());
            freTokeylist.get(fre+1).add(key);
            if (freTokeylist.get(fre).isEmpty()){
                freTokeylist.remove(fre);
                if (minFreq ==fre){
                    minFreq++;
                }
            }

        }
        private void removeMinFreKey() {
            LinkedHashSet<Integer> set = freTokeylist.get(minFreq);
            Integer key = set.iterator().next();
            set.remove(key);
            if (set.isEmpty()){
                freTokeylist.remove(minFreq);
                /* 这里不用更新minfreq  因为新插入的时候必然是最小的1
               while (!freTokeylist.containsKey(minFreq)){
                    minFreq++;
                }*/
            }
            keyToVal.remove(key);
            keyToFre.remove(key);

        }
    }

    public static void main(String[] args) {
        No_460_LFU No_460_LFU = new No_460_LFU();
        LFUCache lfuCache = No_460_LFU.new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        int i = lfuCache.get(1);
        System.out.println(i);
        lfuCache.put(3,3);
        int i1 = lfuCache.get(2);
        int i2 = lfuCache.get(3);
        System.out.println(i1);
        System.out.println(i2);
        lfuCache.put(4,4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);

    }

}
