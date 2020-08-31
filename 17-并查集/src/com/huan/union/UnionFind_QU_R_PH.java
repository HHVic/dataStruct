package com.huan.union;

public class UnionFind_QU_R_PH extends UnionFind_QU_R {
    public UnionFind_QU_R_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int value) {
        while(value != parents[value]){
            parents[value] = parents[parents[value]];
            value = parents[parents[value]];
        }
        return parents[value];
    }
}
