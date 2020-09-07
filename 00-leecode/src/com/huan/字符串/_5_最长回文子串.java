package com.huan.字符串;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/  同动态规划
 */
public class _5_最长回文子串 {
    //1.定义状态 dp[i][j] 表示从i到j是否是回文
    //2.初始状态 dp[i][i] = true;
    //3.状态转移方程 dp[i][j] = dp[i + 1][j - 1]

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        //1.定义状态 dp[i][j] 表示从i到j是否是回文
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        //2.初始状态 dp[i][i] = true;单个字符必定是回文串
        for(int i = 0;i < chars.length;++i){
            dp[i][i] = true;
        }
        int max = 1;
        //记录最长回文串的初始位置
        int begin = 0;
        //因为要用到dp[i + 1][j - 1]从j开始遍历
        for(int j = 1;j < chars.length;++j){
            for(int i = 0;i < j;++i){
                //这里  aba   aa均是回文
                //chars[i] == chars[j]  判断i-j有几个字符，大于三个字符dp[i][j] = dp[i + 1][j - 1]
                if(chars[i] == chars[j]){
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }else{
                    //不相等必不是回文
                    dp[i][j] = false;
                }
                //若是回文又比之前的长，记录当前为最长
                if(dp[i][j] && j - i + 1 > max){
                    //更新初始值
                    begin = i;
                    //更新长度
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin,begin + max);
    }

    /**
     * 优化空间（滚动数组）
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        if(s == null || s.length() <= 1) return s;
        //1.定义状态 dp[i][j] 表示从i到j是否是回文
        boolean[][] dp = new boolean[s.length()][2];
        char[] chars = s.toCharArray();
        //2.初始状态 dp[i][i] = true;单个字符必定是回文串
        dp[0][0] = true;
        dp[1][1] = true;
        int max = 1;
        //记录最长回文串的初始位置
        int begin = 0;
        //因为要用到dp[i + 1][j - 1]从j开始遍历
        for(int j = 1;j < chars.length;++j){
            for(int i = 0;i < j;++i){
                //这里  aba   aa均是回文
                //chars[i] == chars[j]  判断i-j有几个字符，大于三个字符dp[i][j] = dp[i + 1][j - 1]
                if(chars[i] == chars[j]){
                    if(j - i < 3){
                        dp[i][j & 1] = true;
                    }else{
                        dp[i][j & 1] = dp[i + 1][(j - 1) & 1];
                    }
                }else{
                    //不相等必不是回文
                    dp[i][j & 1] = false;
                }
                //若是回文又比之前的长，记录当前为最长
                if(dp[i][j & 1] && j - i + 1 > max){
                    //更新初始值
                    begin = i;
                    //更新长度
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin,begin + max);
    }

    /**
     * 优化空间（一维数组）
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if(s == null || s.length() <= 1) return s;
        //1.定义状态 dp[i][j] 表示从i到j是否是回文
        boolean[] dp = new boolean[s.length()];
        char[] chars = s.toCharArray();
        //2.初始状态 dp[i][i] = true;单个字符必定是回文串
        dp[0] = true;
        int max = 1;
        //记录最长回文串的初始位置
        int begin = 0;
        //因为要用到dp[i + 1][j - 1]从j开始遍历
        for(int j = 1;j < chars.length;++j){
            for(int i = 0;i < j;++i){
                //这里  aba   aa均是回文
                //chars[i] == chars[j]  判断i-j有几个字符，大于三个字符dp[i][j] = dp[i + 1][j - 1]
                if(chars[i] == chars[j]){
                    if(j - i < 3){
                        dp[i] = true;
                    }else{
                        dp[i] = dp[i + 1];
                    }
                }else{
                    //不相等必不是回文
                    dp[i] = false;
                }
                //若是回文又比之前的长，记录当前为最长
                if(dp[i] && j - i + 1 > max){
                    //更新初始值
                    begin = i;
                    //更新长度
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin,begin + max);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome2("aabaaa"));
    }
}
