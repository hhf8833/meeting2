package leetcode;

/**
 * @author HP
 * 338. 比特位计数
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *方法一：
 * 依次计算第i个位置的数，计算时，不断取余并记录即可
 *
 * 方法二：
 * 类似于动态规划，第i个位置为奇偶来判断，当为偶数（num&1 ==0）其1的个数和其num除于2的个数一样，为奇数是，为前一个个数加1
 */
public class No_338_countBits {
    /*public int[] countBits(int n) {
        int[] nums = new int[n+1];
        for (int i = 0; i <=n; i++) {
            nums[i]=calculate(i);
        }
        return nums;
    }
    public int calculate(int num){
        int count =0;
*//*        while (num!=0){
            if (num%2 ==1){
                count++;
            }
            num = num/2;
        }*//*
        while (num !=0){
            num &=num-1;
            count++;
        }
        return count;
    }*/
    public int[] countBits(int n) {
        int[] nums = new int[n+1];
        for (int i = 0; i <=n; i++) {
            if ((i &1) ==0){
                //为偶数时候
                nums[i] = nums[i >> 1];
            }else {
                nums[i] = nums[i - 1] + 1;
            }
        }
        return nums;
    }

}
