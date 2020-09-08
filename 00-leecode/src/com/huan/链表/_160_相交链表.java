package com.huan.链表;

import com.huan.链表.dataType.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class _160_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //独特思路: 两个指针指向headA，headB，当一个的next为空时，指向另外一个
        if(headA == null || headB == null) return null;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1 != p2){
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
