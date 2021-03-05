package com.huan._美团2021校招笔试;

import java.io.*;
import java.util.*;

/**
 * @author:HuanK
 * @create:2021-03-02 16:20
 */
public class _公司食堂_ {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        for(int i = 0;i < T;++i){
            int n = Integer.parseInt(reader.readLine());
            int[] nums = new int[n];
            char[] temp = reader.readLine().toCharArray();
            for(int j = 0;j < temp.length;++j){
                nums[j] = temp[j] - '0';
            }
            int m = Integer.parseInt(reader.readLine());
            char[] arr = reader.readLine().toCharArray();
            List<Integer> res = helper(nums,arr);
            for (Integer x : res) {
                writer.write((Integer.valueOf(x + 1).toString()));
                writer.newLine();
            }
        }
        writer.flush();
    }

    private static List<Integer> helper(int[] nums, char[] arr) {
        //0人
        Queue<Integer> queue1 = new PriorityQueue<>();
        //1人
        Queue<Integer> queue2 = new PriorityQueue<>();
        List<Integer> res = new ArrayList<>();
        for(int i = 0;i < nums.length;++i){
            if(nums[i] == 0) {
                queue1.offer(i);
            }else if(nums[i] == 1){
                queue2.offer(i);
            }
        }
        for(int i = 0;i < arr.length;++i){
            if(arr[i] == 'M'){
                if(!queue2.isEmpty()){
                    res.add(queue2.poll());
                }else{
                    if(!queue1.isEmpty()){
                        int poll = queue1.poll();
                        res.add(poll);
                        queue2.offer(poll);
                    }
                }
            }else{
                if(!queue1.isEmpty()){
                    int poll = queue1.poll();
                    res.add(poll);
                    queue2.offer(poll);
                }else{
                    if(!queue2.isEmpty()){
                        res.add(queue2.poll());
                    }
                }
            }
        }
        return res;
    }
}
