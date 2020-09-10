package com.huan.动态规划;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class _122_买卖股票的最佳时机II {

    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        //思路:求所有上升序列的差
        int max = 0;
        for(int i = 1;i < prices.length;++i){
            if(prices[i] > prices[i - 1]){
                max += (prices[i] - prices[i - 1]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,4,6,3,5}));
    }
}
