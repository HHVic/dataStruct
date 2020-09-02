package com.huan.回溯;

import org.junit.Test;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens-ii/
 */
public class _52_N皇后II {


    //判断第i列是否有皇后000001000
    short cols;
    //判断对角线是否有皇后（左上 -> 右下）
    int leftTop;
    //判断对角线是否有皇后（右上 -> 左下）
    int rightTop;
    //多少种摆法
    int ways;

    public int totalNQueens(int n) {
        place(0, n);
        return ways;
    }

    private void place(int row, int n) {
        if(row == n){
            ++ways;
            return ;
        }
        for (int col = 0; col < n; ++col) {
            int colsPlace = (1 << col);
            int leftTopPlace = (1 << (row - col + n - 1));
            int rightTopPlace = (1 << (row + col));
            if ((cols & colsPlace) != 0) continue;
            if((leftTop & leftTopPlace) != 0) continue;
            if((rightTop & rightTopPlace) != 0) continue;
            //可以摆
            cols |= colsPlace;
            leftTop |= leftTopPlace;
            rightTop |= rightTopPlace;
            place(row + 1,n);
            //回溯还原 1还原成0   ~colsPlace & cols
            cols &= ~colsPlace;
            leftTop &= ~leftTopPlace;
            rightTop &= ~rightTopPlace;
        }
    }

    @Test
    public void test() {
        System.out.println(totalNQueens(8));
    }
}
