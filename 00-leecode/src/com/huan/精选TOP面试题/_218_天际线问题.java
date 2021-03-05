package com.huan.精选TOP面试题;

import com.sun.scenario.effect.Merge;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author:HuanK
 * @create:2021-03-01 10:03
 * https://leetcode-cn.com/problems/the-skyline-problem/
 */
public class _218_天际线问题 {
    //分治思想
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings.length == 0) return new ArrayList<>();
        return getSkyline(buildings,0,buildings.length);
    }

    //从[left,right)区间
    private List<List<Integer>> getSkyline(int[][] buildings,int left,int right){
        if(right - left < 2){
            List<List<Integer>> res = new ArrayList<>();
            res.add(Arrays.asList(buildings[left][0],buildings[left][2]));
            res.add(Arrays.asList(buildings[left][1],0));
            return res;
        }
        int mid = (left + right) >> 1;
        List<List<Integer>> leftSkyline = getSkyline(buildings,left,mid);
        List<List<Integer>> rightSkyline = getSkyline(buildings,mid,right);
        return merge(leftSkyline,rightSkyline);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
        int lLeft = 0;
        int lRight = leftSkyline.size();
        int rLeft = 0;
        int rRight = rightSkyline.size();
        int lh = 0;
        int rh = 0;
        int curX = 0;
        List<List<Integer>> res = new ArrayList<>();
        while(lLeft < lRight && rLeft < rRight){
            int lX = leftSkyline.get(lLeft).get(0);
            int rX = rightSkyline.get(rLeft).get(0);
            if(lX < rX){
                curX = lX;
                lh = leftSkyline.get(lLeft).get(1);
                ++lLeft;
            }else if(lX > rX){
                curX = rX;
                rh = rightSkyline.get(rLeft).get(1);
                ++rLeft;
            }else{
                curX = lX;
                lh = leftSkyline.get(lLeft).get(1);
                rh = rightSkyline.get(rLeft).get(1);
                ++lLeft;
                ++rLeft;
            }
            int height = Math.max(lh,rh);
            if(!res.isEmpty() && height == res.get(res.size() - 1).get(1)) continue;
            res.add(Arrays.asList(curX,height));
        }
        while(lLeft < lRight){
            int height = leftSkyline.get(lLeft).get(1);
            if(!res.isEmpty() && height == res.get(res.size() - 1).get(1)) continue;
            res.add(Arrays.asList(leftSkyline.get(lLeft++).get(0),height));
        }
        while(rLeft < rRight){
            int height = rightSkyline.get(rLeft).get(1);
            if(!res.isEmpty() && height == res.get(res.size() - 1).get(1)) continue;
            res.add(Arrays.asList(rightSkyline.get(rLeft++).get(0),height));
        }
        System.out.println(res);
        return res;
    }
    @Test
    public void test(){
        int[][] buildings = new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(getSkyline(buildings));
    }
}
