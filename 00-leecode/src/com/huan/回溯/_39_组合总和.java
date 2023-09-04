package com.huan.回溯;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class _39_组合总和 {
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        dfs(candidates,0,target);
        //res.addAll(repeat);
        return res;
    }
    List<Integer> item = new ArrayList<>();

    Set<List<Integer>> repeat = new HashSet<>();
    public void dfs(int[] candidates,int start,int target){
        if(target == 0){
            //放入item的深拷贝
            List<Integer> temp = new ArrayList<>(item);
            //Collections.sort(temp);
            //repeat.add(temp);
            res.add(temp);
            return ;
        }
        if(target < 0) return ;
        for(int i = start;i < candidates.length;++i){
            //if(repeat.contains(candidates[i])) continue;
            item.add(candidates[i]);
            //repeat.add(candidates[i]);
            dfs(candidates,i,target - candidates[i]);
            item.remove(item.size() - 1);
            //repeat.remove(candidates[i]);
        }
    }


    @Test
    public void test(){
        int[] num1 = new int[]{
                2,3,5
        };
        List<List<Integer>> permute1 = combinationSum(num1,8);
        System.out.println(permute1);
    }
}
