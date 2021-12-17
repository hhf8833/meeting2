package test1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test4 {
    public static void main(String[] args) {
        int[] nums  =new int[]{4,3,2,5,6,1};
        int index = nums[0];
        int j =-1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]<index){
                j++;
                int tmp = nums[j]; nums[j]=nums[i]; nums[i]=tmp;
            }
        }
        int tmp = nums[j+1]; nums[j+1]=index; nums[nums.length-1]=tmp;
        System.out.println(Arrays.toString(nums));
        PriorityQueue<Integer> queue = new PriorityQueue<>(2);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        System.out.println(queue);
    }
}
