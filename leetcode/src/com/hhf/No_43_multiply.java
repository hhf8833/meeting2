package com.hhf;

import java.util.Arrays;

/**
 * @author HP
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
public class No_43_multiply {
    public String multiply(String num1, String num2) {
        int n1=num1.length(),n2=num2.length();
        int[] nums = new int[n1+n2];
        for (int i = n2-1; i >=0 ; i--) {
            for (int j = n1-1; j >=0 ; j--) {
                int p1=i+j,p2=i+j+1;
                int cur = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                int sum = cur+nums[p2];
                nums[p2] = sum%10;
                nums[p1] += sum/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0&&i==0){
                continue;
            }
            sb.append(nums[i]);
        }


        return sb.toString();
    }
}
