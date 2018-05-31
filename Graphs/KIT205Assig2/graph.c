#include "graph.h"
Graph *g;

void initGraph(int size) {
    g = (Graph *)malloc(sizeof(Graph) * size * size);
    for(int i = 0; i < size; i++) {
        g[i].V = i;
        g[i].edges = malloc(sizeof(EdgeList));
        g[i].edges-> head = NULL;
    }
}

void addEdge(int from, int to, int weight) {
   g[i] 
}
