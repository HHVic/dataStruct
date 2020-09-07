package com.huan.字符串;


/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class _151_翻转字符串里的单词 {

    public static String reverseWords(String s) {
        if(s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        //去除多余空格
        int cur = 0;
        //前一个是否为空格
        boolean space = true;
        for(int i = 0;i < chars.length;++i){
            //刚开始遇到非空格字符
            if(chars[i] != ' '){
                space = false;
                chars[cur++] = chars[i];
            }else{
                //遇到非空格字符
                //判断遇到的是否是第一个
                if(!space){
                    space = true;
                    chars[cur++] = chars[i];
                }
            }
        }
        int len = space ? cur - 1 : cur;
        if(len <= 0) return "";
        //全部翻转
        //记录前一个空格，默认为-1
        int prevSpace = -1;
        reverse(chars,0,len);
        //对每个单词进行翻转
        for(int i = 0;i < len;++i){
            //当前是空格，翻转
            if(chars[i] == ' '){
                reverse(chars,prevSpace + 1,i);
                //当前空格变成前一个
                prevSpace = i;
            }
        }
        //剩下最后一个单词反转
        reverse(chars,prevSpace + 1,len);
        return new String(chars,0,len);
    }

    /**
     * 对[left,right)区间的字符反转
     * @param chars
     * @param left
     * @param right
     */
    private static void reverse(char[] chars,int left,int right){
        --right;
        while(left < right){
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("aaa_" + reverseWords(" the  sky    is  blue   ") + "_aaa");
    }
}
