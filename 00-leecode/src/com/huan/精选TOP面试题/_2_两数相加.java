package com.huan.精选TOP面试题;

import com.huan.链表.dataType.ListNode;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class _2_两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;

            }
            sum += carry;
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum - 10 : sum;
            tail.next = new ListNode(sum);
            tail = tail.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

        }
        if(carry == 1){
            tail.next = new ListNode(1);
        }
        return newHead.next;
    }
}
