#include "graph.h"
#include <stdlib.h>

int isEmpty(int *Q, int size) {
	int i = 0, f = 0;
	while(i < (size*size)) {
		f |= Q[i];
		if(f == 1) {
			//printf("Q not empty")
			return 0;
		}
		i++;
	}
	return 1;
}

int find_min(int *Q, int *dist, int size) {
	int o = 0,i = 0, min = -1, min_index = 0;
	for(o = 0; o < size*size; o++) {
		if(Q[o]) {
			if(min == -1) {
				min = o;
				continue;
			}
                        if(dist[min] >= dist[o]) {
				min = o;
			}
		} else {
			continue;
		}
	}
	return min;	
}	
int** calculate_shortest_pathA(Graph *g, int **dem, int size, int source, int dest, int *energy) {
	int *Q, i = 0;
	int *dist, *prev, min_dist_ver, alt;
	EdgeNodePtr node;
	int** copy_dem = malloc(size * sizeof *dem);
	for (int x = 0; x < size; x++) {
		copy_dem[x] = malloc(size * sizeof *copy_dem[x]);
		for (int y = 0; y < size; y++) {
			copy_dem[x][y] = dem[x][y];
		}
	}
	Q = (int *)malloc(sizeof(int) * size *size);
	dist = (int *)malloc(sizeof(int) * size *size);
	prev = (int *)malloc(sizeof(int) * size *size);
        for(i = 0; i < (size*size); i++){
		Q[i] = 1;
		dist[i] = 100000;
                prev[i] = -1;		
	}
	dist[source] = 0;
	while(!isEmpty(Q, size)) {
		min_dist_ver = find_min(Q, dist, size);
		Q[min_dist_ver] = 0;
	 	/*if(min_dist_ver == dest){
			break;
		}*/
		//printf("Adding node %d, dist : %d | ", min_dist_ver, dist[min_dist_ver]);
		node = g[min_dist_ver].edges->head;
		while(node){
			alt = dist[min_dist_ver] + node->edge.weight;
			//printf("Distance after Adding node %d is %d | ",node->edge.to_vertex ,alt);
			if(Q[node->edge.to_vertex] && alt <= dist[node->edge.to_vertex]){
				dist[node->edge.to_vertex] = alt;
				prev[node->edge.to_vertex] = min_dist_ver;
			}		
			node = node->next;
		}
		//printf("\n");
	}
	*energy = dist[dest];
	min_dist_ver = dest;
	while(prev[min_dist_ver] != -1) {
		copy_dem[min_dist_ver/size][min_dist_ver%size] = -1;
		min_dist_ver = prev[min_dist_ver];
        
	}
	
	copy_dem[min_dist_ver/size][min_dist_ver%size] = -1;
	return copy_dem;
}

