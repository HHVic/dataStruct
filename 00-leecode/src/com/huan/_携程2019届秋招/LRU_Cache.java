package com.huan._携程2019届秋招;

import java.util.*;
public class LRU_Cache{
    public static void LRUCache(Scanner sc){
        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<Integer,Integer> mmap = new LinkedHashMap<>();
        int curr = 0;
        String[] temp;
        int key,value;
        while (sc.hasNext()){
            temp = sc.nextLine().split(" ");
            key = Integer.parseInt(temp[1]);
            if ("p".equals(temp[0])){
                value = Integer.parseInt(temp[2]);
                if (mmap.containsKey(key)){//覆盖已有，其它什么都不做
                    mmap.put(key,value);
                }
                else if (curr==n){//如果队满了，插入新值并删除队首元素。
                    mmap.put(key,value);
                    key = mmap.entrySet().iterator().next().getKey();//队首元素key
                    mmap.remove(key);
                }else {
                    mmap.put(key,value);
                    curr++;
                }
            }else {
                if (mmap.containsKey(key)){//查询已有值，然后删除再插入在队尾
                    value = mmap.get(key);
                    System.out.println(value);
                    mmap.remove(key);
                    mmap.put(key,value);
                }else System.out.println(-1);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LRUCache(sc);
    }
}