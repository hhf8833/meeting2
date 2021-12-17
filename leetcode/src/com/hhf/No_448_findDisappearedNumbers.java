package com.hhf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * //方法一：用一个新数组，不再写
 *
 * 方法二： 在原有的基础上进行，注意到nums中的数的范围均在（1，n）之间，遍历这些数，将这些数作为下标，找到对应该下标的数值加上n，
 * 之后看sums中有那些位置的数是小于n的则证明该下标是在nums中
 * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 nn 取模来还原出它本来的值。
 */
public class No_448_findDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n =nums.length;
        for (int num :
                nums) {
            int x =(num-1)%n;
            nums[x]+=n;
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            //取等的原因是题中范围是1到n
            if (nums[i]<=n){
                res.add(i+1);
            }
        }
        return res;

    }
}
