package com.huan.sort;

import com.huan.Sort;

import java.util.Comparator;

public class SelectionSort<T> extends Sort<T> {

    public SelectionSort(){
        this(null);
    }

    public SelectionSort(Comparator<T> comparator) {
        super(comparator);
    }
    @Override
    public void sort() {
        for(int end = array.length - 1;end > 0;--end){
            int maxIndex = 0;
            for(int start = 1;start <= end;++start){
                if(compare(array[start],array[maxIndex]) >= 0){
                    maxIndex = start;
                }
            }
            swap(maxIndex,end);
        }
    }
}
