package com.huan.数组_排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:HuanK
 * @create:2021-02-24 9:58
 * https://leetcode-cn.com/problems/3sum/
 */
public class _15_三数之和 {
    //超时
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int left = 0;left < nums.length;++left){
            if(left > 0 && nums[left] == nums[left - 1]) continue;
            for(int mid = left + 1;mid < nums.length;++mid){
                if(left != mid - 1 && mid > 0 && nums[mid] == nums[mid - 1]) continue;
                int right = nums.length - 1;
                while(mid < right){
                    int sum = nums[left] + nums[mid] + nums[right];
                    if(sum < 0) break;
                    if(sum == 0){
                        addAll(res,nums[left],nums[mid],nums[right]);
                    }
                    --right;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int first = 0;first < nums.length;++first){
            if(first > 0 && nums[first] == nums[first - 1]) continue;
            for(int second = first + 1;second < nums.length;++second){
                if(first != second - 1 & second > 0 && nums[second] == nums[second - 1]) continue;
                int target = -(nums[first] + nums[second]);
                int left = second + 1;
                int right = nums.length - 1;
                int third;
                if((third = binarySearch(nums,left,right,target)) != -1){
                    addAll(res,nums[first],nums[second],nums[third]);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        // 三数和为0，即a,b,c 可以理解为 b + c = -a 即可
        List<List<Integer>> res = new ArrayList<>();
        // 初始判断，不满足三数条件，直接返回空集
        if(nums.length < 3){
            return res;
        }

        // 对数组进行排序，方便下一步操作
        Arrays.sort(nums);

        // 遍历数组，寻找满足条件的元素
        for(int i = 0; i < nums.length - 2; i++){
            // 思考1: 此时数组有序，如果当前元素大于0，那么0之后的元素不可能小于0
            if(nums[i] > 0) {
                return res;
            }

            // 思考2: 题目要求不可包含重复元素，此时数组有序，判断当前元素和上一个元素是否相等，然后跳过即可
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            // 开始寻找两数和为-nums[i]的元素
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            while(left < right){
                int num = nums[left] + nums[right];
                if(num < target){
                    // 如果后序两数和小于-nums[i]，则移动left
                    left ++;
                }else if(num > target){
                    // 如果后序两数和大于-nums[i]，则移动right
                    right --;
                }else{
                    // 如果都不是，则表示找到一组结果
                    addAll(res,nums[i],nums[left],nums[right]);

                    // 思考3: 左右指针移动时有无可能出现重复元素（有的），所以这里页需要去除重复元素
                    while(left < right && nums[left + 1] == nums[left]){
                        left ++;
                    }

                    while(left < right && nums[right - 1] == nums[right]){
                        right --;
                    }

                    // 注意找到一组元素后，也需要继续移动指针
                    ++left;
                    --right;
                }
            }

        }
        return res;
    }

    private void addAll(List<List<Integer>> result,int a,int b,int c){
        List<Integer> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        res.add(c);
        result.add(res);
    }

    private int binarySearch(int nums[],int left,int right,int target){
        while(left <= right){
            int mid = (left + right) >> 1;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
