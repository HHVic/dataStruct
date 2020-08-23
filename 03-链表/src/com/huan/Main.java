package com.huan;

import com.huan.circle.CircleLinkedList;
import com.huan.circle.SingleCircleLinkedList;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new CircleLinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(0,3);
        list.add(4,8);
        list.remove(4);
        System.out.println(list.indexOf(5));
        System.out.println(list);
    }
}
