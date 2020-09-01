package com.huan.graph;


import sun.reflect.generics.visitor.Visitor;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public Graph() {
    }

    /**
     * 顶点数
     * @return
     */
    public abstract int vertexSize();

    /**
     * 边数
     * @return
     */
    public abstract int EdgeSize();
    /**
     * 添加顶点
     * @param v
     */
    public abstract void addVertex(V v);

    /**
     * 添加边
     * @param from 从哪个点出来
     * @param to 到哪个点
     */
    public abstract void addEdge(V from, V to);

    /**
     * 带权重的边
     * @param from
     * @param to
     * @param weight
     */
    public abstract void addEdge(V from, V to, E weight);

    /**
     * 删除顶点
     * @param v
     */
    public abstract void removeVertex(V v);

    /**
     * 删除边
     * @param from
     * @param to
     */
    public abstract void removeEdge(V from,V to);

    /**
     * 打印图
     */
    public abstract void print();

    /**
     * 广度优先遍历
     * @param start
     * @param visitor
     */
    public abstract void bfs(V start, Visitor<V> visitor);

    /**
     * 深度优先遍历
     * @param start
     * @param visitor
     */
    public abstract void dfs(V start, Visitor<V> visitor);

    /**
     * 有向无环图拓扑排序
     * @return
     */
    public abstract List<V> topologicalSort();

    public Set<EdgeInfo<V,E>> MST(){
        return kruskalMST();
    }

    /**
     * Prim算法求最小生成树
     * @param <V>
     */
    public abstract Set<EdgeInfo<V,E>> primMST();

    /**
     * Kruskal算法求最小生成树
     * @return
     */
    public abstract Set<EdgeInfo<V,E>> kruskalMST();

    public static abstract class Visitor<V>{
        boolean stop;
        public abstract boolean visitor(V v);
    }

    /**
     * 存放边的信息
     * @param <V>
     * @param <E>
     */
    public static class EdgeInfo<V,E>{
        private V from;
        private V to;
        private E weight;

        protected EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }
    }

    public interface WeightManager<E>{
        int compare(E e1,E e2);
        E add(E e1,E e2);
    }

}
