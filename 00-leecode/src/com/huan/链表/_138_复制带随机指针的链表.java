package com.huan.链表;

import com.huan.链表.dataType.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */

//尚未AC
public class _138_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        if(head == null) return head;
        Map<Integer,Node> map = new HashMap<>();
        Node newHead = new Node(0);
        Node tail = newHead;
        while(head != null){
            Node node = map.get(head.val);
            if(node != null){
                tail.next = node;
                tail = node;
                if(head.random == null){
                    node.random = null;
                }else{
                    Node random = map.get(head.random.val);
                    if(random != null){
                        node.random = random;
                    }else{
                        node.random = new Node(head.random.val);
                        map.put(node.random.val,node.random);
                    }
                }

            }else{
                node = new Node(head.val);
                if(head.random == null){
                    node.random = null;
                }else{
                    Node random = map.get(head.random.val);
                    if(random != null){
                        node.random = random;
                    }else{
                        node.random = new Node(head.random.val);
                        map.put(node.random.val,node.random);
                    }
                }
                tail.next = node;
                tail = node;
                map.put(head.val,node);
            }
            head = head.next;
        }

        return newHead.next;
    }

    @Test
    public void test(){
        Node randomNode1 = new Node(7);
        Node randomNode2 = new Node(13);
        Node randomNode3 = new Node(11);
        Node randomNode4 = new Node(10);
        Node randomNode5 = new Node(1);
        randomNode1.next = randomNode2;
        randomNode2.next = randomNode3;
        randomNode3.next = randomNode4;
        randomNode4.next = randomNode5;
        randomNode5.next = null;
        randomNode1.random = null;
        randomNode2.random = randomNode1;
        randomNode3.random = randomNode5;
        randomNode4.random = randomNode3;
        randomNode5.random = randomNode1;
        Node node = copyRandomList(randomNode1);
        while(node != null){
            System.out.print(node + "->");
            node = node.next;
        }
    }
}
