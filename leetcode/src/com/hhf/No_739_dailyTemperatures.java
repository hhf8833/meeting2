package com.hhf;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author HP
 * 739. 每日温度
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 自己思考: 暴力法
 *
 * 方法二：单调递减栈
 * 遇到比栈顶小的压入栈中，遇到比栈顶大的就不断弹出直到为空或者比栈顶小，之后将当前压入栈，再进行下一个
 */
public class No_739_dailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res =new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        /*自己写的 太过复杂
        stack.push(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()){
                stack.push(i);
            }else {
                while(!stack.isEmpty() ){
                    if (stack.peek() ==Integer.MAX_VALUE){
                        stack.push(i);
                        break;
                    }
                    if (temperatures[stack.peek()] >= temperatures[i]){
                        stack.push(i);
                        break;
                    }else {
                        int stackCur = stack.pop();
                        res[stackCur] =i-stackCur;
                    }
                }
            }
        }
        while (stack.peek()!=Integer.MAX_VALUE){
            res[stack.pop()]=0;
        }*/
        for (int i = 0; i < n; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature >temperatures[stack.peek()] ){
                int curPop = stack.pop();
                res[curPop]= i-curPop;
            }
            stack.push(i);
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums=new int[]{73,74,75,71,69,72,76,73};
        int[] res =dailyTemperatures(nums);
        System.out.println(Arrays.toString(res));
    }
}
