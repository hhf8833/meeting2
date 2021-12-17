package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 方法一，空间复杂度为2n
 *  再开辟两个数组，第一个来放当前第i个元素的左边的数的乘积，第二个来放右边的乘积
 *  三次循环，前两次分别初始化数组，第三次输出
 *
 *  方法二：
 *  因为结果数组不算入空间复杂度，只用一个数组即可
 *  首先res数组放入左边的所有乘积，然后再次遍历，只用一个变量表示右边数组，类似于动态规划优化思想
 */
public class No_238_productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];
        left[0] =1; right[n-1]=1;
        for (int i = 1; i < n; i++) {
            left[i]=nums[i-1]*left[i-1];
        }
        for (int i = n-2; i >=0; i--) {
            right[i] = right[i+1]*nums[i+1];
        }
        for (int i = 0; i < n; i++) {
            res[i]=left[i]*right[i];
        }
        return res;
    }
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int right=1;
        int[] res = new int[n];
        res[0] =1;
        for (int i = 1; i < n; i++) {
            res[i]=nums[i-1]*res[i-1];
        }
        for (int i = n-1; i >=0; i--) {
            res[i]=res[i]*right;
            right=right*nums[i];
        }
        return res;
    }
    @Test
    public void test(){
        No_238_productExceptSelf  pro= new No_238_productExceptSelf();
        int[] nums=new int[]{1,2,3,4};
        int[] res = pro.productExceptSelf2(nums);
        System.out.println(Arrays.toString(res));
        Integer i1 =3,i2 =4;
        System.out.println(i1.compareTo(i2));
    }

}
