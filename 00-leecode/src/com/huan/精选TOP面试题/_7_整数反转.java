package com.huan.精选TOP面试题;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class _7_整数反转 {

    public static int reverse(int x) {
        //正数
        boolean digit = true;
        if(x == Integer.MIN_VALUE) return 0;
        digit = x >= 0;
        x = digit ? x : -x;
        int res = 0;
        while(x > 0){
            //取出最后一位
            int oldRes = res;
            res = 10 * res + (x % 10);
            if((res - (x % 10)) / 10 != oldRes) return 0;
            x /= 10;
        }
        return digit ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-1685423119));
    }
}
