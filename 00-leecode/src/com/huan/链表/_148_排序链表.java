package com.huan.链表;

import com.huan.链表.dataType.ListNode;
import org.junit.Test;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/sort-list/
 */
public class _148_排序链表 {

    //归并排序思想O(NlogN) 空间O(NlogN)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        return sortList(head, p);
    }

    private ListNode sortList(ListNode node1, ListNode node2) {
        if (node1 == node2) return node1;
        if (node1.next == node2) {
            if (node1.val < node2.val) return node1;
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
            return node1;
        }
        //找到node1 node2的中间节点
        ListNode fast = node1, low = node1;
        while (fast != node2 && fast.next != node2) {
            fast = fast.next.next;
            low = low.next;
        }
        //保存中间节点
        ListNode oldMid = low.next;
        low.next = null;
        ListNode left = sortList(node1, low);
        ListNode right = sortList(oldMid, node2);
        return merge(left, right);
    }

    /**
     * 合并两个有序链表
     *
     * @param node1
     * @param node2
     */
    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode node = new ListNode(0);
        ListNode tail = node;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                tail.next = node1;
                tail = node1;
                node1 = node1.next;
            } else {
                tail.next = node2;
                tail = node2;
                node2 = node2.next;
            }
        }
        if (node1 != null) tail.next = node1;
        if (node2 != null) tail.next = node2;
        return node.next;
    }

    @Test
    public void test() {
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(3);
        ListNode sortList = sortList(node);
        while (sortList != null) {
            System.out.print(sortList.val + ",");
            sortList = sortList.next;
        }
    }
}
