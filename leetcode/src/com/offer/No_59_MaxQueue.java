package com.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class No_59_MaxQueue {
    Queue<Integer> queue ;
    Deque<Integer> queueDouble ;//双端队列
    public No_59_MaxQueue() {
        queue = new ArrayDeque<>();
        queueDouble = new ArrayDeque<>();
    }

    public int max_value() {
        if(queueDouble.isEmpty()){
            return -1;
        }
        return queueDouble.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(!queueDouble.isEmpty() && queueDouble.peekLast() < value){
            queueDouble.pollLast();
        }
        queueDouble.offerLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        int res = queue.poll();
        if(res == queueDouble.peekFirst()){
            queueDouble.pollFirst();
        }
        return  res;
    }
}
