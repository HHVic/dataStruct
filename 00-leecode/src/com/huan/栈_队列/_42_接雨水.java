package com.huan.栈_队列;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class _42_接雨水 {

    public static int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int[] rightIdxs = new int[height.length];
        int[] leftIdxs = new int[height.length];
        for(int i = 0;i < height.length;++i){
            rightIdxs[i] = -1;
            leftIdxs[i] = -1;
            while(!stack.isEmpty() && height[i] >= height[stack.peek()]){
                rightIdxs[stack.pop()] = i;
            }
            if(!stack.isEmpty()){
                leftIdxs[i] = stack.peek();
            }
            stack.push(i);

        }
        int max = 0;
        int result1 = 0;
        for(int i = 0;i < height.length;++i){
            if(rightIdxs[i] == -1){
                max = i;
                break;
            }
            if(rightIdxs[i] - i >= 2) {
                result1 += (rightIdxs[i] - i - 1) * height[i];
                int j = i + 1;
                while(j < rightIdxs[i]){
                    result1 -= height[j++];
                }
                i = j - 1;
            }
        }
        for(int i = height.length - 1;i >= max;--i){
            if(leftIdxs[i] == -1) break;
            if(i - leftIdxs[i] >= 2) {
                result1 += (i - leftIdxs[i] - 1) * height[i];
                int j = i - 1;
                while(j > leftIdxs[i]){
                    result1 -= height[j--];
                }
                i = j + 1;
            }
        }
        return result1;
    }

    public int trap1(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) {
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) {
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] height = {4,9,4,5,3,2};
        System.out.println(trap(height));
    }
}
