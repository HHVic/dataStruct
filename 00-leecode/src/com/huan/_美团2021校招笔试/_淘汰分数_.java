package com.huan._美团2021校招笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author:HuanK
 * @create:2021-03-02 15:23
 */
public class _淘汰分数_ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < a.length;++i) a[i] = sc.nextInt();
        System.out.println(helper(a,x,y));
    }
    private static int helper(int[] a,int x,int y){
        Arrays.sort(a);
        for(int i = 0;i < a.length;++i){
            if(x <= i + 1 && i + 1 <= y && a.length - i - 1 >= x && a.length - i - 1 <= y){
                return a[i];
            }
        }
        return -1;
    }
}
