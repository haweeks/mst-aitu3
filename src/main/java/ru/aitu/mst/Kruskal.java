package ru.aitu.mst;
import java.util.*;
public class Kruskal {
    static class UF {
        int[] p;
        public UF(int n){ p = new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int a){ return p[a]==a?a:(p[a]=find(p[a])); }
        boolean union(int a,int b){
            int pa=find(a), pb=find(b);
            if (pa==pb) return false;
            p[pb]=pa; return true;
        }
    }
    public static Result run(Graph g) {
        List<Edge> edges = new ArrayList<>(g.edges);
        Collections.sort(edges);
        UF uf = new UF(g.V);
        List<Edge> tree = new ArrayList<>();
        long ops = 0;
        for (Edge e: edges) {
            ops++;
            if (uf.union(e.u, e.v)) {
                tree.add(e);
                if (tree.size()==g.V-1) break;
            }
        }
        double total=0; for (Edge e: tree) total += e.w;
        return new Result(tree, total, ops);
    }
    public static class Result { public List<Edge> tree; public double total; public long ops; public Result(List<Edge> t,double total,long ops){this.tree=t;this.total=total;this.ops=ops;} }
}
