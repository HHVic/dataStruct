package com.huan._美团2021校招笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author:HuanK
 * @create:2021-03-03 15:43
 */
public class _糕点_ {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int[] nums = new int[m];
            for(int i = 0;i < m;++i){
                nums[i] = sc.nextInt();
            }
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }
            System.out.println(helper(n,m,a,b,nums));
        }
    }

    private static String helper(int n, int m, int a, int b, int[] nums) {
        Arrays.sort(nums);
        if(n - m > 1) return "YES";
        int small = nums[0];
        int big = nums[m - 1];
        if(small == a && big == b) return "YES";
        if(small == a && n - m > 0 || big == b && n - m > 0) return "YES";
        return "NO";
    }
}
