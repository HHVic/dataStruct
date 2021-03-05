package com.huan.精选TOP面试题;

import org.junit.Test;

/**
 * @author:HuanK
 * @create:2021-02-27 22:01
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class _300_最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        int max = 1;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1;i < n;++i){
            dp[i] = 1;
            for(int j = 0;j < i;++j){
                if(nums[j] >= nums[i]) continue;
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }


    //思路 采用贪心 + 二分查找
    //贪心： 使用上升最慢的串的长度作为最长上升子序列长度
    //dp[i]数组表示  长度为i + 1的最长上升子序列末尾元素的最小值
    //由于dp数组是一个递增数组，所以可用二分查找
    //时间复杂度为n(logn)
    //举例：10，9，2，5，3，7，101，18
    //dp[0] = 10
    //第一步插入9   10 > 9 并且dp数组中没有比9小的数  k = -1  dp = 【9】   len = 0
    //第二部插入2   9 > 2 并且dp数组中没有比2小的数   k = -1  dp = 【2】   len = 0
    //第三步插入5   2 < 5 直接插入dp数组后面   dp = 【2，5】
    //第四步插入3   5 > 3 查找数组中最大的比3小的数为2   dp = 【2，3】
    //第五步插入7   3 < 7 直接插入数组后面    dp = 【2，3，7】
    //第六部插入101   7 < 101 直接插入数组后面  dp = 【2，3，7，101】
    //第七步插入18   101 > 18  查找dp数组最大的比18小的数7   dp = 【2，3，7，18】  最终数组下标为3  长度为4
    public int lengthOfLIS1(int[] nums) {
        //下标，结果是下标+1
        int len = 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1;i < n;++i){
            //当前元素大于dp数组的末尾值 直接添加后面
            if(nums[i] > dp[len]){
                dp[++len] = nums[i];
                continue;
            }
            int l = 0;
            int r = len;
            int k = -1;
            //在dp数组中二分查找 最大的小于nums[i]的数
            while(l <= r){
                int mid = (l + r) >> 1;
                if(dp[mid] < nums[i]){
                    k = mid;
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
            //再找到数后面添加nums[i]
            dp[k + 1] = nums[i];
        }
        return len + 1;
    }

    @Test
    public void test(){
        int[] nums = new int[]{0,1,0,3,2,3};
        System.out.println(lengthOfLIS1(nums));
    }
}
