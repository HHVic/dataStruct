package com.huan.链表;

import com.huan.链表.dataType.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        //思路:创建一个虚拟头节点指向值不等于val的节点
        if(head == null) return head;
        ListNode newHead = new ListNode(0);
        ListNode newTail = newHead;
        while (head != null){
            if(head.val != val){
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        newTail.next = null;
        return newHead.next;
    }
}
