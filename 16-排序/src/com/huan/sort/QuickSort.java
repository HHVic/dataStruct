package com.huan.sort;

import com.huan.Sort;

public class QuickSort<T> extends Sort<T> {
    @Override
    public void sort() {
        sort(0,array.length);
    }

    /**
     * 对 [left,right) 区间进行快速排序
     * @param left
     * @param right
     */
    public void sort(int left,int right){
        // 若只剩一个元素直接返回
        if(right - left < 2) return ;
        int pivot = pivotIndex(left,right);
        // 对轴点左边快排
        sort(left,pivot);
        // 对轴点右边快排
        sort(pivot + 1,right);
    }

    private int pivotIndex(int left, int right){
        // 随机选择轴点元素
        swap(left, left + (int) (Math.random() * (right - left)));
        // 选取第一个元素作为轴点元素
        T temp = array[left];
        --right;
        while(left < right){

            while(left < right){
                if(compare(temp,array[right]) < 0){
                    --right;
                }else{
                    array[left++] = array[right];
                    break;
                }
            }
            while(left < right){
                if(compare(temp,array[left]) > 0){
                    ++left;
                }else{
                    array[right--] = array[left];
                    break;
                }
            }
        }
        array[left] = temp;
        return left;
    }
}
