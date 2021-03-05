package com.huan.精选TOP面试题;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author:HuanK
 * @create:2021-03-01 16:41
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _76_最小覆盖子串 {
    public String minWindow(String s, String t) {
        char[] arrs = s.toCharArray();
        char[] arrt = t.toCharArray();
        Set<Character> set = new HashSet<>();
        int[] tCount = setTCount(arrt,set);

        Queue<Integer> queue = new LinkedList<>();
        //count == arrt.length 匹配
        int count = 0;
        int left = -1;
        int len = Integer.MAX_VALUE;
        int minLeft = -1;
        boolean flag = false;
        for(int i = 0;i < arrs.length;){
            if(!set.contains(arrs[i])) {
                ++i;
                continue;
            }
            if(left == -1){
                left = i;
                --tCount[arrs[i]];
                ++count;
                if(count == arrt.length) return new String(arrs,i,1);
                ++i;
                continue;
            }
            if(!flag){
                queue.offer(i);
                if(tCount[arrs[i]]-- > 0){
                    ++count;
                }
            }
            if(count == arrt.length){
                System.out.println("i = " + i + ",left = " + left + "----" + new String(arrs,left,i - left + 1));
                if(i - left + 1 < len){
                    len = i - left + 1;
                    minLeft = left;
                }

                if(len == arrt.length) return new String(arrs,left,len);
                if(++tCount[arrs[left]] > 0){
                    --count;
                }
                left = queue.poll();
                if(count == arrt.length){
                    flag = true;
                }else{
                    flag = false;
                    ++i;
                }
            }else{
                ++i;
            }
        }
        return new String(arrs,minLeft,len);
    }

    private int[] setTCount(char[] arrt,Set<Character> set){
        int[] tCount = new int[128];
        for(int i = 0;i < arrt.length;++i){
            ++tCount[arrt[i]];
            set.add(arrt[i]);
        }
        return tCount;
    }

    @Test
    public void test(){
        System.out.println(minWindow("A", "A"));
    }
}
