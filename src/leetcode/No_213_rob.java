package leetcode;

/**
 * @author HP
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *
 * 这里的房间是一个圆形的，所以对比198题，该题要分为两种情况一种是不偷第一间则偷盗范围是（1，n-1），另一种是偷第一间的则偷盗范围是（0，n-2）
 * 边界条件是 第一间和第二间  循环从第三间开始
 */
public class No_213_rob {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n==1){
            return nums[0];
        }
        if(n==2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(robSome(nums,1,n-1),robSome(nums,0,n-2));
    }
    public int robSome(int[] nums,int start, int end) {
        int first = nums[start],second = Math.max(first,nums[start+1]);
        for (int i = start+2; i <= end; i++) {
            int temp = second;
            second= Math.max(first+nums[i],second);
            first =temp;
        }
        return Math.max(first,second);
    }
}
