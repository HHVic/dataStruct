package com.huan.精选TOP面试题;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author:HuanK
 * @create:2021-03-01 11:55
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class _378_有序矩阵中第K小的元素 {
    public int kthSmallest(int[][] matrix, int k) {
        //归并排序
        int[] nums = divid(matrix,0,matrix.length);
        //System.out.println(Arrays.toString(nums));
        return nums[k - 1];
    }

    private int[] divid(int[][] matrix,int top,int bottom){
        if(bottom - top < 2){
            return matrix[top];
        }
        int mid = (top + bottom) >> 1;
        int[] nums1 = divid(matrix,top,mid);
        int[] nums2 = divid(matrix,mid,bottom);
        return merge(nums1,nums2);
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l = l1 + l2;
        int[] nums = new int[l];
        int cur = 0;
        int i = 0;
        int j = 0;
        while(i < l1 && j < l2){
            if(nums1[i] < nums2[j]) {
                nums[cur++] = nums1[i++];
            }else{
                nums[cur++] = nums2[j++];
            }
        }
        if(i < l1) System.arraycopy(nums1,i,nums,cur,l1 - i);
        if(j < l2) System.arraycopy(nums2,j,nums,cur,l2 - j);
        return nums;
    }

    public int kthSmallest1(int[][] matrix,int k){
        PriorityQueue<Index> queue = new PriorityQueue<>((index1,index2) -> index1.value - index2.value);
        int n = matrix.length;
        boolean[][] flag = new boolean[n][n];
        if(k == 1) return matrix[0][0];
        int cur = 1;
        queue.offer(new Index(0,0,matrix[0][0]));
        flag[0][0] = true;
        while(!queue.isEmpty()){
            Index index = queue.poll();
            if(cur++ == k) {
                return index.value;
            }
            int i = index.i;
            int j = index.j;
            if(i < n - 1 && !flag[i + 1][j]) {
                queue.offer(new Index(i + 1,j,matrix[i + 1][j]));
                flag[i + 1][j] = true;
            }
            if(j < n - 1 && !flag[i][j + 1]) {
                queue.offer(new Index(i,j + 1,matrix[i][j + 1]));
                flag[i][j + 1] = true;
            }
        }
        return matrix[n - 1][n - 1];
    }

    private static class Index{
        int i;
        int j;
        int value;

        @Override
        public String toString() {
            return "Index{" +
                    "i=" + i +
                    ", j=" + j +
                    ", value=" + value +
                    '}';
        }

        public Index(int i, int j, int value){
            this.i = i;
            this.j = j;
            this.value = value;

        }
    }

    @Test
    public void test(){
        int[][] matrix = new int[][]{{1,3,5},{6,7,12},{11,14,14}};
        System.out.println(kthSmallest1(matrix,6));
    }
}
