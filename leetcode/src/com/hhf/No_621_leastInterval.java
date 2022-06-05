package com.hhf;

import java.util.Map;
import java.util.Scanner;

/**
 * @author HP
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间（一个做完之后再需要n的冷却时间），
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 * 示例 1：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 *
 * 自己乱思考：需要知道有几种task，写到map里面并求出次数
 *
 * 利用桶，桶的个数是最多的任务个，桶里能放的任务种类是n+1个
 * 分为有空闲时间(N-1)*(n+1)+x和没有空闲时间tasks.length()的两种，
 * 如图621.1.png这个图中所示，是有空闲时间的，但是他们最符合是(6-1)*3+2=17，是要大于没有空闲时间所求数14的，因为空闲时间内没有任务能够执行
 * 当没有空闲时间的时候，两者是一样的，要都符合
 *
 * 这个题一些地方难以理解
 *
 *
 */
public class No_621_leastInterval {
    public int leastInterval(char[] tasks, int n) {
       int countMaxTask =0;
       int countMaxTasks = 0;
       int[] nums = new int[26];
        for (char c:
             tasks) {
            nums[c-'A']++;
            countMaxTask = Math.max(nums[c-'A'],countMaxTask);
        }
        //求出所有任务中能和maxtask数量相同的任务的个数
        for (int i = 0; i <26 ; i++) {
            if (nums[i] == countMaxTask) {
                countMaxTasks++;
            }
        }

        return Math.max(tasks.length,(countMaxTask-1)*(n+1)+countMaxTasks);
    }

}
