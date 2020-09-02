package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/
 *
 * AC 2 ms, 在所有 Java 提交中击败了95.27%的用户
 */
public class _51_N皇后_优化 {

    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        return res;
    }

    //索引是行号，值是列 queens[2] = 3表示第二行第三列摆了皇后
    int[] queens;
    //判断第i列是否有皇后
    boolean[] cols;
    //判断对角线是否有皇后（左上 -> 右下）
    boolean[] leftTop;
    //判断对角线是否有皇后（右上 -> 左下）
    boolean[] rightTop;

    /**
     * 在第row行摆皇后
     * @param row
     */
    public void place(int row){
        if(row == queens.length){
            save();
            return ;
        }
        for(int col = 0; col < queens.length; ++col){
            //判断该位置是否有皇后
            //该列有皇后
            if(cols[col]) continue;
            //leftTop有皇后
            int lt = row - col + queens.length - 1;
            if(leftTop[lt]) continue;
            //rightTop有皇后
            int rt = row + col;
            if(rightTop[rt]) continue;
            //可以摆
            queens[row] = col;
            cols[col] = true;
            leftTop[lt] = true;
            rightTop[rt] = true;
            //摆下一行
            place(row + 1);
            //回溯恢复状态
            cols[col] = false;
            leftTop[lt] = false;
            rightTop[rt] = false;
        }
    }

    private void save() {
        List<String> item = new ArrayList<>();
        for (int col : queens) {
            //[i,cols[i]]
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                sb.append(".");
            }
            sb.append("Q");
            for (int j = col + 1; j < queens.length; j++) {
                sb.append(".");
            }
            item.add(sb.toString());
        }
        res.add(item);
    }

    @Test
    public void test(){
        List<List<String>> lists = solveNQueens(11);
        System.out.println("size = " + lists.size());
    //    System.out.println(lists);
    }
}
