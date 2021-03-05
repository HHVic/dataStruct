package com.huan.精选TOP面试题;

/**
 * @author:HuanK
 * @create:2021-03-01 19:20
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class _10_正则表达式匹配 {
    public boolean isMatch(String s, String p) {
        if((s == null || "".equals(s)) && (p == null || "".equals(p))) return true;
        if(p == null || "".equals(p)) return false;
        char[] arrs = s.toCharArray();
        char[] arrp = p.toCharArray();
        boolean[][] dp = new boolean[arrp.length + 1][arrs.length + 1];
        dp[0][0] = true;
        for(int i = 1;i <= arrp.length;++i){
            for(int j = 0;j <= arrs.length;++j){
                if(arrp[i - 1] == '*'){
                    dp[i][j] = dp[i - 2][j];
                    if(j > 0 && (arrp[i - 2] == '.' || arrs[j - 1] == arrp[i - 2])){
                        dp[i][j] = dp[i][j] || dp[i - 1][j] || dp[i - 1][j];
                    }
                }else{
                    if(j > 0 && (arrp[i - 1] == '.' || arrs[j - 1] == arrp[i - 1])){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[arrp.length][arrs.length];
    }
}
