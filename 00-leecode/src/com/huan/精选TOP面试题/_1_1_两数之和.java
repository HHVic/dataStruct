package com.huan.精选TOP面试题;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/two-sum/
 */
public class _1_1_两数之和 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;++i){
            int temp = target - nums[i];
            if(map.containsKey(temp)) return new int[]{i,map.get(temp)};
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
