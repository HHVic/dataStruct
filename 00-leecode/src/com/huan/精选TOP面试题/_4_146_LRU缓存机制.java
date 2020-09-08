package com.huan.精选TOP面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class _4_146_LRU缓存机制 {
    //思路:使用HashMap加双向链表
    Map<Integer, LinkedNode> cacheMap;
    int capacity;
    LinkedNode head;
    LinkedNode tail;

    public _4_146_LRU缓存机制(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
    }

    public int get(int key) {
        LinkedNode linkedNode = cacheMap.get(key);
        if (linkedNode != null) {
            remove(linkedNode);
            addFirst(linkedNode);
            return linkedNode.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        LinkedNode linkedNode = cacheMap.get(key);
        if(linkedNode != null){
            remove(linkedNode);
            addFirst(linkedNode);
            linkedNode.val = value;
            cacheMap.put(key,linkedNode);
        }else if(cacheMap.size() == capacity){
            linkedNode = new LinkedNode(key,value);
            cacheMap.remove(tail.key);
            removeLast();
            addFirst(linkedNode);
            cacheMap.put(key,linkedNode);
        }else{
            linkedNode = new LinkedNode(key,value);
            addFirst(linkedNode);
            cacheMap.put(key,linkedNode);
        }
    }

    private void removeLast() {
        //只有一个节点，清空
        if(head == tail) {
            head = tail = null;
        }else{
            tail.prev.next = null;
            tail = tail.prev;
        }
    }

    private void addFirst(LinkedNode node) {
        //添加的是第一个节点
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
    }

    private void remove(LinkedNode node) {
        //删除头节点
        if(node == head){
            removeFirst();
        }else if(node == tail){
            removeLast();
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private void removeFirst(){
        //只有一个节点，清空
        if(head == tail) {
            head = tail = null;
        }else{
            head.next.prev = null;
            head = head.next;
        }
    }



    private static class LinkedNode{
        LinkedNode prev;
        LinkedNode next;
        int val;
        int key;
        public LinkedNode(int key,int val){
            this.val = val;
            this.key = key;
        }

    }

    public static void main(String[] args) {
        _4_146_LRU缓存机制 cache = new _4_146_LRU缓存机制(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(4));

        System.out.println(cache.get(3));

        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5,5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
//        cache.put(2,1);
//        System.out.println(cache.get(2));
    }

}
