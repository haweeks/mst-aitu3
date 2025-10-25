package edu.example.mst;

import java.util.*;
public class Graph {
    public int V;
    public List<Edge> edges = new ArrayList<>();
    public List<List<Edge>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u, v, w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }
}
