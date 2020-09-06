package com.huan.动态规划;

import org.omg.CORBA.MARSHAL;

/*
 *https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class _32_最长有效括号 {

    public static int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        int len = s.length();
        //以i结尾的最长有效括号
        int[] dp = new int[len];
        int maxLen = 0;
        for(int i = 1;i < len;++i){
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
    private static boolean isMatched(char s1,char s2){
        return s1 == '(' && s2 == ')';
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
