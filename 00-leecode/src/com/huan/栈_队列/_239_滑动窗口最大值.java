package com.huan.栈_队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class _239_滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //思路:维护一个单调递减的双端队列
        //if(nums == null || nums.length == 0 || k <= 0)
        if(k > nums.length) return new int[0];
        if(k == 1) return nums;
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int right = 0;right < nums.length;++right){
            //判断新加入的元素是否大于等于尾部值
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]){
                deque.pollLast();
            }
            deque.offer(right);
            //检验头是否失效
            int left = right - k + 1;
            if(left < 0) continue;
            //失效
            if(deque.peekFirst() < left) deque.pollFirst();
            res[left] = nums[deque.peekFirst()];
        }
        return res;
    }
}
