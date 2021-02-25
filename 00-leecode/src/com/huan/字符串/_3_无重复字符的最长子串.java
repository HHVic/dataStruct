package com.huan.字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class _3_无重复字符的最长子串 {

    public static String lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        //上一次出现字符的位置
        Map<Character,Integer> map = new HashMap<>();
        map.put(chars[0],0);
        int pi = 0;
        int max = 1;
        int l = 0;
        for(int i = 1;i < chars.length;++i){
            //获取上一次出现的位置
            Integer prev = map.get(chars[i]);
            if(prev != null && prev >= pi){
                pi = prev + 1;
            }
            map.put(chars[i],i);
            if(max < i - pi + 1){
                l = pi;
                max = i - pi + 1;
            }
        }
        return s.substring(l,l + max);
    }

    /**
     * 不使用map
     * @param s
     * @return
     */
    public static String lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        //上一次出现字符的位置
        //单字符ACII码表的范围0-127
        int[] prevInx = new int[128];
        for(int i = 1;i < chars.length;++i){
            prevInx[chars[i]] = -1;
        }
        prevInx[chars[0]] = 0;
        int pi = 0;
        int max = 1;
        int l = 0;
        for(int i = 1;i < chars.length;++i){
            //获取上一次出现的位置
            int prev = prevInx[chars[i]];
            if(prev >= pi){
                pi = prev + 1;
            }
            prevInx[chars[i]] = i;
            if(max < i - pi + 1){
                l = pi;
                max = i - pi + 1;
            }
        }
        return s.substring(l,l + max);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("The Linux kernel is an open source software project of fairly large scope."));
    }
}
