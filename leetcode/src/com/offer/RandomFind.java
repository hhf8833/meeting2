package com.offer;

import java.util.BitSet;
import java.util.Random;

public class RandomFind {
    public static void main(String[] args) {
        Random random = new Random();

        //System.out.println(i);
        BitSet bs = new BitSet(100000000);
        for (int j = 0; j < 10000000; j++) {
            int i = random.nextInt(100000000);
            bs.set(i);
        }
        int count =0;
        for (int i = 0; i < 100000000; i++) {
            if (bs.get(i)){
               count++;
            }
        }
        System.out.println(count);
    }
}
