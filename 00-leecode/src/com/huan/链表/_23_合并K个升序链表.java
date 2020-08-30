package com.huan.链表;

import com.huan.链表.dataType.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * AC 1 ms , 在所有 Java 提交中击败了100.00% 的用户
 */
public class _23_合并K个升序链表 {

    @Test
    public void test1(){
        ListNode[] lists = new ListNode[3];
        ListNode root1 = new ListNode(1);
        ListNode root2 = new ListNode(1);
        ListNode root3 = new ListNode(2);
        ListNode node1 = root1;
        ListNode node2 = root2;
        ListNode node3 = root3;
        node1.next = new ListNode(4);
        node1 = node1.next;
        node1.next = new ListNode(5);
        node1 = node1.next;
        node2.next = new ListNode(3);
        node2 = node2.next;
        node2.next = new ListNode(4);
        node2 = node2.next;
        node3.next = new ListNode(6);
        lists[0] = root1;
        lists[1] = root2;
        lists[2] = root3;
        mergeKLists1(lists);


    }

    /**
     * 优先级队列  小顶堆
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        //使用优先队列
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // lists 头节点全部入队
        for(ListNode list : lists){
            if(list != null){
                queue.offer(list);
            }
        }
        ListNode res = new ListNode();
        ListNode cur = res;
        while(!queue.isEmpty()){
            ListNode node = queue.poll();
            cur.next = node;
            cur = node;
            if(node.next != null){
                queue.offer(node.next);
            }
        }
        return res.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return mergeLists(lists,0,lists.length);
    }

    /**
     * 对 [start,end) 的链表进行合并
     * @param lists
     * @return
     */
    public ListNode mergeLists(ListNode[] lists,int start,int end) {
        // 一个链表不用合并
        if(end - start < 2) return lists[start];
        int mid = start + ((end - start) >> 1);
        ListNode node1 = mergeLists(lists,start,mid);
        ListNode node2 = mergeLists(lists,mid,end);
        return merge(node1,node2);
    }
    /**
     * 对两个列表进行合并
     * @param node1
     * @param node2
     * @return
     */
    public ListNode merge(ListNode node1,ListNode node2){
        //虚拟头节点
        ListNode res = new ListNode();
        ListNode cur = res;
        ListNode p1 = node1,p2 = node2;
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                cur.next = p1;
                p1 = p1.next;
                cur = cur.next;
            }else{
                cur.next = p2;
                p2 = p2.next;
                cur = cur.next;
            }
        }
        if(p1 != null){
            cur.next = p1;
        }
        if(p2 != null){
            cur.next = p2;
        }
        return res.next;
    }
}
