package com.hhf;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。*/

//单调递增栈
//左右边界的意思是以当前柱子为矩形的高，他向左向右的边界！！！！！！
//当前柱子高度与栈顶比，大于栈顶的话就依次入栈，
//如果小于的话就依次出栈，直到找到第一个比当前高度小的栈顶元素，并将其入到栈顶，在此同时每次要计算出栈元素的位置，去求矩形长度
//在求矩形面积时候，右边界始终是在最后，而左边界是变化的这就让矩形面积是变化的，例如栈中1，2，3 遇到0的时候，需要依次出栈且每次都要求对应的面积，
//高度是在变化，但是右边界是不变的，例如2出栈时右边界仍然是对应3的位置，但是左边界实际上已经是2了，这样矩形的宽则为2，高是2
/*      1	42. 接雨水（困难）	暴力解法、优化、双指针、单调栈
        2	739. 每日温度（中等）	暴力解法 + 单调栈
        3	496. 下一个更大元素 I（简单）	暴力解法、单调栈
        4	316. 去除重复字母（困难）	栈 + 哨兵技巧（Java、C++、Python）
        5	901. 股票价格跨度（中等）	「力扣」第 901 题：股票价格跨度（单调栈）
        6	402. 移掉K位数字
        7	581. 最短无序连续子数组*/


public class No_84_largestRectangleArea {

    public int largestRectangleArea(int[] heights) {

        if (heights.length==0){
            return 0;
        }
        //Deque双边队列
        Deque<Integer> stack = new ArrayDeque<>(heights.length);
        int[] newHeights =new int[heights.length+1];
        int maxArea = 0;
        //使用新的数组的原因是当遇到1，2，3，4这种依次递增的图到最后都不会出栈，因此开辟一个新数组，最后一位多一个零，让其能够全部出栈
        for (int i = 0; i < heights.length ; i++) {
            newHeights[i] = heights[i];
        }
        System.out.println(Arrays.toString(newHeights));
        //循环newheights
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()]>newHeights[i]){
                //因为是单调递增栈，所以在出栈的时候，栈内所有的元素都是单调递增的
                int cur = stack.pop();
                int left =0;
                if (!stack.isEmpty()){
                    //这里为什么要用peek+1而不直接用cur，因为存在栈在中间时刻为零的情况4,2,0,3,2,5，
                    // 当其为零时，栈内全空，此时会把0的位置入栈，在3入栈后又出栈，然后压入2，5，
                    // 计算长度如果不按peek的位置，那么0的位置的后一个因为已经超过出栈就找不到了，但是记录了0的位置，知道其后面还是有一个的
                    left=stack.peek()+1;
                }
                //因为i位置对应的是比栈中小的元素，
                int right = i-1;
                maxArea=Math.max(maxArea,(right-left+1)*newHeights[cur]);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,2};
        int res ;
        No_84_largestRectangleArea lag =new No_84_largestRectangleArea();
        res=lag.largestRectangleArea(heights);
        System.out.println(res);
    }
}
