#include <stdlib.h>
#include "floyd-warshall.h"
int** calculate_shortest_pathB(Graph *g, int **dem, int size, int source, int dest, int *energy) {
	int **dist=malloc(size*size*sizeof(*dist)), **next=malloc(size*size*sizeof(*next));
	int x = 0, y = 0;
	int i = 0, j = 0, k = 0;
        EdgeNodePtr node;
	int** copy_dem = malloc(size * sizeof *dem);
	for (int x = 0; x < size*size; x++) {
		dist[x] = malloc(size * size * sizeof *dist[x]);
		next[x] = malloc(size * size * sizeof *next[x]);
		for (int y = 0; y < size*size; y++) {
			dist[x][y] = 1000;
			next[x][y] = -1;
		}
	}
	
	for (int x = 0; x < size; x++) {
		copy_dem[x] = malloc(size * sizeof *copy_dem[x]);
		for (int y = 0; y < size; y++) {
			copy_dem[x][y] = dem[x][y];
		}
	}
	for(i = 0 ; i < size*size; i++) {
		//printf("Vertex : %d |", i);
		node = g[i].edges->head;
		while(node != NULL) {
			//printf(" (%d, %d )", node->edge.to_vertex);
			dist[i][node->edge.to_vertex] = node->edge.weight;
			next[i][node->edge.to_vertex] = node->edge.to_vertex;
			node = node->next;
		}		
		//printf("\n");
	}

	for(i = 0; i < size*size; i++){
		dist[i][i] = 0;
	}
	//printf("Starting..\n");
	for(k = 0; k < size*size; k++) {
		for(i = 0; i < size*size; i++) {
			for(j = 0; j < size*size; j++) {
				if(dist[i][j] > dist[i][k] + dist[k][j]){
					dist[i][j] = dist[i][k] + dist[k][j];
					next[i][j] = next[i][k];
				}
			}
		}		
	}
	/*for(i = 0; i < size*size; i++){
		for(j=0; j< size*size; j++) {
			printf("%d ", next[i][j]);
		}
		printf("\n");
	}*/
	*energy = dist[source][dest];
        if(next[source][dest] == -1) {
		return copy_dem;
	}
	copy_dem[source/size][source%size] = -1;
	//printf("Start PAth.. %d\n",source);
	while(source != dest) {
		source = next[source][dest];
		//printf("Next Node.. %d\n",source);
		copy_dem[source/size][source%size] = -1;	
	}

	return copy_dem;
}
