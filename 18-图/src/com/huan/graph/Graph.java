package com.huan.graph;

public interface Graph<V, E> {

    /**
     * 顶点数
     * @return
     */
    int vertexSize();

    /**
     * 边数
     * @return
     */
    int EdgeSize();
    /**
     * 添加顶点
     * @param v
     */
    void addVertex(V v);

    /**
     * 添加边
     * @param from 从哪个点出来
     * @param to 到哪个点
     */
    void addEdge(V from, V to);

    /**
     * 带权重的边
     * @param from
     * @param to
     * @param weight
     */
    void addEdge(V from, V to, E weight);

    /**
     * 删除顶点
     * @param v
     */
    void removeVertex(V v);

    /**
     * 删除边
     * @param from
     * @param to
     */
    void removeEdge(V from,V to);

    /**
     * 打印图
     */
    void print();

}
