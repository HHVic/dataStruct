package com.huan.union;

/**
 * Quick Union
 */
public class UnionFind_QU extends UnionFind{

    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int value) {
        while(parents[value] != value){
            value = parents[value];
        }
        return value;
    }

    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        // 同一集合直接返回
        if(parent1 == parent2) return ;
        parents[parent1] = parent2;
    }
}
