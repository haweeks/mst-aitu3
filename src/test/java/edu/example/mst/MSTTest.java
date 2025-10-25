package edu.example.mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {
    @Test
    public void smallGraphTest(){
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(0,2,3);
        g.addEdge(1,2,1);
        g.addEdge(1,3,4);
        g.addEdge(2,3,2);
        MSTResult p = Prim.run(g);
        MSTResult k = Kruskal.run(g);
        assertEquals(p.mstEdges.size(), g.V-1);
        assertEquals(k.mstEdges.size(), g.V-1);
        assertEquals(p.totalCost, k.totalCost, 1e-6);
    }
}
