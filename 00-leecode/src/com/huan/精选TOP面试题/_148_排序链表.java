package com.huan.精选TOP面试题;

import com.huan.链表.dataType.ListNode;

/**
 * @author:HuanK
 * @create:2021-02-28 22:19
 * https://leetcode-cn.com/problems/sort-list/
 */
public class _148_排序链表 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode mid = getMiddle(head);
        ListNode right = mid.next;
        mid.next = null;
        ListNode leftList = sortList(head);
        ListNode rightList = sortList(right);
        ListNode res = merge(leftList,rightList);
        return res;
    }

    private ListNode merge(ListNode leftList, ListNode rightList) {
        ListNode res = new ListNode(0);
        ListNode r = res;
        while(leftList != null && rightList != null){
            if(leftList.val <= rightList.val){
                r.next = leftList;
                leftList = leftList.next;
            }else{
                r.next = rightList;
                rightList = rightList.next;
            }
            r = r.next;
        }
        if(leftList != null){
            r.next = leftList;
        }
        if(rightList != null){
            r.next = rightList;
        }
        return res.next;
    }

    //获取链表的中间节点  -1->3->6->2->5->0   中间节点6
    //-1->3->6->2->5  中间节点6
    private ListNode getMiddle(ListNode node){
        if(node == null || node.next == null) return node;
        ListNode low = node;
        ListNode fast = low.next;
        while(fast == null || fast.next == null){
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
}
