package com.huan.精选TOP面试题;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:HuanK
 * @create:2021-02-25 16:07
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class _146_LRU缓存机制 {

    private static class LRUCache {

        //思路:使用HashMap加双向链表
        Map<Integer, LinkedNode> cacheMap;
        int capacity;
        LinkedNode head;
        LinkedNode tail;

        public LRUCache(int capacity) {
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
    }

    private static class LRUCache1 extends LinkedHashMap<Integer,Integer> {
        int capacity;

        public LRUCache1(int capacity) {
            super(capacity,0.75F,true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.get(key);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return this.size() > capacity;
        }
    }
}
