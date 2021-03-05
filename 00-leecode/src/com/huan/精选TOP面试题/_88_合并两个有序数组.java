package com.huan.精选TOP面试题;

/**
 * @author:HuanK
 * @create:2021-03-02 13:03
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class _88_合并两个有序数组 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //思路一 借助一个小数组存放nums1中元素  省略
        //思路二 倒着遍历，选最大的，nums2遍历完毕及结束
        int i = m - 1,j = n - 1,cur = m + n - 1;
        while(j >= 0){
            if(i >= 0 && nums1[i] > nums2[j]){
                nums1[cur--] = nums1[i--];
            }else{
                nums1[cur--] = nums2[j--];
            }
        }
    }
}
