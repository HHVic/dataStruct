package com.huan.链表;

import com.huan.链表.dataType.ListNode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class _234_回文链表 {
    public boolean isPalindrome(ListNode head) {
        //思路:找到中间节点，就是分割后左边最后一个节点,反转中间节点后面的节点
        if(head == null || head.next == null) return true;
        if(head.next.next == null) return head.val == head.next.val;
        //至少三个节点
        ListNode middle = middle(head);
        ListNode newHead = reverse(middle.next);
        while(head != null && newHead != null){
            if(head.val != newHead.val) return false;
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }

    /**
     * 找到中间节点
     * @param head
     * @return
     */
    private ListNode middle(ListNode head){
        ListNode fast = head.next;
        ListNode low = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            low = low.next;
        }
        return low;
    }

    /**
     * 反转链表
     * @param node
     * @return 反转后的头
     */
    private ListNode reverse(ListNode node){
        if(node.next == null) return node;
        ListNode p = node.next;
        node.next = null;
        ListNode r;
        while(p != null){
            r = p.next;
            p.next = node;
            node = p;
            p = r;
        }
        return node;
    }

}
