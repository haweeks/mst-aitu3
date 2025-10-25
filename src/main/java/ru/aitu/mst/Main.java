package ru.aitu.mst;
import com.google.gson.*;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String input = args.length>0?args[0] : "data/assign_3_input.json";
        String out = args.length>1?args[1] : "data/output.json";
        String csv = args.length>2?args[2] : "data/summary.csv";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader r = new FileReader(input);
        GraphDescriptor[] descs = gson.fromJson(r, GraphDescriptor[].class);
        List<Map<String,Object>> results = new ArrayList<>();
        List<String> csvLines = new ArrayList<>();
        csvLines.add("graph,algorithm,vertices,edges,total,ops");
        for (GraphDescriptor d: descs) {
            Graph g = d.toGraph();
            Map<String,Object> item = new LinkedHashMap<>();
            item.put("name", d.name);
            item.put("vertices", g.V);
            item.put("edges", g.edges.size());
            if (!g.isConnected()) {
                item.put("error", "disconnected");
                results.add(item);
                continue;
            }
            Prim.Result p = Prim.run(g);
            Kruskal.Result k = Kruskal.run(g);
            item.put("prim_total", p.total);
            item.put("prim_ops", p.ops);
            item.put("prim_edges", p.tree.toString());
            item.put("kruskal_total", k.total);
            item.put("kruskal_ops", k.ops);
            item.put("kruskal_edges", k.tree.toString());
            results.add(item);
            csvLines.add(String.join(",", d.name, "Prim", String.valueOf(g.V), String.valueOf(g.edges.size()), String.format("%.4f", p.total), String.valueOf(p.ops)));
            csvLines.add(String.join(",", d.name, "Kruskal", String.valueOf(g.V), String.valueOf(g.edges.size()), String.format("%.4f", k.total), String.valueOf(k.ops)));
        }
        Writer w = new FileWriter(out);
        gson.toJson(results, w);
        w.close();
        try (PrintWriter pw = new PrintWriter(new FileWriter(csv))) {
            for (String s: csvLines) pw.println(s);
        }
        System.out.println("Wrote " + out + " and " + csv);
    }
    static class GraphDescriptor { String name; int vertices; List<SimpleEdge> edges; Graph toGraph(){ Graph g = new Graph(vertices); if (edges!=null) for (SimpleEdge e: edges) g.addEdge(e.u,e.v,e.w); return g; } }
    static class SimpleEdge { int u,v; double w; }
}
