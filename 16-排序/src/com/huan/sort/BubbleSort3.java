package com.huan.sort;

import com.huan.Sort;

import java.util.Comparator;

public class BubbleSort3<T> extends Sort<T> {

    public BubbleSort3(){
        this(null);
    }

    public BubbleSort3(Comparator<T> comparator) {
        super(comparator);
    }
    @Override
    public void sort() {
        for(int end = array.length - 1;end > 0;--end){
            int stopIndex = 1;
            for(int start = 1;start <= end;++start){
                if(compare(array[start - 1],array[start]) > 0){
                    swap(start,start - 1);
                }
                stopIndex = start;
            }
            end = stopIndex;
        }
    }
}
