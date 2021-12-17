package com.hhf;

/**
 * @author HP
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 * 方法一：
 * 采用142题环形链表的思想，使用快慢指针，但是因为是数组，所以要建立映射关系，使其看作一个链表，
 *  这里要注意条件： n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n）！！！，所以肯定是数组内的值作为下标是存在的
 *
 *  方法二:二分查找
 *  二分查找是必须要对有序的数组才行，而题目中nums是无序的但是因为数组内容都在1--n之间，1--n是有序的，所以对其进行二分查找，
 *  因为多了那么一个，所以判断mid=（1+n）/2，数组中的元素有多少个是小于中间个数mid的，判断在该元素在左还是右
 */
public class No_287_findDuplicate {
    public int findDuplicate(int[] nums) {
        int fast=0,slow =0;
        while (true){
            slow=nums[slow];
            fast=nums[nums[fast]];
            if (fast == slow){
                break;
            }
        }
        slow=0;
        while (fast!=slow){
            slow=nums[slow];
            fast=nums[fast];
        }
        return fast;
    }
    //二分查找
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        //数字都在 1 到 n 之间（包括 1 和 n）,数组n + 1 个整数，所以left=1，right=n-1即为n
        int left = 1;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int num :
                    nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
