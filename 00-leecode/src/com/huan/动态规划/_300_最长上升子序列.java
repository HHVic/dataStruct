package com.huan.动态规划;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class _300_最长上升子序列 {
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //1.定义状态 dp[i] 表示以i结尾的最长上升子序列
        int[] dp = new int[nums.length];
        //2.定义初始状态 dp[0] = 1 dp[i] = 1
        dp[0] = 1;
        //3.状态转移方程 j < i if(nums[j] >= nums[i]) dp[i] = 1 否则 dp[i] = dp[j] + 1;
        int max = dp[0];
        for (int i = 1;i < nums.length;++i){
            dp[i] = 1;
            for(int j = 0;j < i;++j){
                if(nums[j] >= nums[i]) continue;
                dp[i] = Math.max(dp[j] + 1,dp[i]);
            }
            max = Math.max(max,dp[i]);
        }
        for(int i : dp){
            System.out.println(i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
