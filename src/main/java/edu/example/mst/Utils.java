package edu.example.mst;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Utils {
    public static Graph fromJsonFile(String path) throws IOException {
        Gson gson = new Gson();
        try (Reader r = new FileReader(path)) {
            JsonObject root = gson.fromJson(r, JsonObject.class);
            JsonArray verts = root.getAsJsonArray("graphs");
            // If file contains multiple graphs, return the first one (main runner uses whole file differently)
            JsonObject g = verts.get(0).getAsJsonObject();
            int V = g.get("vertices").getAsInt();
            Graph graph = new Graph(V);
            JsonArray edges = g.getAsJsonArray("edges");
            for (JsonElement ee: edges){
                JsonObject eo = ee.getAsJsonObject();
                int u = eo.get("u").getAsInt();
                int v = eo.get("v").getAsInt();
                double w = eo.get("w").getAsDouble();
                graph.addEdge(u,v,w);
            }
            return graph;
        }
    }
}
