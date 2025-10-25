package edu.example.mst;
import java.util.*;
public class Kruskal {
    static class DSU {
        int[] p; int[] r;
        Counter counter;
        public DSU(int n, Counter c){ p = new int[n]; r = new int[n]; counter = c; for (int i=0;i<n;i++) p[i]=i; }
        public int find(int x){
            if (p[x]==x) return x;
            p[x]=find(p[x]);
            return p[x];
        }
        public boolean union(int a, int b){
            int pa=find(a), pb=find(b);
            counter.addComparisons(1);
            if (pa==pb) return false;
            counter.unions++;
            if (r[pa]<r[pb]) p[pa]=pb;
            else if (r[pb]<r[pa]) p[pb]=pa;
            else { p[pb]=pa; r[pa]++; }
            return true;
        }
    }

    public static MSTResult run(Graph g){
        long start = System.nanoTime();
        Counter counter = new Counter();
        MSTResult res = new MSTResult();
        res.originalVertices = g.V;
        res.originalEdges = g.edges.size();
        List<Edge> edges = new ArrayList<>(g.edges);
        Collections.sort(edges);
        counter.addComparisons(edges.size()); // rough count for sort comparisons
        DSU dsu = new DSU(g.V,counter);
        double total = 0;
        for (Edge e: edges){
            if (dsu.union(e.u,e.v)){
                res.mstEdges.add(e);
                total += e.w;
                if (res.mstEdges.size()==g.V-1) break;
            }
        }
        res.totalCost = total;
        res.operationCount = counter.total();
        res.executionTimeMs = (System.nanoTime()-start)/1_000_000;
        return res;
    }
}
