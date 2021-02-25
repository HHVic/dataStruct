package com.huan.字符串;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _76_最小覆盖子串 {

    /*
    给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
    示例：
    输入：S = "ADOBECODEBANC", T = "ABC"
    输出："BANC"
     */

    public static String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        char[] chars = s.toCharArray();
        int left = 0,right = 0;
        int[] windows = new int[58];
        int[] tcount = new int[58];
        char[] charst = t.toCharArray();
        //统计t中所有字符及其个数
        for(int i = 0;i < charst.length;++i){
            tcount[charst[i] - 'A']++;
        }
        //记录最小覆盖的字符串长度
        int len = chars.length + 1;
        //记录最小覆盖的开始索引
        int l = 0;
        while(left < chars.length){
            if(tcount[chars[left] - 'A'] > 0) break;
            ++left;
        }
        right = left;
//        ++windows[chars[0]];
        while(right <= chars.length){
            //left缩小窗口([left,right)区间涵盖t中所有字符)
            if(containsAll(tcount,windows)){
                if(right - left == charst.length) return s.substring(left,right);
                //更新max,l
                if(len > right - left){
                    len = right - left;
                    l = left;
                }
                --windows[chars[left] - 'A'];
                ++left;
            }else if(right < chars.length){
                //right 扩大窗口
                ++windows[chars[right] - 'A'];
                ++right;
            }else{
                break;
            }
        }
        if(len == chars.length + 1) return "";
        return s.substring(l,l + len);
    }

    private static boolean containsAll(int[] tcount,int[] windows){
        //最多128次 O（1）
        for(int i = 0;i < tcount.length;++i){
            if(tcount[i] == 0) continue;
            if(windows[i] < tcount[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("aaaaABddd","AB"));
    }
}
