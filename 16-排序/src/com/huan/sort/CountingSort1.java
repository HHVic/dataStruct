package com.huan.sort;

import com.huan.Sort;

public class CountingSort1 extends Sort<Integer> {
    @Override
    public void sort() {
        //计算最大和最小的数  索引 = array[i] - min
        int min = array[0];
        int max = array[0];
        for(int i = 1;i < array.length;++i){
            max = Math.max(array[i],max);
            min = Math.min(array[i],min);
        }
        int[] counts = new int[max - min + 1];
        for(int i = 0;i < array.length;++i){
            ++counts[array[i] - min];
        }
        for(int i = 1;i < counts.length;++i){
            counts[i] += counts[i - 1];
        }
        int[] newArray = new int[array.length];
        for(int i = array.length - 1;i >= 0;--i){
            newArray[--counts[array[i] - min]] = array[i];
        }
        for(int i = 0;i < array.length;++i){
            array[i] = newArray[i];
        }
    }
}
