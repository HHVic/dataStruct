package com.huan.数组_排序;

/**
 * https://leetcode-cn.com/problems/sort-colors/  荷兰国旗(三指针)
 * 0 ms , 在所有 Java 提交中击败了100.00%的用户
 */
public class _75_颜色分类 {

    /*
    给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     */
    public static void sortColors(int[] nums) {
        //题意就是按照012顺序进行排序
        //思路：定义三个指针
        // left:存放0的位置
        // right:存放2的位置
        // i:扫描指针 遇到2和right交换 同时right--，遇到0就和left交换同时left++ i++
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while(i <= right){
            if(nums[i] == 2){
                swap(nums,i,right--);
            }else if(nums[i] == 0){
                swap(nums,i++,left++);
            }else{
                ++i;
            }
        }
    }
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        sortColors(new int[]{2,0,2,1,1,0});
    }
}
