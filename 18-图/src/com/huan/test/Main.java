package com.huan.test;

import com.huan.graph.Graph;
import com.huan.graph.ListGraph;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
//        testBFS();
        testDFS();
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
        Graph<Object, Double> graph = new ListGraph<>();
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
        Graph<Object, Double> graph = new ListGraph<>();
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
