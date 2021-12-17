package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HP
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 *
 * 先排序，再插队；根据第一个元素从大到小排序，即高个子在前，低个子在后，然后在根据前k个的大小将元素依次插入进去
 */
public class No_406_reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (people1, people2) -> {
            if (people1[0] == people2[0]) {
                //两人身高相同，则k值从小到大
                return people1[1] - people2[1];
            } else {
                //两人身高不同，高的在前低的在后
                return people2[0] - people1[0];
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            if (list.size() > people[i][1]) {
                list.add(people[i][1], people[i]);
            } else {
                list.add(list.size(), people[i]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}