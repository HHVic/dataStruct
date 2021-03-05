package com.huan._美团2021校招笔试;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author:HuanK
 * @create:2021-03-03 16:46
 */
public class _回转寿司_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        List<Integer> res = new ArrayList<>();
        for(int t = 0;t < T;++t){
            int N = sc.nextInt();
            int[] cost = new int[N];
            for(int i = 0;i < N;++i) cost[i] = sc.nextInt();
            res.add(helper(cost));
        }
        res.forEach(x -> System.out.println(x));
    }

    private static int helper(int[] cost) {
        int[] nums = new int[cost.length << 1];
        System.arraycopy(cost,0,nums,0,cost.length);
        System.arraycopy(cost,0,nums,cost.length,cost.length);
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        //dpMax[0] = Math.max(0,cost[0]);
        //定义初始状态
        dpMax[0] = dpMin[0] = nums[0];
        int max = dpMax[0];
        int min = dpMin[0];
        int total = nums[0];
        for(int i = 1;i < nums.length;++i){
            total += nums[i];
            dpMax[i] = Math.max(0,dpMax[i - 1]) + nums[i];
            dpMin[i] = Math.min(0,dpMin[i - 1]) + nums[i];
            max = Math.max(max,dpMax[i]);
            min = Math.min(min,dpMin[i]);
        }
        max = Math.max(max,total - min);
        return max;
    }
}
