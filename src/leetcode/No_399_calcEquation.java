package leetcode;

import java.util.*;

/**
 * @author HP
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 *
 * 方法一：广度优先遍历
 * 首先要构建一个图，因为节点都是a,b,c....，所以要用一个数字来使其对应（借用map a:1，b:2），图中节点为一个坐标加值；
 * 在找结果时判断即便需要的queries（如a,x）x不在图中，在遍历完a的邻接表后，因为初始值均为-1，没有的话输出的值为-1，有的话就不是-1
 */
public class No_399_calcEquation {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //将abc变为123对应
        int indexs=0;
        int n =equations.size();
        int queriesCount = queries.size();
        //结果集
        double[] ret = new double[queriesCount];
        HashMap<String, Integer> map = new HashMap<>();
//        将每一个元素abcd以整数表示
        for (List<String> equation:
                equations) {
            if (!map.containsKey(equation.get(0))){
                map.put(equation.get(0),indexs++);
            }
            if (!map.containsKey(equation.get(1))){
                map.put(equation.get(1),indexs++);
            }
        }
        //建立邻接表
        List<Pair>[] edges = new List[indexs];
        for (int i = 0; i < indexs; i++) {
            edges[i]=new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int va = map.get(equations.get(i).get(0)),vb=map.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb,values[i]));
            edges[vb].add(new Pair(va,1/values[i]));
        }
        //进行遍历
        for (int i = 0; i < queriesCount; i++) {
            double result = -1;
            if (map.containsKey(queries.get(i).get(0)) && map.containsKey(queries.get(i).get(1))){
                //当queries第i个list里面两个元素都是图中节点就进行遍历，有一个不是则该位置节点result为-1
                int ia = map.get(queries.get(i).get(0)),ib=map.get(queries.get(i).get(1));
                if (ia == ib){
                    result=1.0;
                }else {
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);
                    double[] ratios = new double[indexs];
                    Arrays.fill(ratios,-1.0);
                    ratios[ia] = 1.0;
                    while (!points.isEmpty() && ratios[ib] <0){
                        //将邻接表中所有的元素都进行遍历，这样第ib个必会被包含
                        int x = points.poll();
                        for (Pair pair:
                                edges[x]) {
                            int y =pair.index;
                            double val = pair.value;
                            if (ratios[y]<0){
                                ratios[y] = ratios[x] *val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i]=result;
        }
        return ret;
    }
}


//邻接表中元素的对象
class Pair {
    int index;
    double value;

    Pair(int index, double value) {
        this.index = index;
        this.value = value;
    }
}

