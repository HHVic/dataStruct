package com.huan.sort;

import com.huan.Sort;

public class CountingSort extends Sort<Integer> {
    @Override
    public void sort() {
        int max = array[0];
        for(int i = 1;i < array.length;++i){
            max = Math.max(array[i],max);
        }
        int[] counts = new int[max + 1];
        for(int i = 0;i < array.length;++i){
            ++counts[array[i]];
        }
        int cur = 0;
        for(int i = 0;i < counts.length;++i){
            while(counts[i]-- > 0){
                array[cur++] = i;
            }
        }
    }
}
