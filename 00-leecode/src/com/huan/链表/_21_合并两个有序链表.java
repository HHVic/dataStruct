package com.huan.链表;

import com.huan.链表.dataType.ListNode;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _21_合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //虚拟头节点
        ListNode res = new ListNode();
        ListNode cur = res;
        ListNode p1 = l1,p2 = l2;
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
