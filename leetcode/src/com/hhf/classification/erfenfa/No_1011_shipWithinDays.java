package com.hhf.classification.erfenfa;

import org.junit.jupiter.api.Test;

/**
 * @author HP
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。

 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 同类题857
 *
 * 求最低的速度（载运能力），速度越大，需要的时间days越少，所以是递减的，求出左边界
 */
public class No_1011_shipWithinDays {
    public static int shipWithinDays(int[] weights, int days) {
        int leftSpeed = 0;
        int rightSpeed = 0;
        for (int i = 0; i < weights.length; i++) {
            rightSpeed+=weights[i];
            leftSpeed= Math.max(leftSpeed,weights[i]);
        }
        while (leftSpeed < rightSpeed){
            int mid =leftSpeed+(rightSpeed-leftSpeed)/2;
            if (needTime(weights,mid) >days){
                //说明当前需要的时间大，需要速度更快点
                leftSpeed=mid+1;
            }else {
                rightSpeed =mid;
            }
        }
        return leftSpeed;
    }

    public static int needTime(int[]weights,int speed){
        int time =0;
        int i =0,n= weights.length;
        while (i<n){
            int curWeightSum=0;
            while (i < n ){
                curWeightSum+=weights[i];
                if (curWeightSum>speed){
                    break;
                }
                //curWeightSum + weights[i] <=speed
                i++;
            }
            time++;
        }
        return time;
    }
    @Test
    public void test(){
        int[] weights=new int[]{3,2,2,4,1,4};
        int days =3;
        shipWithinDays(weights,days);
    }
}
