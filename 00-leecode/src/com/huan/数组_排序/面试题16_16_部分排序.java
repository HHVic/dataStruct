package com.huan.数组_排序;

/**
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 * AC 2 ms , 在所有 Java 提交中击败了99.52%的用户
 */
public class 面试题16_16_部分排序 {


    public int[] subSort(int[] array) {
        //思路：正向扫描求出最后一个逆序为right，反向扫描求出最后一个逆序为left
        //正向扫描时:初始化第一个数为最大值max,遇到小于max的记录当前的值为right,反之max更新为当前的数
        //反向扫描时:初始化最后一个数为最小值min,遇到小于min的记录当前的值为left,反之max更新为当前的数
        if(array.length == 0) return new int[]{-1,-1};
        int right = -1;
        int max = array[0];
        //从左往右扫描
        for(int i = 1;i < array.length;++i){
            if(array[i] < max){
                right = i;
            }else{
                max = array[i];
            }
        }
        //right若为-1说明正序提前结束
        if(right == -1) return new int[]{-1,-1};
        //从右往左扫描
        int left = -1;
        int min = array[array.length - 1];
        for(int i = array.length - 2;i >= 0;--i){
            if(array[i] > min){
                left = i;
            }else{
                min = array[i];
            }
        }
        return new int[]{left,right};
    }
}
