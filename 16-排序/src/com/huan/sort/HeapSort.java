package com.huan.sort;

import com.huan.Person;
import com.huan.Sort;

import java.util.Comparator;

public class HeapSort<T> extends Sort<T> {
    private int heapSize;

    public HeapSort(){
        this(null);
    }

    public HeapSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort() {
        // 初始化堆的数量
        heapSize = array.length;
        // 对array进行原地批量建堆(heapify)
        for(int i = (heapSize >> 1) - 1;i >= 0;--i){
            siftDown(i);
        }
        // 堆的大小大于1时排序
        while(heapSize > 1){
            swap(0,--heapSize);
            siftDown(0);
        }
    }

    public void siftDown(int index){
        T data = array[index];
        while(index < (heapSize >> 1)){
            int leftIndex = (index << 1) + 1;
            T leftData = array[leftIndex];
            int rightIndex;
            if((rightIndex = leftIndex + 1) < heapSize && compare(array[rightIndex],array[leftIndex]) > 0){
                leftData = array[leftIndex = rightIndex];
            }
            if(compare(data,leftData) >= 0) break;
            array[index] = leftData;
            index = leftIndex;
        }
        array[index] = data;
    }
}
