package com.huan.sort;

import com.huan.Sort;

import java.util.Comparator;

public class HeapSort2<T> extends Sort<T> {

    public HeapSort2(){
        this(null);
    }

    public HeapSort2(Comparator<T> comparator) {
        super(comparator);
    }
    @Override
    public void sort(){
        // 建堆 O(n)
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            siftDown(i,array.length);
        }
        // 开始排序
        for(int i = array.length - 1;i >= 0;--i){
            swap(0,i);
            siftDown(0,i);
        }
    }

    public void siftDown(int index,int end){
        T data = array[index];
        int leftIndex;
        while(index < (array.length >> 1) && (leftIndex = (index << 1) + 1) < end){
            T leftData = array[leftIndex];
            int rightIndex;
            if((rightIndex = leftIndex + 1) < end && compare(array[rightIndex],array[leftIndex]) > 0){
                leftData = array[leftIndex = rightIndex];
            }
            if(compare(data,leftData) >= 0) break;;
            array[index] = leftData;
            index = leftIndex;
        }
        array[index] = data;
    }
}
