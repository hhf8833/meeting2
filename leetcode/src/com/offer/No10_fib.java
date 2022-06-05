package com.offer;

import org.junit.jupiter.api.Test;
import sun.net.util.IPAddressUtil;

/**
 * @author HP
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 * 直接暴力递归不行，复杂度太高，考虑使用动态规划来做（经过优化的动态规划只保留 两个空间）
 */
public class No10_fib {
    public static int fib(int n) {
        int sum ,a=0,b=1;
        for (int i = 0; i < n; i++) {
            sum = a + b ;
            a = b;
            b = sum;
        }
        return a;
    }

    @Test
    public void test(){
        int num = 44 ;
        int res = fib(num);
        System.out.println(res);
    }
}
