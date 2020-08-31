package com.huan.union;

public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if(capacity < 1){
            throw new IllegalArgumentException("capacity must >= 1");
        }
        parents = new int[capacity];
        for(int i = 0;i < parents.length;++i){
            parents[i] = i;
        }
    }

    /**
     * 找到value所对应的祖先
     * @param value
     * @return
     */
    public abstract int find(int value);

    public abstract void union(int v1,int v2);

    public boolean isSame(int v1,int v2){
        return find(v1) == find(v2);
    }

    protected void indexCheck(int index){
        if(index < 0 || index >= parents.length){
            throw new IndexOutOfBoundsException("index out of bunds");
        }
    }
}
