package leetcode.No_85_maximalRectangle;


/*
85. 最大矩形
        给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
*/
//方法一：暴力法
//求出以当前位置ij为右下角的矩形，遍历到该位置，先求矩形的长，根据该行前一个判断，位置ij的如果为1时则能构成矩形，需要加上前一位置已经存储的数量（动态规划的思想），
// 矩形的长找到之后，需要从该元素ij向纵向进行循环（i，j-1；i，j-2；。。。。。），每次都去找循环的位置和当前最小值的宽度两者进行比较找出更小的作为矩形真正的长，
// 求出矩形的高，并记录最大值

//方法二，利用84题去解
//循环行每次将该行往上看作一个一个的柱状图，以此求出最大面积
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class maximalRectangleTest {
     //方法一：暴力法
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length==0){
            return 0;
        }
        int maxArea = 0;
        //构建辅助数组
        int[][] width = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] != '0'){
                    if (col == 0){
                        //补全第一列
                        width[row][col]=1;
                    }else {
                        //每次更新高度为1矩形的最长长度
                        width[row][col]=width[row][col-1]+1;
                    }
                }else {
                    width[row][col]=0;
                }
                //注意这里要写在外面，因为求的是该列最小的那个，循环的时候需要和当前已经比较过的最小的进行比较这，如width从上到下是123，
                // 当从下到上循环到1的时候应该是去拿当前最小2去比，而非3去比
                if(width[row][col]!='0'){
                    int minWidth=width[row][col];
                    //向上不断求出最小值
                    for (int upRow =row; upRow >= 0; upRow--) {
                        int height = row-upRow+1;
                        minWidth = Math.min(minWidth,width[upRow][col]);
                        maxArea=Math.max(maxArea,height*minWidth);
                    }
                }
            }
        }
        return maxArea;
    }
    //方法二，利用84题去解
     public int maximalRectangle2(char[][] matrix) {
         if (matrix.length==0){
             return 0;
         }
         int maxArea =0;
         int[] heights=new int[matrix[0].length];
         for (int row = 0; row < matrix.length; row++) {
             for (int col = 0; col < matrix[0].length; col++) {
                 if (matrix[row][col] == '1'){
                     heights[col]++;
                 }else {
                     heights[col]=0;
                 }
             }
             maxArea=Math.max(maxArea,largestRectangleArea(heights));
         }
         return maxArea;
     }
     //84题代码
    public int largestRectangleArea(int[] heights) {

        if (heights.length==0){
            return 0;
        }
        //Deque双边队列
        Deque<Integer> stack = new ArrayDeque<>(heights.length);
        int[] newHeights =new int[heights.length+1];
        int maxArea = 0;
        for (int i = 0; i < heights.length ; i++) {
            newHeights[i] = heights[i];
        }
        System.out.println(Arrays.toString(newHeights));

        //循环newheights
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()]>newHeights[i]){
                int cur = stack.pop();
                int left =0;
                if (!stack.isEmpty()){
                    left=stack.peek()+1;
                }
                int right = i-1;
                maxArea=Math.max(maxArea,(right-left+1)*newHeights[cur]);
            }
            stack.push(i);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        //char[][] matrix =new char[][]{{'0','1'},{'1','0'}};
        char[][] matrix =new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        maximalRectangleTest maxmal = new maximalRectangleTest();
        int res = maxmal.maximalRectangle(matrix);
        System.out.println(res);
    }
}
