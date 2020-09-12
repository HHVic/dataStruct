package com.huan.回溯;

import org.junit.Test;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutation-sequence/
 */
public class _60_第k个排列 {
    char[] chars;
    String result;
    int count = 0;
    public String getPermutation(int n, int k) {
        chars = new char[n];
        dfs(1,n,k);
        return result;
    }

    private void dfs(int start,int n,int k){
        if(count > k) return ;
        if(start - 1 == n){
            if(count++ == k){
                result = new String(chars);
                return ;
            }
            return ;
        }
        for(int i = 1;i <= n;++i){
            chars[i - 1] = (char) (i + '0');
            dfs(start + 1,n,k);
        }
    }

    @Test
    public void test(){
        System.out.println(getPermutation(4,9));
    }
}
