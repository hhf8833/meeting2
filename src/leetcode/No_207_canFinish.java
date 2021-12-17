package leetcode;

import java.util.*;

/**
 * @author HP
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *采用图的遍历的方式，设置邻接表
 *
 * 方法一 图广度优先遍历，设置入度表
 * 需要一个入度表（数组） 邻接表（集合里面嵌套集合） 队列
 * 初始化表后，即初始化入度表、邻接表，将所有入度为零的课程入队列，遍历队列，每次队列中对应节点删除，并将该节点的指向的另一个节点入度减一，为零的话就加入到对队列
 *
 * 方法二：
 * 深度优先遍历，仍然使用邻接表
 * 设置标记位 flag  ==1 表示当前递归循环节点已经访问过， ==-1 表示其他节点已经访问过不用在访问，减少递归次数 ==0表示未被访问过
 * 判断条件 当循环的时候遇到1 则说明是有环的要以此返回false退出，当遇到-1 即返回true代表已经访问过减少 次数，遇到0表示该节点还未访问要进行访问
 */
public class No_207_canFinish {
   /* public boolean canFinish(int numCourses,int[][] prerequisities){
        //入度表
        int[] indegree = new int[numCourses];
        //邻接表
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        //初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] cp:
             prerequisities) {
            indegree[cp[0]]++;
            adj.get(cp[1]).add(cp[0]);
        };
        //System.out.println(adj);
        //将所有入度为零的节点入队
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i]==0){
                queue.add(i);
            }
        }
        //System.out.println(Arrays.toString(indegree));
        while (!queue.isEmpty()){
            int pre = queue.poll();
            //System.out.println("pre::"+pre);
            numCourses--;
            for (int e:adj.get(pre)){

                indegree[e]--;
                if (indegree[e] == 0){
                    // 当 队列添加应该是邻接表的入度为0的节点 而不是入度0 （indegree[e]）
                    queue.add(e);
                }
            }
        }
        //System.out.println(numCourses);
        return numCourses==0;
    }*/

    public boolean canFinish(int numCourse, int[][] preerquisities){
        //创建邻接表
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourse; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e :
                preerquisities) {
            adj.get(e[1]).add(e[0]);
        }
        //创建flag数组
        int[] flag = new int[numCourse];
        for (int i = 0; i < numCourse; i++) {
            if (!dfs(adj,flag,i)){
                return false;
            }
        }
        return true;
    }
    public boolean dfs(List<List<Integer>> adj, int[] flag , int i){
        if (flag[i] == 1){
            return false;
        }
        if (flag[i] == -1){
            return true;
        }
        flag[i]=1;
        for (int e:
             adj.get(i)) {
            if (!dfs(adj,flag,e)){
                return false;
            }
        }
        flag[i]=-1;
        return true;
    }
    public static void main(String[] args) {
        No_207_canFinish canFinish =new No_207_canFinish();
        int numCourse = 5;
        int[][] prerequisities =new int[][]{{1,4},{2,4},{3,1},{3,2}};
        boolean res =canFinish.canFinish(numCourse,prerequisities);
        System.out.println(res);
    }
}
