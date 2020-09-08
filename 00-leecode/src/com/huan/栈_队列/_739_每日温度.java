package com.huan.栈_队列;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class _739_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        //思路:利用栈求右边第一个比当前元素大的索引，维护单调递减栈
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; ++i) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperatures1(int[] T) {
        //思路:倒推法
        int[] res = new int[T.length];
        for (int i = T.length - 2; i >= 0; --i) {
            int j = i + 1;
            while(j < T.length){
                if (T[i] < T[j]) {
                    res[i] = j - i;
                    break;
                }else if(res[j] == 0){
                    res[i] = 0;
                    break;
                }else{
                    j = j + res[j];
                }
            }
        }
        return res;
    }
}
