package com.huan.字符串;

/**
 * https://leetcode-cn.com/problems/string-rotation-lcci/
 */
public class 面试题01_09_字符串轮转 {

    public boolean isFlipedString(String s1, String s2) {
        if(s1.length() == 0 && s2.length() == 0) return true;
        if(s1.length() != s2.length()) return false;
        return (s1 + s1).contains(s2);
    }
}
