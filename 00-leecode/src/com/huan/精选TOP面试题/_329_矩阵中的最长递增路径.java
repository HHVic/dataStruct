package com.huan.精选TOP面试题;

import org.junit.Test;

/**
 * @author:HuanK
 * @create:2021-03-02 12:07
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 */
public class _329_矩阵中的最长递增路径 {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] flag = new int[matrix.length][matrix[0].length];
        int max = 0;
        for(int i = 0;i < matrix.length;++i){
            for(int j = 0;j < matrix[0].length;++j){
                if (flag[i][j] == 0) max = Math.max(max,dfs(matrix,i,j,flag));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix,int i,int j,int[][] flag){
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length){
            return 0;
        }
        if(flag[i][j] > 0) return flag[i][j];
        int max = 0;
        if(i - 1 >= 0 && matrix[i][j] < matrix[i - 1][j]){
            max = Math.max(max,dfs(matrix,i - 1,j,flag));
        }
        if(j - 1 >= 0 && matrix[i][j] < matrix[i][j - 1]){
            max = Math.max(max,dfs(matrix,i,j - 1,flag));
        }
        if(i + 1 < matrix.length && matrix[i][j] < matrix[i + 1][j]){
            max = Math.max(max,dfs(matrix,i + 1,j,flag));
        }
        if(j + 1 < matrix[0].length && matrix[i][j] < matrix[i][j + 1]){
            max = Math.max(max,dfs(matrix,i,j + 1,flag));
        }
        flag[i][j] = max + 1;
        return flag[i][j];
    }

    @Test
    public void test(){
        int[][] matrix = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(longestIncreasingPath(matrix));
    }
}
