#include "floyd-warshall.h"
int** calculate_shortest_pathB(Graph *g, int **dem, int size, int source, int dest) {
	int **dist=malloc(size*size);
	int x = 0, y = 0;
	int i = 0, j = 0, k = 0;
        EdgeNodePtr node;
	for (int x = 0; x < size*size; x++) {
		dist[x] = malloc(size * size);
		for (int y = 0; y < size*size; y++) {
			dist[x][y] = 100000;
		}
	}
	
	for(i = 0 ; i < size*size; i++) {
		//printf("Vertex : %d | ", i);
		node = g[i].edges->head;
		while(node != NULL) {
			//printf(" %d", node->edge.to_vertex);
			node = node->next;
			dist[i][node->edge.to_vertex] = node->edge.weight;
		}		
		//printf("\n");
	}

	for(i = 0; i < size*size; i++){
		dist[i][i] = 0;
	}

	
}
