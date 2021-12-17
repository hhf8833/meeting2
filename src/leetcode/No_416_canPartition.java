package leetcode;

import org.junit.jupiter.api.Test;

/**
 * @author HP
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 *背包问题
 * dp[i][j] = 1) dp[i-1][j] ，在添加一个num的时候，状态肯定是从i-1个数来的,如果 dp[i−1][j] 为真，直接计算下一个状态
 *            2) true (nums[i]==j)
 *            3) dp[i-1][j-nums[i]] (nums[i]<==>j)
 *
 * 优化：，当前行总是参考了它上面一行 「头顶上」 那个位置和「左上角」某个位置的值。因此，我们可以只开一个一维数组，从后向前依次填表即可，前面的都是上一行留下的
 */
public class No_416_canPartition{
    public static boolean canPartition(int[] nums) {
        int n =nums.length;
        int sum =0;
        for (int num :
                nums) {
            sum+=num;
        }
        //因为是要把数组内的所有数值分成两半所以如果是奇数的话则不能分，返回
        if ((sum&1) ==1){
            return false;
        }
        int target = sum/2;
        boolean[][] dp = new boolean[n][target+1];
        if (nums[0]<target){
            //当只有第一个数的时候，刚dp【0】行仅有第num是【0】的位置为true
            dp[0][nums[0]] =true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {

                dp[i][j] = dp[i-1][j];
                if (nums[i] == j ){
                    dp[i][j] =true;
                    continue;
                }
                if (nums[i]<j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
                //这里是剪枝操作，只要第i个数的某一j为true，那么这一列往下都为true
                if (dp[i][target]) {
                    return true;
                }
            }
        }
        return dp[n-1][target];
    }
    public static boolean canPartition2(int[] nums) {
        int n =nums.length;
        int sum =0;
        for (int num :
                nums) {
            sum+=num;
        }
        //因为是要把数组内的所有数值分成两半所以如果是奇数的话则不能分，返回
        if ((sum&1) ==1){
            return false;
        }
        int target = sum/2;
        boolean[]dp = new boolean[target+1];
        dp[0] =true;

        if (nums[0]<=target){
            //当只有第一个数的时候，刚dp【0】行仅有第num是【0】的位置为true
            dp[nums[0]] =true;
        }
        for (int i = 1; i < n; i++) {
//            一旦 nums[i] <= j 不满足，可以马上退出当前循环，因为后面的 j 的值肯定越来越小，没有必要继续做判断，直接进入外层循环的下一层。
            for (int j = target; j >=nums[i] ; j--) {
                if (dp[target]){
                    return true;
                }
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }
        return dp[target];
    }
    @Test
    public void test(){
        int[] nums = new int[]{1,5,11,5};
        boolean flag = canPartition(nums);

    }
}
