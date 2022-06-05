package com.offer;

import org.junit.jupiter.api.Test;

/**
 * @author HP
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 *
 * 给定target=20，返回false
 *注意：本题与主站 240 题相同
 *
 * 从矩阵的最右边开始向左遍历，当前ij位置数据小于target的时候就向下用二分法进行查找，没有的话就再次向左遍历并向下找（这里不能直接return，
 * 因为本列虽然没有，但是不代表向左的列中没有）
 */
public class No_04_findNumberIn2DArray {
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length ==0  ){
            return false;
        }
        int m = matrix.length,n = matrix[0].length;
        int row = 0, col = n-1;
        while( col >= 0 ){
            if (target < matrix[row][col]){
                col--;
                continue;
            }
            int start =0, end = m-1;
            while(start <= end){
                int mid = (start+end)/2;
                if (target == matrix[mid][col]){
                    return true;
                }else if (target < matrix[mid][col]){
                    end =mid-1;
                }else {
                    start = mid+1;
                }
            }
            col--;
        }
        return false;
    }
    @Test
    public void test(){
/*        int[][] nums = new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };*/
        int[][] nums =new int[][]{};
        int target = 20;
        boolean res = findNumberIn2DArray(nums,target);
        System.out.println(res);
    }
}
