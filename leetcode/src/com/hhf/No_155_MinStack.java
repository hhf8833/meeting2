package com.hhf;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author HP
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 法一：
 * 同步辅助栈，辅助栈里面存的到当前元素为止，最小的值，和动态规划相似，当前的最小值
 *
 * 法二：
 * 不使用辅助栈，每次将其当前值和当前最小值都push进同一栈中，并每次记录最小值，pop的时候更新最小值，而且要判空
 *             top的时候将顶层存起来，peek下面一个，再push进去即可
 *             gemin的时候就取最小值即可
 */
/*public class MinStack {
    Deque<Integer> stack;
    Deque<Integer> minStack;
    public MinStack() {
        stack = new LinkedList<>();
        minStack= new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(),val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return  minStack.peek();
    }
}*/
public class No_155_MinStack {
    Deque<Integer> stack;
    int min;
    public No_155_MinStack() {
        stack = new LinkedList<>();
        min= Integer.MAX_VALUE;
    }

    public void push(int val) {
        stack.push(val);
        min=Math.min(min,val);
        stack.push(min);
    }

    public void pop() {
        int minValue =stack.pop();
        stack.pop();
        if (!stack.isEmpty() && min==minValue){
            min = stack.peek();
        }else {
            min = Integer.MAX_VALUE;
        }

    }

    public int top() {
        int temp = stack.pop();
        int res = stack.peek();
        stack.push(temp);
        return  res;
    }

    public int getMin() {
        return  min;
    }
}
