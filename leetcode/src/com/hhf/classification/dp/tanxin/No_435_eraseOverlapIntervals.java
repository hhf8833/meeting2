package com.hhf.classification.dp.tanxin;

import java.util.Arrays;

/**
 * @author HP
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 */
public class No_435_eraseOverlapIntervals {
    int res ;
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(int[] i1,int[] i2)->{
            if (i1[1]==i2[1]){
                return i1[0]-i2[0];
            }else {
                return i1[1]-i2[1];
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(intervals[i][j]+"\t");
            }
            System.out.println();
        }
        int[] x = intervals[0];
        for (int i = 0; i < intervals.length; i++) {
            if (x[1]<=intervals[i][1] &&intervals[i][0]<x[1]){
                res++;
            }else {
                x=intervals[i];
            }

        }
        return res;
    }
}
