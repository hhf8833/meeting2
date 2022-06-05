package com.hhf.classification.dp.tanxin;

/**
 * @author HP
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * 示例 1:
 *
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 贪心算法：
 * 如果从i为起点走不到j，那么i到j中间的任何位置为起点都走不到j，那么我们要让j+1为起点试试
 * 证明：如果从i刚好无法走到j，那么ij之间的任一一个节点也都无法走到j（一旦sumVal为负数就表示无法走到），想象一个折线图从i开始纵坐标为0，线先增后减直到j恰好为负，那么上下移动这个图 让ij中的任一一个k点为0起始点必然到达不了j
 */
public class No_134_canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int n =gas.length;
        for (int i = 0; i < n ; i++) {
            sum+=gas[i]-cost[i];

        }
        if (sum<0){
            return -1;
        }
        //新的起点
        int start =0;
        int sumVa =0;
        for (int i = 0; i < n; i++) {
            sumVa+=gas[i]-cost[i];
            if (sumVa<0){
                sumVa=0;
                start =i+1;
            }
        }
        return start ==n ? 0:start;
    }
}
