package com.offer;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;

/**
3 2
11 22
22 33
33 44
14 41
20 5
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        int M =sc.nextInt();
        //sc.nextLine();
        int[][] nn = new int[N][2];
        int[][] mm = new int[M][2];
       // System.out.println("len+"+nn.length);
       // System.out.println("len+"+mm.length);
        for (int i = 0; i < N; i++) {
            //System.out.println("输入N："+i);
            for (int j = 0; j < 2; j++) {
                nn[i][j] =sc.nextInt();
            }
            //sc.nextLine();
        }

        for (int i = 0; i < M; i++) {
            //System.out.println("输入M："+i);
            for (int j = 0; j < 2; j++) {
                mm[i][j] =sc.nextInt();
            }
            //sc.nextLine();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(nn[i][j]+"\t");
            }
            System.out.println();
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(mm[i][j]+"\t");
            }
            System.out.println();
        }

       // stack(nn,mm);
    }
    public static void stack(int[][] nn,int[][] mm){
        Arrays.sort(nn,(int[] num1,int[]num2)->{
            return num2[0]-num1[0];
        });
        Arrays.sort(mm,(int[] num1,int[]num2)->{
            return num2[0]-num1[0];
        });
/*        for (int i = 0; i < nn[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(nn[i][j]+"\t");
            }
            System.out.println();
        }
        for (int i = 0; i < mm[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(mm[i][j]+"\t");
            }
            System.out.println();
        }*/
        int i=0,j=0,succ=0,res=0;
        while(i < nn[0].length&& j<mm[0].length){
            if (nn[i][0]>mm[i][1]){
                succ++;
                if (mm[i][0]>nn[i][1]){
                    res++;
                }
                i++;
                j++;
            }else {
                j++;
            }
        }
        if (succ ==nn[0].length){
            System.out.println(res);
        }else {
            System.out.println(-1);
        }
    }
}
