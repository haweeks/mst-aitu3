package ru.aitu.mst;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MSTTests {
    @Test
    public void testSmall() {
        Graph g = new Graph(4);
        g.addEdge(0,1,1.0); g.addEdge(1,2,2.0); g.addEdge(2,3,1.5); g.addEdge(3,0,2.5); g.addEdge(0,2,2.2);
        Prim.Result p = Prim.run(g);
        Kruskal.Result k = Kruskal.run(g);
        assertEquals(p.total, k.total, 1e-6);
        assertEquals(3, p.tree.size());
        assertEquals(3, k.tree.size());
    }
    @Test
    public void testDisconnected() {
        Graph g = new Graph(3);
        g.addEdge(0,1,1.0);
        assertFalse(g.isConnected());
    }
}
