package com.huan.graph;


import sun.reflect.generics.visitor.Visitor;

import java.util.List;

@SuppressWarnings("ALL")
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

    /**
     * 广度优先遍历
     * @param start
     * @param visitor
     */
    void bfs(V start, Visitor<V> visitor);

    /**
     * 深度优先遍历
     * @param start
     * @param visitor
     */
    void dfs(V start,Visitor<V> visitor);

    /**
     * 有向无环图拓扑排序
     * @return
     */
    List<V> topologicalSort();

    abstract class Visitor<V>{
        boolean stop;
        public abstract boolean visitor(V v);
    }

    class EdgeInfo<V,E>{
        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
