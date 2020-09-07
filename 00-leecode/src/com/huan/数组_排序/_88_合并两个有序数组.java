package com.huan.数组_排序;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _88_合并两个有序数组 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 分别指向两个数组
        int i = 0,j = 0;
        // 覆盖到哪了
        int t = 0;
        // 拷贝一份nums1
        int[] copy = new int[m];
        System.arraycopy(nums1,0,copy,0,m);
        while(i < m && j < n){
            if(copy[i] > nums2[j]){
                // nums2[j] 比 copy[i] 小
                nums1[t++] = nums2[j++];
            }else {
                nums1[t++] = copy[i++];
            }
        }
        // nums1 或者 nums2遍历完毕
        // nums2 未遍历完，拷贝到nums1 中
        while(j < n){
            nums1[t++] = nums2[j++];
        }
        // nums1 未遍历完 拷贝到nums1 中
        while(i < m){
            nums1[t++] = copy[i++];
        }
    }

    /**
     * 从后往前扫描
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        //指向nums1数组的指针
        int i1 = m - 1;
        //指向nums2数组的指针
        int i2 = n - 1;
        //合并的指针
        int cur = nums1.length - 1;
        while(i2 >= 0){
            if(i1 >= 0 && nums1[i1] > nums2[i2]){
                nums1[cur--] = nums1[i1--];
            }else{
                //i1 < 0 || nums1[i1] <= nums2[i2]
                nums1[cur--] = nums2[i2--];
            }
        }
    }
}
