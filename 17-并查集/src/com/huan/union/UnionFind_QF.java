package com.huan.union;

/**
 * Quick Find
 */
public class UnionFind_QF extends UnionFind {
    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int value) {
        return parents[value];
    }

    /**
     * 将v1集合 的所有元素嫁接到v2的父节点
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        // 同一集合直接返回
        if(parent1 == parent2) return ;
        // 找到v1集合的所有元素
        for(int i = 0;i < parents.length;++i){
            if(parents[i] == parent1){
                parents[i] = parent2;
            }
        }
    }
}
