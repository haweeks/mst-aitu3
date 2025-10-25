package edu.example.mst;
import java.util.*;
public class Prim {
    public static MSTResult run(Graph g){
        long start=System.nanoTime();
        Counter counter = new Counter();
        MSTResult res = new MSTResult();
        res.originalVertices = g.V;
        res.originalEdges = g.edges.size();
        boolean[] used = new boolean[g.V];
        double[] key = new double[g.V];
        Edge[] parentEdge = new Edge[g.V];
        Arrays.fill(key, Double.POSITIVE_INFINITY);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a->a[1]));
        key[0]=0;
        pq.add(new int[]{0,0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int v = cur[0];
            if (used[v]) continue;
            used[v]=true;
            if (parentEdge[v]!=null) { res.mstEdges.add(parentEdge[v]); }
            for (Edge e: g.adj.get(v)){
                int to = e.other(v);
                counter.addComparisons(1);
                if (!used[to] && e.w < key[to]){
                    key[to] = e.w;
                    parentEdge[to] = e;
                    pq.add(new int[]{to,(int)Double.doubleToRawLongBits(e.w)});
                }
            }
        }
        double total=0;
        for (Edge e: res.mstEdges) total+=e.w;
        res.totalCost = total;
        res.operationCount = counter.total();
        res.executionTimeMs = (System.nanoTime()-start)/1_000_000;
        return res;
    }
}
