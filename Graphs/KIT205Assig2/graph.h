#include <stdio.h>
#pragma once

typedef struct edge {
	int to_vertex;
	int weight;
} Edge;

typedef struct edgeNode {
	Edge edge;
	struct edgeNode *next;
} *EdgeNodePtr;

typedef struct edgeList {
	EdgeNodePtr head;
} EdgeList;

typedef struct graph {
	int V;
	EdgeList *edges;
} Graph;

void graph_init(Graph **g, int size);
void add_edge(Graph *g, int from, int to, int weight); 
void print_graph(Graph *g, int size) ;
