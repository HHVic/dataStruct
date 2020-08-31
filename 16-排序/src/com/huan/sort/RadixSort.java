package com.huan.sort;

import com.huan.Sort;

public class RadixSort extends Sort<Integer> {
    @Override
    public void sort() {
        int max = array[0];
        for(int i = 1;i < array.length;++i){
            max = Math.max(array[i],max);
        }
        int n = 1;
        while(max > 0){
            countingSort(n);
            n = n * 10;
            max /= 10;
        }
    }

    protected void countingSort(int radix){
        int[] counts = new int[10];
        for(int i = 0;i < array.length;++i){
            ++counts[array[i] / radix % 10];
        }
        for(int i = 1;i < counts.length;++i){
            counts[i] += counts[i - 1];
        }
        int[] newArray = new int[array.length];
        for(int i = array.length - 1;i >= 0;--i){
            newArray[--counts[array[i] / radix % 10]] = array[i];
        }
        for(int i = 0;i < array.length;++i){
            array[i] = newArray[i];
        }
    }
}
