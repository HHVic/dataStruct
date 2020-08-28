package com.huan.sort;

import com.huan.Sort;

public class InsertionSort2<T> extends Sort<T> {
    @Override
    public void sort() {
        for(int start = 1;start < array.length;++start){
            int current = start;
            T data = array[current];
            while(current > 0 && compare(array[current - 1],data) > 0){
                array[current] = array[--current];
            }
            array[current] = data;
        }
    }
}
