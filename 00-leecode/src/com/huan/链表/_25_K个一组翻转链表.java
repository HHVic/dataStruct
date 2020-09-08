package com.huan.链表;

import com.huan.链表.dataType.ListNode;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class _25_K个一组翻转链表 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        int count = 0;
        ListNode left = head;
        while(head != null){
            if(count == k){
                ListNode newTail = left;
                tail.next = reverse(left,head);
                tail = newTail;
                left = head;
                count = 1;
            }else{
                ++count;
            }
            head = head.next;
        }
        if(count < k){
            tail.next = left;
        }
        if(count == k){
            tail.next = reverse(left,null);
        }
        return newHead.next;
    }

    /**
     * 翻转[left,right)区间的链表
     * @param left
     * @param right
     * @return
     */
    private ListNode reverse(ListNode left,ListNode right){
        ListNode p = left.next;
        left.next = null;
        ListNode r;
        while(p != right){
            r = p.next;
            p.next = left;
            left = p;
            p = r;

        }
        return left;
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

        reverseKGroup(node1,2);
    }

}
