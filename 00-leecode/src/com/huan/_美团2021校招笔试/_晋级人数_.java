package com.huan._美团2021校招笔试;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author:HuanK
 * @create:2021-03-03 16:14
 */
public class _晋级人数_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i < nums.length;++i) nums[i] = sc.nextInt();
        System.out.println(helper(nums,x));
    }

    private static int helper(int[] nums, int x) {
        Arrays.sort(nums);
        int count = 0;
        if(nums[nums.length - x] != 0) {
            int score = nums[nums.length - x];
            for(int i = nums.length - x;i >= 0;--i){
                if(nums[i] != score) break;
                ++count;
            }
            return x + count - 1;
        }
        int i = nums.length - x;

        while(i < nums.length){
            if(nums[i] != 0) break;
            ++i;
            ++count;
        }
        return x - count;
    }
}
