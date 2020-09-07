package com.huan.字符串;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/longest-string-chain/
 */
public class _1048_最长字符串链 {

    /*
    给出一个单词列表，其中每个单词都由小写英文字母组成。
    如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
    词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。
    从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
     */
    public static int longestStrChain(String[] words) {
        //定义状态 dp[i]表示前i个最长单词链的长度
        //为了保证后面的一定是前面的前身，后面的长度必须大于前面的，所以对数组排序
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
    }
}
