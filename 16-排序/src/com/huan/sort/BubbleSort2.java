package com.huan.sort;

import com.huan.Sort;

import java.util.Comparator;

public class BubbleSort2<T> extends Sort<T> {

    public BubbleSort2(){
        this(null);
    }

    public BubbleSort2(Comparator<T> comparator) {
        super(comparator);
    }
    @Override
    public void sort() {
        for(int end = array.length - 1;end > 0;--end){
            boolean stop = true;
            for(int start = 1;start <= end;++start){
                if(compare(array[start - 1],array[start]) > 0){
                    swap(start,start - 1);
                    stop = false;
                }
            }
            if(stop) break;
        }
    }
}
