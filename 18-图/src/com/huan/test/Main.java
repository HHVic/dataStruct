package com.huan.test;

import com.huan.graph.Graph;
import com.huan.graph.Graph.WeightManager;
import com.huan.graph.ListGraph;
import com.huan.graph.UnionFind;
import com.huan.tools.Asserts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    static WeightManager<Double> weightManager = new WeightManager<Double>() {
        @Override
        public int compare(Double e1, Double e2) {
            return e1.compareTo(e2);
        }

        @Override
        public Double add(Double e1, Double e2) {
            return e1 + e2;
        }
    };

    public static void main(String[] args) {
//        testBFS();
//        testDFS();
//        testTopo();
        testMST();
//        testUnionFind();
    }

    private static void testUnionFind(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < 12;++i){
            list.add(i);
        }
        UnionFind<Integer> uf = new UnionFind<>(list);

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);
        uf.print();

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));
    }

    private static void testMST(){
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> edgeInfos1 = graph.kruskalMST();
        Set<Graph.EdgeInfo<Object, Double>> edgeInfos2 = graph.primMST();
        edgeInfos1.forEach(System.out::println);
        System.out.println("====================");
        edgeInfos2.forEach(System.out::println);
    }

    private static void testBFS(){
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0, new Graph.Visitor<Object>() {
            @Override
            public boolean visitor(Object vertex) {
                System.out.println(vertex);
                return false;
            }
        });
    }

    private static void testDFS(){
        Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
        graph.dfs(1, new Graph.Visitor<Object>() {
            @Override
            public boolean visitor(Object vertex) {
                System.out.println(vertex);
//                if(Objects.equals(vertex,5)) return true;
                return false;
            }
        });
    }

    private static void testTopo(){
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> topoList = graph.topologicalSort();
        topoList.forEach(System.out::println);
    }
    static void test(){
        Graph<String,Integer> graph = new ListGraph<>();
        graph.addEdge("v1","v2",3);
        graph.addEdge("v1","v0",9);
        graph.addEdge("v2","v0",2);
        graph.addEdge("v2","v3",5);
        graph.addEdge("v3","v4",1);
        graph.addEdge("v0","v4",6);
        graph.print();
        graph.removeVertex("v0");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("删除后");
        graph.print();
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
