package com.huan.union;

/**
 *
 */
public class UnionFind_QU_R_PC extends UnionFind_QU_R {
    public UnionFind_QU_R_PC(int capacity) {
        super(capacity);
    }

    /**
     * 查找时 v路径的所有父节点指向v的祖先节点
     * @param value
     * @return
     */
    @Override
    public int find(int value) {
        while(value != parents[value]){
            value = find(parents[value]);
        }
        return parents[value];
    }
}
