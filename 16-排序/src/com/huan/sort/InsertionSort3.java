package com.huan.sort;

import com.huan.Sort;

/**
 * 使用二分查找代替逐个比较
 * @param <T>
 */
public class InsertionSort3<T> extends Sort<T> {
    @Override
    public void sort() {
        for(int start = 1;start < array.length;++start){
            T data = array[start];
            int insertIndex = search(start);
            System.arraycopy(array,insertIndex,array,insertIndex + 1,start - insertIndex);
            array[insertIndex] = data;
        }
    }

    /**
     * 返回待插入的位置
     * @param index
     * @return
     */
    public int search(int index){
        T target = array[index];
        int left = 0;
        int right = index;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(compare(target,array[mid]) < 0){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}
