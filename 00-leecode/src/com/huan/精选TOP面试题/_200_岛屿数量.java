package com.huan.精选TOP面试题;

/**
 * @author:HuanK
 * @create:2021-03-01 16:02
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class _200_岛屿数量 {
    //思路：遍历表格遇到 '1'则dfs每次将'1'置为'0'   结果统计遇到多少次'1'
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int res = 0;
        for(int i = 0;i < grid.length;++i){
            for(int j = 0;j < grid[0].length;++j){
                if(grid[i][j] == '0') continue;
                ++res;
                dfs(grid,i,j);
            }
        }
        return res;
    }

    private void dfs(char[][] grid,int i,int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return ;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }
}
