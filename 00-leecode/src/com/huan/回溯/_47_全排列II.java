package com.huan.回溯;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class _47_全排列II {

    //    List<List<Integer>> res;
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        res = new ArrayList<>();
//        abled = new boolean[nums.length];
//        mute(nums,0);
//        //res.addAll(repeat);
//        return res;
//    }
//    List<Integer> item = new ArrayList<>();
//    Set<List<Integer>> repeat = new HashSet<>();
//    boolean[] abled;
//
//    private void mute(int[] nums,int start){
//        if(start == nums.length){
//            //放入item的深拷贝
//            List<Integer> temp = new ArrayList<>(item);
//            if(!res.contains(temp))res.add(temp);
//            return ;
//        }
//        for (int i = 0;i < nums.length;++i) {
//            if (abled[i]) continue;
//            item.add(nums[i]);
//            abled[i] = true;
//            mute(nums, start + 1);
//            item.remove(start);
//            abled[i] = false;
//        }
//    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, nums, result);
        return result;
    }

    private void dfs(int idx, int[] nums, List<List<Integer>> result) {
        if (idx == nums.length) {
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                item.add(nums[i]);
            }
            result.add(item);
            return;
        }
        for (int i = idx; i < nums.length; ++i) {
            if (repeat(nums, i, idx)) continue;
            swap(nums, i, idx);
            dfs(idx + 1, nums, result);
            swap(nums, i, idx);
        }
    }

    private boolean repeat(int[] nums, int i, int idx) {
        for (int j = idx; j < i; ++j) {
            if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    private void swap(int[] nums, int i, int idx) {
        int temp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = temp;
    }

    @Test
    public void test() {
//        int[] num = new int[]{
//                1,2,3,4
//        };
//        List<List<Integer>> permute = permuteUnique(num);

        int[] num1 = new int[]{
                2, 2, 1, 1
        };
        List<List<Integer>> permute1 = permuteUnique(num1);
        System.out.println(permute1);
//        System.out.println(permute.size());
    }
}
