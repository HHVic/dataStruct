package com.huan.字符串;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/largest-number/
 */
public class _179_最大数 {
    public static String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for(Integer num : nums) list.add(num.toString());
        list.sort((s1,s2) -> (s2 + s1).compareTo(s1 + s2));
        list.forEach(item -> sb.append(item));

        String s = sb.toString();
        return s.startsWith("0") ? "0" : s;
    }

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{228541,10,3,9}));
    }
}
