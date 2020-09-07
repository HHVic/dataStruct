package com.huan.数组_排序;

/**
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 */
public class _977_有序数组的平方 {
    /*
    给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     */
    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        int i = 0, j = A.length - 1, k = A.length - 1;
        while (i <= j) {
            if (A[i] + A[j] < 0) {
                ans[k--] = A[i] * A[i];
                i++;
            } else {
                ans[k--] = A[j] * A[j];
                j--;
            }
        }
        return ans;
    }
}
