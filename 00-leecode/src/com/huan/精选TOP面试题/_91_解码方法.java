package com.huan.精选TOP面试题;

/**
 * @author:HuanK
 * @create:2021-03-02 13:39
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class _91_解码方法 {
    public int numDecodings(String s) {
        char[] arrs = s.toCharArray();
        if(arrs[0] == '0') return 0;
        int[] dp = new int[arrs.length];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i <= arrs.length;++i) {
            //排除0
            if(arrs[i - 1] == '0'){
                if(arrs[i - 2] > '2' || arrs[i - 2] == '0') return 0;
                dp[i] = dp[i - 2];
            }else{
                if(arrs[i - 2] == '0'){
                    dp[i] = dp[i - 1];
                }else{
                    if(arrs[i - 2] == '1' || (arrs[i - 1] <= '6' && arrs[i - 2] <= '2')){
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }else{
                        dp[i] = dp[i - 1];
                    }
                }
            }
        }
        return dp[arrs.length];
    }
}
