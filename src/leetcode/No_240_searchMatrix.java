package leetcode;

/**
 * @author HP
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 法一，逐行二分查找
 * 法二：类似于二分查找树，当前节点左边逐步变小，往右逐步变大
 *       这里二维数组向左逐步变小，向下逐步变大
 */
public class No_240_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int col = matrix[0].length -1 ;
        int row = 0;
        while (col>=0){
            if (target<matrix[row][col]){
                col--;
                continue;
            }
            int start= 0,end=n-1;
            while (start<=end){
                int mid = (start+end)/2;
                if (matrix[mid][col] == target){
                    System.out.println(matrix[mid][col]);
                    return true;
                }else if (target<matrix[mid][col]){
                    end=mid-1;
                }else {
                    start=mid+1;
                }
            }
            col--;
        }
        return false;
    }
}
