package com.huan.动态规划;

/**
 * https://leetcode-cn.com/problems/three-steps-problem-lcci/
 */
public class _面试题_0801_三步问题 {
    /*
    三步问题。有个小孩正在上楼梯，
    楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
    实现一种方法，计算小孩有多少种上楼梯的方式。
    结果可能很大，你需要对结果模1000000007。

    取模运算效率太差
     */
    public static final int MAX = 1000000007;
    public static int waysToStep(int n) {
        if(n < 3) return n;
        if(n == 3) return n + 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4;i <= n;++i){
            int res = dp[i - 1] + dp[i - 2];
            if(res > MAX){
                res -= MAX;
            }
            res += dp[i - 3];
            if(res > MAX){
                res -=MAX;
            }
            dp[i] = res;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(waysToStep(11));
    }
}
