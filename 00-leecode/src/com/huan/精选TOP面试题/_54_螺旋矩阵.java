package com.huan.精选TOP面试题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author:HuanK
 * @create:2021-02-25 17:08
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class _54_螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left,bottom,top,right;
        left = top = 0;
        bottom = matrix.length - 1;
        right = matrix[0].length - 1;
        while(left <= right && top <= bottom){
            for(int i = left;i <= right;++i){
                res.add(matrix[top][i]);
            }
            if(top >= bottom) break;
            for(int i = ++top;i <= bottom;++i){
                res.add(matrix[i][right]);
            }
            if(left >= right) break;
            for(int i = --right;i >= left;--i){
                res.add(matrix[bottom][i]);
            }
            for(int i = --bottom;i >= top;--i){
                res.add(matrix[i][left]);
            }
            ++left;
        }
        return res;
    }

    @Test
    public void test(){
        int[][] matrix = new int[][]{{1,2,3},{5,6,7},{9,10,11},{13,14,15}};
        System.out.println(spiralOrder(matrix));
    }
}
