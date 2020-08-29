package com.huan.sort;

import com.huan.Sort;

public class MergeSort<T> extends Sort<T> {
    private T[] leftArray;
    @Override
    public void sort() {
        leftArray = (T[]) new Object[array.length >> 1];
        sort(0,array.length);
    }

    /**
     * 对 [left,right) 左闭右开区间进行排序
     * @param left
     * @param right
     */
    public void sort(int left,int right) {
        // 如果只剩一个元素 停止递归
        if(right - left < 2) return ;
        int mid = left + ((right - left) >> 1);
        sort(left,mid);
        sort(mid,right);
        // merge
        merge(left,mid,right);
    }

    /**
     * 合并 [left,mid) 和 [mid,right)的元素
     * @param left
     * @param mid
     * @param right
     */
    public void merge(int left,int mid,int right){
        int al = left;
        int ll = 0,lr = mid - left;
        int rl = mid,rr = right;
        // 备份左边的元素
        System.arraycopy(array,left,leftArray,0,mid - left);
        while(ll < lr){
            if(rl < rr && compare(leftArray[ll],array[rl]) > 0){
                array[al++] = array[rl++];
            }else{
                array[al++] = leftArray[ll++];
            }
        }
    }
}
