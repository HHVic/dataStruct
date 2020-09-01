package com.huan.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 并查集
 * @param <T>
 */
public class UnionFind<T> {
    Map<T,Node<T>> all = new HashMap<>();

    public UnionFind(Collection<T> allValue) {
        if(allValue == null) return ;
        allValue.forEach(value -> {
            all.put(value,new Node<>(value));
        });
    }
    public UnionFind(){
        this(null);
    }

    public void make(T t){
        all.put(t,new Node<>(t));
    }

    /**
     * 查找集合的祖父节点
     * @param t
     * @return
     */
    public T find(T t){
        Node<T> tNode = all.get(t);
        if(tNode == null) return null;
        while(tNode != tNode.parent){
            tNode.parent = tNode.parent.parent;
            tNode = tNode.parent;
        }
        return tNode.value;
    }

    private Node<T> findNode(T t){
        return all.get(find(t));
    }

    public void print(){
        all.forEach((v,node) -> {
            System.out.println("【v = " + v + "】  " + node);
        });
    }

    /**
     * 合并两个集合
     * @param t1
     * @param t2
     */
    public void union(T t1,T t2){
        // 树矮的嫁接到树高的上面
        Node<T> t1Node = findNode(t1);
        Node<T> t2Node = findNode(t2);
        if(t1Node == t2Node) return ;
        if(t1Node.rank > t2Node.rank){
            t2Node.parent = t1Node;
        }else if(t1Node.rank < t2Node.rank){
            t1Node.parent = t2Node;
        }else{
            t1Node.parent = t2Node;
            ++t2Node.rank;
        }
    }

    /**
     * 判断两个元素是否在同一集合中
     * @param t1
     * @param t2
     * @return
     */
    public boolean isSame(T t1,T t2){
        return Objects.equals(find(t1),find(t2));
    }

    private static class Node<T>{
        Node<T> parent = this;
        T value;
        int rank = 1;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent.value +
                    ", value=" + value +
                    ", rank=" + rank +
                    '}';
        }
    }
}
