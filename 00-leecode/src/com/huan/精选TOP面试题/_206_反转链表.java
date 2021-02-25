package com.huan.精选TOP面试题;

import com.huan.链表.dataType.ListNode;
import org.junit.Test;

import java.util.List;


/**
 * @author:HuanK
 * @create:2021-02-25 16:23
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {
    //非递归(借助额外头节点)
    public ListNode reverseList(ListNode head) {
        ListNode res = new ListNode(0);
        ListNode p = head;
        ListNode q = head;
        while(p != null){
            q = p.next;
            p.next = res.next;
            res.next = p;
            p = q;
        }
        return res.next;
    }

    //非递归(不借助额外头节点)
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = p.next;
        ListNode r;
        p.next = null;
        while(q != null){
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }

    //递归
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Test
    public void test(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;

        reverseList2(node1);
    }
}
