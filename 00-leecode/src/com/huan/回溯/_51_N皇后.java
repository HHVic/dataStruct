package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/
 *  AC 3 ms, 在所有 Java 提交中击败了86.58%的用户
 */
public class _51_N皇后 {

    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        cols = new int[n];
        place(0);
        return res;
    }

    //索引是行号，值是列 cols[2] = 3表示第二行第三列摆了皇后
    int[] cols;

    /**
     * 在第row行摆皇后
     * @param row
     */
    public void place(int row){
        if(row == cols.length){
            List<String>  item = new ArrayList<>();
            for (int col : cols) {
                //[i,cols[i]]
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < col; j++) {
                    sb.append(".");
                }
                sb.append("Q");
                for (int j = col + 1; j < cols.length; j++) {
                    sb.append(".");
                }
                item.add(sb.toString());
            }
            System.out.println(item);
            res.add(item);
            return ;
        }
        for(int col = 0;col < cols.length;++col){
            if(isValid(row,col)){
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    /**
     * 第row行第col列是否可以摆放皇后
     * @param row
     * @param col
     * @return
     */
    public boolean isValid(int row,int col){
        for(int i = 0;i < row;++i){
            //摆放皇后  i行cols[i]列
            if(cols[i] == col) return false;
            if(row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    @Test
    public void test(){
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists);
    }
}
