package com.huan.字符串;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * AC 12 ms, 在所有 Java 提交中击败了74.64%的用户
 */
public class _1143_最长公共子序列 {

    //1 定义状态 dp[i][j]text1中以i结尾与text2中以j结尾的最长公共子序列
    //2 状态转移方程 text1[i] == text2[j] dp[i][j] = dp[i - 1][j - 1] + 1
    //else dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1])
    public static int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //dp[i][0] dp[0][j]均为0
        for(int i = 1;i <= len1;++i){
            for(int j = 1;j <= len2;++j){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }
}
