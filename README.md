# MST Assignment — Prim and Kruskal

**Author:** Zarina
**Group:** SE-2429

Assignment 3: Optimization of a City Transportation Network (MST)
Purpose of the work
Implement and compare two algorithms for finding a minimum spanning tree (Prim and Kruskal) for optimizing a city's transportation network.

Input data
Input: a JSON file input.json containing several graphs:
5 small (4-6 vertices),
10 medium (10-15 vertices),
10 large (20-30 vertices and more).
3 Extra large ()
Each graph: a list of vertices and edges with weights (the cost of building a road).

Implementation
Language: Java 17.
Build: Maven (or mvnw). 
Algorithms:
Prim - via PriorityQueue (simple tutorial version, without decrease-key).
Kruskal — through edge sorting and Union-Find.

Results
The output is saved in:
data/output.json — detailed results for each graph;
`summary.csv' — a table for comparing the time and number of operations.
