package com.huan.union;

/**
 * Quick Union - 基于rank的优化（树高）
 */
public class UnionFind_QU_R extends UnionFind_QU{

    private int[] heights;
    public UnionFind_QU_R(int capacity) {
        super(capacity);
        heights = new int[parents.length];
        for(int i = 0;i < parents.length;++i){
            heights[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        // 同一集合直接返回
        if(parent1 == parent2) return ;
        // 树矮的嫁接到树高的上面
        if(heights[parent1] > heights[parent2]){
            parents[parent2] = parent1;
        }else if(heights[parent1] < heights[parent2]){
            parents[parent1] = parent2;
        }else{
            parents[parent1] = parent2;
            ++heights[parent2];
        }
    }
}
