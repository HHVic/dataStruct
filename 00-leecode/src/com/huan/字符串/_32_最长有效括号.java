package com.huan.字符串;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * 1 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _32_最长有效括号 {
    //1 定义状态dp[i] 表示以i结尾的最长有效括号长度
    //2 状态转移方程 s[i] == '(' dp[i] = 0;
    // s[i] == ')' && s[i - 1] == '(' 表示匹配了 dp[i] = dp[i - 2] + 2;
    // s[i] == ')' && s[i - 1] == ')' 看dp[i - 1]并且前移dp[i - 1]个长度判断是不是'('
    //      是  if(i - dp[i - 1]  > 2) dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
    //      否 dp[i] = 0
    public static int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len];
        int max = 0;
        for(int i = 1;i < len;++i){
            if(s.charAt(i) == '('){
                dp[i] = 0;
            }else{
                //右括号,前一个是左括号
                if(s.charAt(i - 1) == '('){
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else{
                    //前一个是右括号
                    if(i - dp[i - 1] < 1 || s.charAt(i - dp[i - 1] - 1) == ')'){
                        dp[i] = 0;
                    }else{
                        //i - dp[i - 1] >= 1 && s.charAt(i - dp[i - 1] - 1) == '('
                        dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                    }
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()()())()"));
    }
}
