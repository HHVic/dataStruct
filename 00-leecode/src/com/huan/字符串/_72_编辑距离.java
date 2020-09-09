package com.huan.字符串;

/*
    https://leetcode-cn.com/problems/edit-distance/
    6 ms, 在所有 Java 提交中击败了87.96%的用户
 */
public class _72_编辑距离 {

    //1 定义状态 dp[i][j] word1中以i结尾变成word2中以j结尾需要百年换的最小次数
    //2 状态转移方程 word1[i] == word1[j]  dp[i][j] = dp[i - 1][j - 1]  else  dp[i][j] = Math.min(dp[i - 1][j],dp[i - 1][j - 1],dp[i][j - 1]) + 1
    public static int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null) return 0;
        if(word1 == null) return word2.length();
        if(word2 == null) return word1.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        //初始化
        for(int j = 0;j <= len2;++j){
            dp[0][j] = j;
        }
        for(int i = 0;i <= len1;++i){
            dp[i][0] = i;
        }

        for(int i = 1;i <= len1;++i) {
            for (int j = 1; j <= len2; ++j) {
                dp[i][j] = dp[i - 1][j - 1];
                if (chars1[i - 1] != chars2[j - 1]) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i][j]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }
}
