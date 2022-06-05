package com.hhf.classification.backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author HP
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No_752_openLock {
    Set<String> dead = new HashSet<>();
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    public int openLock(String[] deadends, String target) {
        for (String cur : deadends){
            dead.add(cur);
        }
        int count =0;
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()){
            int size= queue.size();
            for (int i = 0; i < size; i++) {
                String  cur = queue.poll();
                if (dead.contains(cur)){
                    continue;
                }
                if (target.equals(cur)){
                    return count;
                }

                //将当前节点八个相邻节点加入
                for (int j = 0; j < 4; j++) {
                    String upWord = upOne(cur,j);
                    String downWord = downOne(cur,j);
                    if (!visited.contains(upWord)){
                        queue.offer(upWord);
                        visited.add(upWord);
                    }
                    if (!visited.contains(downWord)){
                        queue.offer(downWord);
                        visited.add(downWord);
                    }
                }
            }

            count++;
        }
        return -1;
    }

    private String downOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        if (chars[j] =='0'){
            chars[j] ='9';
        }else {
            chars[j] =(char) ((int)chars[j]-1);
        }
        return String.valueOf(chars);
    }

    private String upOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        if (chars[j] =='9'){
            chars[j] ='0';
        }else {
            chars[j] =(char) ((int)chars[j]+1);
        }
        return String.valueOf(chars);
    }
}
