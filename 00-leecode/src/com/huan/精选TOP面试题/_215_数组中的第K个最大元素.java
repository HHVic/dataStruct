package com.huan.精选TOP面试题;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author:HuanK
 * @create:2021-03-02 14:11
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class _215_数组中的第K个最大元素 {

    //大顶堆
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k,(o1,o2) -> o2 - o1);
        for(int i = 0;i < nums.length;++i){
            queue.offer(nums[i]);
        }
        for(int i = 0;i < k - 1;++i){
            queue.poll();
        }
        return queue.poll();
    }

    //小顶堆
    public int findKthLargest1(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i = 0;i < k;++i){
            queue.offer(nums[i]);
        }
        for(int i = k;i < nums.length;++i){
            if(nums[i] > queue.peek()){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
    //分治
    public int findKthLargest2(int[] nums,int k){
        return sort(nums,0,nums.length - 1,k);
    }

    int res = 0;
    private int sort(int[] nums,int left,int right,int k){
        if(left >= right) return nums[left];
        int pivot = partition(nums,left,right);
        if(pivot == k - 1) return nums[pivot];
        if(pivot > k - 1) {
            return sort(nums,left,pivot - 1,k);
        }else{
            return sort(nums,pivot + 1,right,k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int temp = nums[left];
        while(left < right){
            while(left < right && nums[right] <= temp) --right;
            nums[left] = nums[right];
            while(left < right && nums[left] >= temp) ++left;
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    @Test
    public void test(){
        int[] nums = new int[]{3,2};
        System.out.println(findKthLargest2(nums, 1));
    }

}
