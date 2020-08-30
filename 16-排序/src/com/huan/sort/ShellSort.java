package com.huan.sort;

import com.huan.Sort;
import com.huan.tools.Integers;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<T> extends Sort<T> {
    @Override
    public void sort() {
        List<Integer> newArray = shellSortArray();
        for(int x : newArray){
            sort(x);
        }
    }

    /**
     * 划分序列为divider进行插入排序
     * @param divider
     */
    private void sort(int divider){
        for(int col = 0;col < divider;++col){
            for(int start = col + divider;start < array.length;start += divider){
                T temp = array[start];
                int cur = start;
                while(cur > col && compare(temp,array[cur - divider]) < 0){
                    array[cur] = array[cur -= divider];
                }
                array[cur] = temp;
            }
        }
    }

    private List<Integer> shellSortArray(){
        //  n / 2^k  k = 1,2,3...
        List<Integer> newArray = new ArrayList<>();
        int n = array.length;
        while((n = n / 2) > 0){
            newArray.add(n);
        }
        return newArray;
    }

//    public static void main(String[] args) {
//        Integer[] random = Integers.random(32, 1, 1);
//        List<Integer> newArray = new ShellSort<Integer>().shellSortArray(random);
//        newArray.forEach(System.out::println);
//    }
}
