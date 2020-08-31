package com.huan.union;

/**
 * Quick Union - 基于size的优化
 */
public class UnionFind_QU_S extends UnionFind_QU{
    private int[] sizes;
    public UnionFind_QU_S(int capacity) {
        super(capacity);
        sizes = new int[parents.length];
        for(int i = 0;i < parents.length;++i){
            sizes[i] = 1;
        }
    }

    /**
     * 少的数量嫁接到多的数量
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        // 同一集合直接返回
        if(parent1 == parent2) return ;
        if(sizes[parent1] > sizes[parent2]){
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        }else{
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        }
    }
}
