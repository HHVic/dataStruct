package com.huan.精选TOP面试题;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author:HuanK
 * @create:2021-02-24 11:24
 *
 */
public class _149_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int res = 0;
        //遍历每个点
        for (int i = 0; i < points.length; i++) {
            int duplicate = 0;
            int max = 0;//保存经过当前点的直线中，最多的点
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                //求出分子分母
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    duplicate++;
                    continue;

                }
                //进行约分
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;
                String key = x + "@" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            //1 代表当前考虑的点，duplicate 代表和当前的点重复的点
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int maxPoints1(int[][] points) {
        int n = points.length;
        if(n < 3) return n;
        int res = 0;
        for(int i = 0;i < n - 1;++i){
            //经过该点的直线中最多点的个数
            int max = 0;
            //重复点
            int duplicate = 1;
            //垂直点   x2 - x1 = 0;
            int vCount = 0;
            //水平点   y2 - y1 = 0;
            int hCount = 0;
            Map<Double,Integer> map = new HashMap<>();
            for(int j = i + 1;j < n;++j){
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if(dx == 0 && dy == 0){
                    ++duplicate;
                    continue;
                }
                if(dx == 0){
                    ++vCount;
                    continue;
                }
                if(dy == 0){
                    ++hCount;
                    continue;
                }
                // 处理精度问题
                Double key = dy * 1000000000.0 / dx;
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                max = Math.max(max, count);
            }
            max = Math.max(max,vCount);
            max = Math.max(max,hCount);
            res = Math.max(max + duplicate,res);
        }
        return res;
    }

    @Test
    public void test(){
        int[][] points = new int[][]{
                {1,1},{3,2},{5,3},{4,1},{2,3},{1,4}
        };
        System.out.println(maxPoints1(points));
    }
}