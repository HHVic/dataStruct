package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _216_组合总和III {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> item = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {

        if (k * 9 < n) return result;
        dfs(1, k, n);
        return result;
    }

    private void dfs(int start,int k,int remain) {

        if(item.size() == k && remain == 0){
            result.add(new ArrayList<>(item));
            return ;
        }
        for (int i = start; i < 10; ++i) {
            if(remain - i < 0) return ;
            item.add(i);
            dfs(i + 1,k,remain - i);
            item.remove(item.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> permute1 = combinationSum3(3,9);
        System.out.println(permute1);
//        System.out.println(permute.size());
    }
}
