package com.huan.动态规划;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class _322_零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        for(int i = 1;i <= amount;++i){
            int min = Integer.MAX_VALUE;
            for(int j = coins.length - 1;j >= 0;--j){
                if(i < coins[j]) continue;
                if(dp[i - coins[j]] < 0 || dp[i - coins[j]] >= min) continue;
                min = dp[i - coins[j]];
            }
            if(min == Integer.MAX_VALUE) {
                dp[i] = -1;
                continue;
            }
            dp[i] = min + 1;
        }

        return dp[amount];
    }
}
