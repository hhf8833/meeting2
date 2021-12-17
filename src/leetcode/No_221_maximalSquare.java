package leetcode;

/**
 * @author HP
 * 参考第85题求最大矩形面积的暴力法，仅作一点点修改
 * 方法是只用一个一维数组，每次求的都是当前位置的下一个 ，dp【0】默认是0，
 */
public class No_221_maximalSquare {

    /* 参考85题所做 但是速度比较慢
    public int maximalSquare(char[][] matrix) {
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
                //注意这里要写在外面，因为求的是该列最小的那个，循环的时候需要和当前已经比较过的最小的进行比较这，如width从上到下是123， 当从下到上循环到1的时候应该是去拿当前最小2去比，而非3去比
                if(width[row][col]!='0'){
                    int minWidth=width[row][col];
                    //向上不断求出最小值
                    for (int upRow =row; upRow >= 0; upRow--) {
                        int height = row-upRow+1;
                        minWidth = Math.min(minWidth,width[upRow][col]);
                        int side =Math.min(height,minWidth);
                        maxArea=Math.max(maxArea,side*side);
                    }
                }
            }
        }
        return maxArea;
    }*/

    public int maximalSquare(char[][] matrix) {
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;
        int[] dp = new int[width + 1];
        for (char[] wid :
                matrix) {
            //辅助西北角/左上角的数
            int northWest = 0;
            for (int i = 0; i < width; i++) {
                //这里要注意！！！ nextNorthWest保存的是下一轮的西北角(因为此时的dp数组还是上一轮即上一行留下的结果，所以这里直接取了，同时他也是本轮的正北的数)
                //一维数组的好处就是直接拿来上轮的数用，不用在开辟空间了 速度也会快
                int nextNorthWest = dp[i + 1];
                int west = dp[i + 1];
                if (wid[i] == '1') {
                    //别忘了最后要+1
                    dp[i + 1] = Math.min(Math.min(dp[i], west), northWest)+1;
                    maxSide = Math.max(maxSide, dp[i + 1]);
                } else {
                    dp[i + 1] = 0;
                }
                northWest = nextNorthWest;
            }
        }
        return maxSide*maxSide;
    }
}
