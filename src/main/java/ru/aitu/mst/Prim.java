package ru.aitu.mst;
import java.util.*;
public class Prim {
    public static Result run(Graph g) {
        int n = g.V;
        boolean[] used = new boolean[n];
        double[] key = new double[n];
        Edge[] sel = new Edge[n];
        Arrays.fill(key, Double.POSITIVE_INFINITY);
        PriorityQueue<VertexKey> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v.key));
        key[0]=0; pq.add(new VertexKey(0,0));
        long ops = 0;
        List<Edge> tree = new ArrayList<>();
        while(!pq.isEmpty()) {
            VertexKey vk = pq.poll();
            int v = vk.v;
            if (used[v]) continue;
            used[v]=true;
            if (sel[v]!=null) tree.add(sel[v]);
            for (Edge e: g.adj.get(v)) {
                int to = (e.u==v)?e.v:e.u;
                ops++;
                if (!used[to] && e.w < key[to]) {
                    key[to]=e.w;
                    sel[to]=e;
                    pq.add(new VertexKey(to, e.w));
                }
            }
        }
        double total=0; for (Edge e: tree) total += e.w;
        return new Result(tree, total, ops);
    }
    static class VertexKey { int v; double key; VertexKey(int v,double k){this.v=v;this.key=k;} }
    public static class Result { public List<Edge> tree; public double total; public long ops; public Result(List<Edge> t,double total,long ops){this.tree=t;this.total=total;this.ops=ops;} }
}
