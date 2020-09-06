package com.huan.动态规划;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 */
public class _354_俄罗斯套娃信封问题 {
    //固定一个求另一个最长上升子序列
    public static int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, (o1, o2) -> {
            if(o1[0] != o2[0]){
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        int maxLen = dp[0];
        for(int i = 1;i < envelopes.length;++i){
            dp[i] = 1;
            for(int j = 0;j < i;++j){
                if(envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[j] + 1,dp[i]) ;
                }
            }
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[][] envelopes = {
                {5,4},
                {6,4},
                {6,7},
                {2,3}
        };
        System.out.println(maxEnvelopes(envelopes));
    }
}
