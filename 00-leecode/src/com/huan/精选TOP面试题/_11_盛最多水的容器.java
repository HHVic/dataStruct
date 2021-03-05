package com.huan.精选TOP面试题;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:HuanK
 * @create:2021-03-01 16:24
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class _11_盛最多水的容器 {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while(i < j){
            int w = j - i;
            int area = w;
            if(height[i] < height[j]){
                area = area * height[i++];
            }else if(height[i] > height[j]){
                area = area * height[j--];
            }else{
                area = area * height[i++];
                --j;
            }
            max = Math.max(max,area);
        }
        return max;
    }
}
