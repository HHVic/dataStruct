package com.huan.map;

import java.util.Objects;

public class LinkedHashMap<K,V> extends HashMap<K,V> {
    private LinkedNode<K,V> first;
    private LinkedNode<K,V> last;

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public boolean containsValue(V value) {
        if(first == null) return false;
        LinkedNode<K,V> node = first;
        while(node != null){
            if (Objects.equals(node.value,value)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if(first == null) return;
        LinkedNode<K,V> node = first;
        while(node != null){
            if (visitor.visit(node.key,node.value)){
                return ;
            }
            node = node.next;
        }
    }

    /**
     * @param willNode 想要删除的节点
     * @param removeNode 真正要删除的节点
     */
    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {
        LinkedNode<K,V> willLinkedNode = (LinkedNode<K, V>) willNode;
        LinkedNode<K,V> removeLinkedNode = (LinkedNode<K, V>) removeNode;
        if(willLinkedNode != removeLinkedNode){
            // 交换prev
            LinkedNode<K,V> temp = willLinkedNode.prev;
            willLinkedNode.prev = removeLinkedNode.prev;
            removeLinkedNode.prev = temp;
            if(willLinkedNode.prev == null){
                first = willLinkedNode;
            }else{
                willLinkedNode.prev.next = willLinkedNode;
            }
            if(removeLinkedNode.prev == null){
                first = removeLinkedNode;
            }else {
                removeLinkedNode.prev.next = removeLinkedNode;
            }
            //交换next
            temp = willLinkedNode.next;
            willLinkedNode.next = removeLinkedNode.next;
            removeLinkedNode.next = temp;
            if(willLinkedNode.next == null){
                last = willLinkedNode;
            }else{
                willLinkedNode.next.prev = willLinkedNode;
            }
            if(removeLinkedNode.next == null){
                last = removeLinkedNode;
            }else {
                removeLinkedNode.next.prev = removeLinkedNode;
            }
        }

        //删除willNode
        LinkedNode<K,V> prev = removeLinkedNode.prev;
        LinkedNode<K,V> next = removeLinkedNode.next;
        if(first == removeLinkedNode){
            first = next;
        }else{
            prev.next = next;
        }
        if(last == removeLinkedNode){
            last = prev;
        }else{
            next.prev = prev;
        }
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<K,V>(key, value, parent);
        if(first == null){
            first = last = node;
        }else{
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    protected static class LinkedNode<K,V> extends Node<K,V> {

        LinkedNode<K,V> prev;
        LinkedNode<K,V> next;
        private LinkedNode(K key, V value, Node<K, V> parent) {
            super(key,value,parent);
        }
    }
}
