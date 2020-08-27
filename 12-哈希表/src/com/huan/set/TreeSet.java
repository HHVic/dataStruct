package com.huan.set;

import com.huan.map.Map;
import com.huan.map.TreeMap;

import java.util.Comparator;

/**
 * 使用TreeMap实现TreeSet
 * @param <E>
 */
public class TreeSet<E> implements Set<E> {
    TreeMap<E,Object> treeMap;
    //比较器
    private Comparator<E> comparator;

    private TreeSet(Comparator<E> comparator,TreeMap<E,Object> treeMap) {
        this.comparator = comparator;
        this.treeMap = treeMap;
    }

    public TreeSet(Comparator<E> comparator){
        this(comparator,new TreeMap<>());
    }

    public TreeSet() {
        this(new TreeMap<>());
    }

    private TreeSet(TreeMap<E,Object> treeMap){
        this(null,treeMap);
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return treeMap.isEmpty();
    }

    @Override
    public void clear() {
        treeMap.clear();
    }

    @Override
    public boolean contains(E element) {
        return treeMap.containsKey(element);
    }

    @Override
    public void add(E element) {
        treeMap.put(element,null);
    }

    @Override
    public void remove(E element) {
        treeMap.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        treeMap.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
