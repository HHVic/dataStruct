package com.huan.map;

public class Main {

    public static void main(String[] args) {
        Map<String,Integer> map = new TreeMap<>();
        System.out.println(map.put("aaa", 1));
        System.out.println(map.put("aba", 6));
        System.out.println(map.put("baa", 5));
        System.out.println(map.put("ed", 10));
        System.out.println(map.put("aaa", 30));
        System.out.println(map.put("bac", 39));

        System.out.println("===========");
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key:" + key + "-> value:" + value);
                return false;
            }
        });

        map.remove("aaa");
    }
}
