package com.huan.动态规划;

public class _121_买卖股票的最佳时机 {


    public int maxProfit(int[] prices) {
        //思路:求出差价数组，求最大连续子数组和
        if(prices == null || prices.length <= 1) return 0;
        int[] profixs = new int[prices.length - 1];
        for(int i = 1;i < prices.length;++i){
            profixs[i - 1] = prices[i] - prices[i - 1];
        }
        int[] dp = new int[profixs.length];
        dp[0] = profixs[0];
        int max = dp[0];
        for(int i = 1;i < profixs.length;++i){
            dp[i] = Math.max(dp[i - 1] ,0) + profixs[i];
            max = Math.max(dp[i],max);
        }
        return Math.max(0,max);
    }

    public int maxProfit1(int[] prices) {
        //思路:当前天卖掉股票获得的最大利润
        if(prices == null || prices.length <= 1) return 0;
        int min = prices[0];
        int maxProfix = 0;
        for(int i = 1;i < prices.length;++i){
            if(prices[i] < min){
                min = prices[i];
            }else{
                maxProfix = Math.max(maxProfix,prices[i] - min);
            }
        }
        return maxProfix;
    }
}
