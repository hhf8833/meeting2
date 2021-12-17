package com.hhf;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author HP
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 */
public class No_394_decodeString {
    public String decodeString(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        StringBuilder strBuilder = new StringBuilder();
        StringBuilder strBuilder2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ']') {
                stack.push(c);
            } else {
                while (stack.peek() != '[') {
                    stack2.push(stack.pop());
                }
                //弹出[
                stack.pop();

                while (!stack.isEmpty() && stack.peek() >= 48 && stack.peek() <= 57) {
                    strBuilder.append(stack.pop());
                }
                int n = Integer.parseInt(strBuilder.reverse().toString());
//                strBuilder.delete(0,strBuilder.length());
                strBuilder = new StringBuilder();
                //将队列中的元素取出来
                while (!stack2.isEmpty()) {
                    strBuilder.append(stack2.pop());
                }
                for (int j = 0; j < n; j++) {
                    strBuilder2.append(strBuilder);
                }
                for (int j = 0; j < strBuilder2.length(); j++) {
                    stack.push(strBuilder2.charAt(j));
                }
//                strBuilder.delete(0,strBuilder.length());
//                strBuilder2.delete(0,strBuilder2.length());
                strBuilder = new StringBuilder();
                strBuilder2 = new StringBuilder();
            }
        }

        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        while (!stack2.isEmpty()) {
            strBuilder2.append(stack2.pop());
        }
        return strBuilder2.toString();
    }

    @Test
    public void test() {
        String s = "3[a2[c]]";
        No_394_decodeString de = new No_394_decodeString();
        String res = de.decodeString2(s);
        System.out.println(res);
    }

    public String decodeString2(String s) {
        //stackStr每次入栈都是入的当前包含最新的所有字符串，这样的好处就是 不用在最后一个个出栈再进行调转
        Deque<StringBuilder> stackStr = new ArrayDeque<>();
        Deque<Integer> stackInt = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int multi = 0;
        for (char c :
                s.toCharArray()) {
            if (c == '[') {
                //遇到左括号都要将当前的字符串push到栈
                stackStr.push(res);
                stackInt.push(multi);
                multi=0;
                res = new StringBuilder();
            }else if (c ==']'){
                StringBuilder tmp = new StringBuilder();
                int curMulti = stackInt.pop();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                res = stackStr.pop().append(tmp);
            }else if (c >='0' && c<='9'){
                multi = multi*10 + c-'0';
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }
}