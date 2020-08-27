package com.huan.堆;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * topk问题（小顶堆实现）
 */
public class _剑指Offer_40_最小的k个数 {
    public static int[] getLeastNumbers(int[] arr, int k) {
        if(arr == null || arr.length <= 0 || k == 0){
            return new int[]{};
        }
        int[] res = new int[k];
        int t = 0;
        for(int i = 0;i < arr.length;++i){
            if(i < k){
                res[t] = arr[i];
                siftUp(res,t++);
            }else if(arr[i] < res[0]){
                res[0] = arr[i];
                siftDown(res,0);
            }
        }
        return res;
    }
    public static void siftDown(int[] res,int index){
        int data = res[index];
        while(index < (res.length >> 1)){
            int leftIndex = (index << 1) + 1;
            int leftData = res[leftIndex];
            int rightIndex;
            if((rightIndex = leftIndex + 1) < res.length && res[rightIndex] > res[leftIndex]){
                leftData = res[leftIndex = rightIndex];
            }
            if(leftData <= data) break;
            res[index] = leftData;
            index = leftIndex;
        }
        res[index] = data;
    }

    public static void siftUp(int[] res,int index){
        int data = res[index];
        while(index > 0){
            int parentIndex = (index - 1) >> 1;
            if(res[parentIndex] >= data) break;
            res[index] = res[parentIndex];
            index = parentIndex;
        }
        res[index] = data;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{
                0,1,1,1,4,5,3,7,7,8,10,2,7,8,0,5,2,16,12,1,19,
                15,5,18,2,2,22,15,8,22,17,6,22,6,22,26,32,8,10,
                11,2,26,9,12,9,7,28,33,20,7,2,17,44,3,52,27,2,
                23,19,56,56,58,36,31,1,19,19,6,65,49,27,63,29,1,
                69,47,56,61,40,43,10,71,60,66,42,44,10,12,83,69,
                73,2,65,93,92,47,35,39,13,75


        };

        int[] numbers = getLeastNumbers(nums, 75);
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ",");
        }
        System.out.println();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }
}
