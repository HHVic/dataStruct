package com.huan.精选TOP面试题;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class _53_最大子序和 {

    //dp解法
    //1.确定状态 dp[i] 表示以i结尾的最大子序和
    //2.定义初始状态 dp[0] = nums[0];
    //3.确定状态转移方程 若dp[i - 1] < 0 则dp[i] = nums[i]
    //                  反之dp[i] = dp[i - 1] + nums[i]
    public static int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        //定义初始状态
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1;i < nums.length;++i){
            dp[i] = Math.max(0,dp[i - 1]) + nums[i];
            max = Math.max(dp[i],max);
        }
        return max;
    }

    //分治解法
    //将原数组切分为两半[left,min)和[mid,right)
    //解[i,j]存在的情况有三种
    //1.存在[left,min)
    //2.存在[mid,right)
    //3.存在[i,mid) + [mid,j]
    public static int maxSubArray1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return maxSubArray(nums,0,nums.length);
    }
    public static int maxSubArray(int[] nums,int left,int right) {
        if(right - left < 2) return nums[left];
        int mid = (left + right) >> 1;
        //3.存在[i,mid) + [mid,j]
        int maxMid = maxMid(nums,left,mid,right);
        //1.存在[left,min)
        int maxLeft = maxSubArray(nums, left, mid);
        //2.存在[mid,right)
        int maxRight = maxSubArray(nums, mid, right);
        return Math.max(maxMid,Math.max(maxLeft,maxRight));
    }

    private static int maxMid(int[] nums, int left, int mid, int right) {
        int leftSum = nums[mid - 1],rightSum = nums[mid];
        int maxLeft = leftSum;
        for(int i = mid - 2;i >= left;--i){
            leftSum += nums[i];
            maxLeft = Math.max(leftSum,maxLeft);
        }
        int maxRight = rightSum;
        for(int i = mid + 1;i < right;++i){
            rightSum += nums[i];
            maxRight = Math.max(rightSum,maxRight);
        }
        return maxLeft + maxRight;
    }


    public static void main(String[] args) {
        System.out.println(maxSubArray1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
