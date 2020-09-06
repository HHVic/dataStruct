package com.huan.动态规划;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class _10_正则表达式匹配 {
    public static boolean isMatch(String s, String p) {
        if(s == null || s.length() == 0 && (p == null || p.length() == 0))
            return true;
        if(p == null || p.length() == 0)
            return false;
        //dp[i][j] 表示p中前i个字符是否可以匹配s中前j个字符
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for(int i = 1;i <= p.length();++i){
            for(int j = 0;j <= s.length();++j){
                //p中第i个字符为*
                if(p.charAt(i - 1) == '*'){
                    //消除*以及前面的
                    dp[i][j] = dp[i - 2][j];
                    if(j > 0 && (p.charAt(i - 2) == '.' || s.charAt(j - 1) == p.charAt(i - 2))){
                        //*匹配当前字符
                        dp[i][j] = dp[i][j] || dp[i - 1][j] || dp[i][j - 1];
                    }
                }else if(j > 0 && (p.charAt(i - 1) == '.' || s.charAt(j - 1) == p.charAt(i - 1))){
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        for (boolean[] booleans : dp) {
            for (int j = 0; j < dp[0].length; ++j) {
                System.out.print(booleans[j] + " ");
            }
            System.out.println();
        }
        return dp[p.length()][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b*"));
    }
}
