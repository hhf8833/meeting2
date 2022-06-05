package com.hhf.classification.qianzhuihe;

public class No_560_1_takeCoins {

    public int takeCoins(int[] list, int k) {
        // Write your code here
        int n = list.length;
        int[] preSum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + list[i];
        }
        for (int i = 0; i <= k; i++) {
            int left = i;
            int right = k - i;
            int cur = preSum[n] - preSum[n - right] + preSum[left];
            ans = Math.max(ans, cur);
        }

        return ans;
    }
}
