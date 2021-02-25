package com.huan.精选TOP面试题;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class _5_最长回文子串 {
    //1.定义状态 dp[i][j] 表示从i到j是否是回文
    //2.初始状态 dp[i][i] = true;
    //3.状态转移方程

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        if(s.length() == 1) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for(int i = 0;i < s.length();++i){
            dp[i][i] = true;
        }
        int maxLen = 1;
        int left = 0;
        for(int j = 1;j < s.length();++j){
            for(int i = 0;i < j;++i){
                if(j - i  < 2){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if(dp[i][j]){
                    if(maxLen < j - i + 1){
                        maxLen = j - i + 1;
                        //System.out.println(maxLen);
                        left = i;
                    }
                }
            }
        }

        return s.substring(left,left + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aabaaa"));
    }
}
