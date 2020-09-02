package com.huan.回溯;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class _47_全排列II {

    List<List<Integer>> res;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        abled = new boolean[nums.length];
        mute(nums,0);
        //res.addAll(repeat);
        return res;
    }
    List<Integer> item = new ArrayList<>();
    Set<List<Integer>> repeat = new HashSet<>();
    boolean[] abled;

    private void mute(int[] nums,int start){
        if(start == nums.length){
            //放入item的深拷贝
            List<Integer> temp = new ArrayList<>(item);
            if(!res.contains(temp))res.add(temp);
            return ;
        }
        for (int i = 0;i < nums.length;++i) {
            if (abled[i]) continue;
            item.add(nums[i]);
            abled[i] = true;
            mute(nums, start + 1);
            item.remove(start);
            abled[i] = false;
        }
    }

    @Test
    public void test(){
//        int[] num = new int[]{
//                1,2,3,4
//        };
//        List<List<Integer>> permute = permuteUnique(num);

        int[] num1 = new int[]{
                2,2,1,1
        };
        List<List<Integer>> permute1 = permuteUnique(num1);
        System.out.println(permute1);
//        System.out.println(permute.size());
    }
}
