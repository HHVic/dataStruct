package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class _40_组合总和II {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> item = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,target,0);
        return result;
    }

    private void dfs(int[] candidates,int target,int start){
        if(target == 0){
            result.add(new ArrayList<>(item));
            return ;
        }
        for(int i = start;i < candidates.length;++i){
            if(target - candidates[i] < 0) return ;
            if(i > start && candidates[i] == candidates[i - 1]){
                continue;
            }
            item.add(candidates[i]);
            dfs(candidates,target - candidates[i],i + 1);
            item.remove(item.size() - 1);
        }
    }

    @Test
    public void test(){
        List<List<Integer>> lists = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(lists);
    }
}
