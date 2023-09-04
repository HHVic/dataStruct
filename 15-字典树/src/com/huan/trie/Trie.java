package com.huan.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie<E> {
    private int size;
    private Node<E> root;
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
        root = null;
    }

    public boolean contains(String str){
        Node<E> node = node(str);
        return node != null && node.word;
    }

    public E add(String str,E element){
        keyNotNullCheck(str);
        if(root == null){
            root = new Node<>(null);
        }
        Node<E> node = root;
        for (int i = 0; i < str.length(); i++) {
            // children是空
            char c = str.charAt(i);
            // children本来就是空
            boolean emptyChildren = node.children == null;
            // 通过c拿到的children是空
            Node<E> childNode = emptyChildren ? null : node.children.get(c);
            if(childNode == null){
                // 创建新节点
                childNode = new Node<>(node);
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c,childNode);
                childNode.s = c;
            }
            node = childNode;
        }
        if(!node.word){
            // 添加单词
            node.word = true;
            node.value = element;
            ++size;
            return null;
        }
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    public E get(String str){
        Node<E> node = node(str);
        return node != null && node.word ? node.value : null;
    }

    public E remove(String str){
        keyNotNullCheck(str);
        Node<E> node = node(str);
        if(node == null) return null;
        --size;
        E oldValue = node.value;
        if(node.children != null && !node.children.isEmpty()){
            // 不能删除，word设为false即可
            node.word = false;
            node.value = null;
            return oldValue;
        }
        Node<E> parent = null;
        while((parent = node.parent) != null) {
            parent.children.remove(node.s);
            if (parent.word || (parent.children != null && !parent.children.isEmpty())) break;
            node = parent;
        }
        return oldValue;
    }

    public boolean startsWith(String prefix){
        Node<E> node = node(prefix);
        return node != null;
    }

    /**
     * 通过str获取节点
     * @param str
     * @return
     */
    private Node<E> node(String str){
        keyNotNullCheck(str);
        if(root == null) return null;
        Node<E> node = root;
        for (int i = 0; i < str.length(); i++) {
            if(node == null || node.children == null || node.children.isEmpty()) return null;
            char c = str.charAt(i);
            node = node.children.get(c);
        }
        return node;
    }

    private void keyNotNullCheck(String str){
        if(str == null || str.length() == 0){
            throw new IllegalArgumentException("key not be null");
        }
    }

    private static class Node<E>{
        Map<Character,Node<E>> children;
        boolean word;
        Character s;
        Node<E> parent;
        E value;

        public Node(Node<E> parent) {
            this.parent = parent;
        }
    }
}
