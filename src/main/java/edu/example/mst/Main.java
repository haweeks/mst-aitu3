package edu.example.mst;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length<2) {
            System.out.println("Usage: java -jar mst-project.jar <input-json> <output-dir>");
            return;
        }
        String input = args[0];
        String outdir = args[1];
        Files.createDirectories(Paths.get(outdir));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject root;
        try (Reader r = new FileReader(input)) {
            root = gson.fromJson(r, JsonObject.class);
        }
        JsonArray graphs = root.getAsJsonArray("graphs");
        JsonArray results = new JsonArray();
        StringBuilder csv = new StringBuilder();
        csv.append("id,algorithm name,vertices,total cost,execution time,operation count,edge count\n");
        int id=1;
        for (JsonElement ge: graphs){
            JsonObject gjo = ge.getAsJsonObject();
            int V = gjo.get("vertices").getAsInt();
            Graph g = new Graph(V);
            for (JsonElement ee: gjo.getAsJsonArray("edges")){
                JsonObject eo = ee.getAsJsonObject();
                int u = eo.get("u").getAsInt();
                int v = eo.get("v").getAsInt();
                double w = eo.get("w").getAsDouble();
                g.addEdge(u,v,w);
            }
            MSTResult r1 = Prim.run(g);
            MSTResult r2 = Kruskal.run(g);
            JsonObject o1 = new JsonObject();
            o1.addProperty("id", id);
            o1.addProperty("algorithm", "Prim");
            o1.addProperty("vertices", r1.originalVertices);
            o1.addProperty("totalCost", r1.totalCost);
            o1.addProperty("executionTimeMs", r1.executionTimeMs);
            o1.addProperty("operationCount", r1.operationCount);
            o1.addProperty("edgeCount", r1.mstEdges.size());
            results.add(o1);
            csv.append(String.format("%d,Prim,%d,%.4f,%d,%d,%d\n", id, r1.originalVertices, r1.totalCost, r1.executionTimeMs, r1.operationCount, r1.mstEdges.size()));
            JsonObject o2 = new JsonObject();
            o2.addProperty("id", id);
            o2.addProperty("algorithm", "Kruskal");
            o2.addProperty("vertices", r2.originalVertices);
            o2.addProperty("totalCost", r2.totalCost);
            o2.addProperty("executionTimeMs", r2.executionTimeMs);
            o2.addProperty("operationCount", r2.operationCount);
            o2.addProperty("edgeCount", r2.mstEdges.size());
            results.add(o2);
            csv.append(String.format("%d,Kruskal,%d,%.4f,%d,%d,%d\n", id, r2.originalVertices, r2.totalCost, r2.executionTimeMs, r2.operationCount, r2.mstEdges.size()));
            id++;
        }
        JsonObject out = new JsonObject();
        out.add("results", results);
        try (Writer w = new FileWriter(outdir + "/output.json")) { gson.toJson(out, w); }
        try (Writer w = new FileWriter(outdir + "/summary.csv")) { w.write(csv.toString()); }
        System.out.println("Done. Outputs in " + outdir);
    }
}
