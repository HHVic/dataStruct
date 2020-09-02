package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * AC 2 ms, 在所有 Java 提交中击败了83.03%的用户
 */
public class _46_全排列 {

    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        mute(nums,0);
        return res;
    }
    List<Integer> item = new ArrayList<>();

    private void mute(int[] nums,int start){
        if(start == nums.length){
            //放入item的深拷贝
            List<Integer> temp = new ArrayList<>(item);
            res.add(temp);
            return ;
        }
        for (int num : nums) {
            if (item.contains(num)) continue;
            item.add(num);
            mute(nums, start + 1);
            item.remove((Integer) num);
        }
    }



    @Test
    public void test(){
        int[] num = new int[]{
                1,2,3
        };
        List<List<Integer>> permute = permute(num);
        System.out.println(permute);
    }
}
