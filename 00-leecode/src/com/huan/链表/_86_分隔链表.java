package com.huan.链表;

import com.huan.链表.dataType.ListNode;

/**
 * https://leetcode-cn.com/problems/partition-list/
 */
public class _86_分隔链表 {

    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode lHead = new ListNode(0);
        ListNode lTail = lHead;
        ListNode rHead = new ListNode(0);
        ListNode rTail = rHead;
        while(head != null){
            if(head.val < x){
                lTail.next = head;
                lTail = head;
            }else{
                rTail.next = head;
                rTail = head;
            }
            head = head.next;
        }
        rTail.next = null;
        lTail.next = rHead.next;
        return lHead.next;
    }
}
