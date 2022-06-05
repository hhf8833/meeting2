package com.offer.ali;

import java.util.Scanner;

public class No_2021FourStar_5 {
    static long res;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        long[][] employee = new long[n][2];
        for (int i = 0; i < n; i++) {
            employee[i][0] = sc.nextLong();
            employee[i][1] = sc.nextLong();
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                long x = (employee[i][0]+employee[j][0])/2;
                long y = (employee[i][1]+employee[j][1])/2;
                res = Math.max(res,Math.min(x,y));
            }
        }
        System.out.println((double) res);
    }
    static class aa{
        public void bb(){

        }
    }
}
