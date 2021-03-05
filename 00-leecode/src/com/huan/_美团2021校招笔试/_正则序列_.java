package com.huan._美团2021校招笔试;

import javax.print.attribute.standard.Finishings;
import java.util.Scanner;

/**
 * @author:HuanK
 * @create:2021-03-02 15:42
 */
public class _正则序列_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < n;++i){
            a[i] = sc.nextInt();
        }
        System.out.println(helper(a));
    }

    private static int helper(int[] a) {
        int count = 0;
        boolean[] nums = new boolean[a.length + 1];
        for(int i = 0;i < a.length;++i){
            if(a[i] >= 1 && a[i] <= a.length){
                count += Math.abs(a[i] - find(a,nums,a[i]));
            }else if(a[i] < 1){
                count += Math.abs(a[i] - find(a,nums,1));
            }else {
                count += Math.abs(a[i] - find(a,nums,nums.length));
            }
        }
        return count;
    }
    private static int find(int[] a,boolean[] nums,int j){
        while (j >= 0 && j <= nums.length - 1){
            if(!nums[j]) break;
            if(j + 1 <= nums.length - 1){
                ++j;
            }else if(j - 1 >= 0){
                --j;
            }
        }
        nums[j] = true;
        return j;
    }
}
