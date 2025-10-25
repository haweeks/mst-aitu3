package ru.aitu.mst;
public class Edge implements Comparable<Edge> {
    public int u, v;
    public double w;
    public Edge(int u, int v, double w) { this.u = u; this.v = v; this.w = w; }
    @Override
    public int compareTo(Edge o) { return Double.compare(this.w, o.w); }
    @Override
    public String toString() { return String.format("%d-%d(%.2f)", u, v, w); }
}
