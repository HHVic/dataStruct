package com.huan.sort;

import com.huan.Sort;

public class InsertionSort1<T> extends Sort<T> {
    @Override
    public void sort() {
        for(int start = 1;start < array.length;++start){
            int current = start;
            while(current > 0 && compare(array[current - 1],array[current]) > 0){
                swap(current,--current);
            }
        }
    }
}
