package com.offer.ali;

import java.util.Arrays;
import java.util.Scanner;

public class No_2021FourStar_3 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long res =geshu(n,m);
        System.out.println(res);
    }
    public static long geshu(int n,int m){
        long[][] dp = new long[n+1][m+1];
        int mod = (int) (Math.pow(10,9)+7);
        dp[0][0]=1;
        //节点数为0的树高度是任意的
        Arrays.fill(dp[0],1);
        for(int i =1; i <=n;i++ ){
            for(int j =1;j<=m;j++){
                for(int k =0;k<i;k++){
                    dp[i][j] = (dp[i][j]+(dp[k][j-1] *dp[i-1-k][j-1])%mod)%mod;
                }
            }
        }
        return dp[n][m];
    }
}
