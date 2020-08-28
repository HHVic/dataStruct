package com.huan.heap;

public class HeapSort {

    public static void main(String[] args) {
        int[] nums = new int[]{
                52, 83, 30, 6, 34, 54, 24, 60, 4, 42, 65, 87
        };
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

    public static void sort(int[] nums){
        // 建堆 O(n)
        for (int i = (nums.length >> 1) - 1; i >= 0; i--) {
            siftDown(nums, i,nums.length);
        }
        // 开始排序
        for(int i = nums.length - 1;i >= 0;--i){
            change(nums,0,i);
            siftDown(nums,0,i);
        }
    }

    public static void change(int[] nums,int a,int b){
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    public static void siftDown(int[] nums,int index,int end){
        int data = nums[index];
        int leftIndex;
        while(index < (nums.length >> 1) && (leftIndex = (index << 1) + 1) < end){
            int leftData = nums[leftIndex];
            int rightIndex;
            if((rightIndex = leftIndex + 1) < end && nums[rightIndex] > leftData){
                leftData = nums[leftIndex = rightIndex];
            }
            if(data >= leftData) break;;
            nums[index] = leftData;
            index = leftIndex;
        }
        nums[index] = data;
    }
}
