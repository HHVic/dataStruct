package com.huan.精选TOP面试题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:HuanK
 * @create:2021-03-04 16:40
 * https://leetcode-cn.com/problems/perfect-squares/
 */
public class _279_完全平方数 {
    //和找零钱类似
    public int numSquares(int n) {
        List<Integer> square = new ArrayList<>();
        int[] dp = new int[n + 1];
        for(int i = 0;i * i <= n;++i){
            dp[i * i] = 1;
            square.add((i + 1) * (i + 1));
        }
        int size = square.size();
        for(int i = 2; i <= n;++i){
            if(dp[i] == 1) continue;
            int min = Integer.MAX_VALUE;
            for(int j = 0;(j + 1) * (j + 1) < i ;++j){
                min = Math.min(min,dp[i - square.get(j)]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    @Test
    public void test(){
        System.out.println(numSquares(12));
    }
}
