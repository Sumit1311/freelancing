#include "graph.h"
#include <stdlib.h>
//Graph *g;

void graph_init(Graph **g, int size) {
    int i = 0;
    Graph *temp;
    temp = (Graph *)malloc(sizeof(Graph) * size * size);
    for(i = 0; i < (size * size); i++) {
        temp[i].V = i;
        temp[i].edges = malloc(sizeof(EdgeList));
        temp[i].edges-> head = NULL;
    }
    *g = temp;
}

void add_edge(Graph *g, int from, int to, int weight) {
   /*printf("Adding edge %d - %d : %d\n", from, to, weight);*/
   EdgeNodePtr x =(EdgeNodePtr)malloc(sizeof(struct edgeNode));
   EdgeNodePtr y, prev;
   x->next = NULL;
   x->edge.to_vertex = to;
   x->edge.weight = weight;
   if(g[from].edges->head == NULL) {
	g[from].edges->head = x;
   } else {
	y = prev = g[from].edges->head;
	while(y) {
		prev = y;
		y = y->next;
	}
	prev->next = x;
  }
}

void print_graph(Graph *g, int size) {
	int i = 0;
        EdgeNodePtr x;
	for(i = 0 ; i < size*size; i++) {
		printf("Vertex : %d | ", i);
		x = g[i].edges->head;
		while(x != NULL) {
			printf(" (%d, %d)", x->edge.to_vertex,x->edge.weight);
			x = x->next;
		}		
		printf("\n");
	}
}
