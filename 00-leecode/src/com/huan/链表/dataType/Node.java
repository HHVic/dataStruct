package com.huan.链表.dataType;

public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("v(").append(this.val).append(")").
                append("_").append(this.next == null ? "null" : this.next.val).
                append(this.random == null ? "null" : this.random.val);

        return sb.toString();
    }
}