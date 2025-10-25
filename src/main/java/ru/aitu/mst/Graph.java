package ru.aitu.mst;
import java.util.*;
public class Graph {
    public int V;
    public List<Edge> edges = new ArrayList<>();
    public List<List<Edge>> adj;
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i=0;i<V;i++) adj.add(new ArrayList<>());
    }
    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u,v,w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }
    public boolean isConnected() {
        if (V==0) return true;
        boolean[] vis = new boolean[V];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(0); vis[0]=true; int cnt=1;
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            for (Edge e: adj.get(cur)) {
                int nb = (e.u==cur)?e.v:e.u;
                if (!vis[nb]) { vis[nb]=true; dq.add(nb); cnt++; }
            }
        }
        return cnt==V;
    }
}
