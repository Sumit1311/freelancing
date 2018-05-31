#include <stdlib.h>
#include <stdio.h>
#include "graph.h"
#include "dijkstra.h"
#include "floyd-warshall.h"

int** make_dem(int size, int roughness) {
	//int seed = time(NULL);
	int seed = 1527744747;
	srand(seed);
	int** dem = malloc(size * sizeof *dem);
	for (int x = 0; x < size; x++) {
		dem[x] = malloc(size * sizeof *dem[x]);
		for (int y = 0; y < size; y++) {
			dem[x][y] = -1;
		}
	}
	int r = roughness;

	dem[0][0] =				50 - r / 2 + rand() % r;
	dem[size-1][0] =		50 - r / 2 + rand() % r;
	dem[0][size-1] =		50 - r / 2 + rand() % r;
	dem[size-1][size-1] =	50 - r / 2 + rand() % r;

	for (int step = (size - 1); step > 0; step /= 2) {
		r = r > 1 ? r / 2:r;
		if (r < 1) r = 1;
		for (int cx = 0; cx < (size-1) / step; cx++) {
			for (int cy = 0; cy < (size-1) / step; cy++) {
				int a = dem[cx*step][cy*step];
				int b = dem[cx*step+step][cy*step];
				int c = dem[cx*step][cy*step+step];
				int d = dem[cx*step+step][cy*step+step];

				dem[cx*step + step / 2][cy*step + step / 2] = (a + b + c + d) / 4 + rand() % r - r/2;
				
				dem[cx*step + step / 2][cy*step]		= (a + b) / 2 + rand() % r - r/2;
				dem[cx*step][cy*step + step / 2]		= (a + c) / 2 + rand() % r - r/2;
				dem[cx*step + step][cy*step + step / 2] = (b + d) / 2 + rand() % r-r/2;
				dem[cx*step + step/2][cy*step + step]	= (c + d) / 2 + rand() % r-r/2;
			}
		}
	}
	for (int x = 0; x < size; x++) {
		for (int y = 0; y < size; y++) {
			dem[x][y] = dem[x][y]<0	? 0 : dem[x][y];
			dem[x][y] = dem[x][y]>99? 99: dem[x][y];
		}
	}
	return dem;
}

int cost_funcA(int diff) {
	int cost = 1;
	if (diff > 0) cost += diff * diff;
	return cost;
}

int cost_funcB(int diff) {
	int cost = 1;
	if (diff > 0)
		cost += diff * diff;
	else
		cost += diff;
	return cost;
}

void print_2D(int** array2D, int size) {
	for (int x = 0; x < size; x++) {
		for (int y = 0; y < size; y++) {
			if (array2D[x][y] >= 0) {
				printf("%2d ", array2D[x][y]);
			}
			else {
				printf("() ");
			}
		}
		printf("\n");
	}
}

void print_2D_ascii(int** array2D, int size) {
	char *shades = " .-:=+*#%@";
	for (int x = 0; x < size; x++) {
		for (int y = 0; y < size; y++) {
			if (array2D[x][y] >= 0) {
				char shade = shades[array2D[x][y] * 10 / 100];
				printf("%c%c", shade, shade);
			} else {
				printf("()");
			}
		}
		printf("\n");
	}
}

int main() {
        int size = 3, i = 0, j = 0, vertex_from = 0, vertex_to = 0, to_x = 0, to_y = 0;
        int **dem = make_dem(size, 4*size);
        Graph *g = NULL, *g1 = NULL;
        graph_init(&g, size);
        graph_init(&g1, size);
        for(i = 0; i < size; i++){
		for(j = 0; j < size; j++){
			vertex_from = i*size + j;
			//printf("%d : ",vertex_from);
                        if(i == 0) {
				add_edge(g, vertex_from, (i+1)*size + j, cost_funcA(dem[i+1][j]-dem[i][j]));	
				//printf("(%d, %d)",(i+1)*size + j, cost_funcA(dem[i+1][j]-dem[i][j]));
                                if(j != (size - 1)) {
					add_edge(g, vertex_from, (i)*size + (j + 1), cost_funcA(dem[i][j+1]-dem[i][j]));
					add_edge(g, vertex_from, (i+1)*size + (j+1), cost_funcA(dem[i+1][j+1]-dem[i][j]));	
				}
                                if(j != 0) {
					add_edge(g, vertex_from, (i)*size + (j - 1), cost_funcA(dem[i][j-1]-dem[i][j]));
					add_edge(g, vertex_from, (i+1)*size + (j - 1), cost_funcA(dem[i+1][j-1] - dem[i][j]));	
				}
			} else if( i  == (size - 1)) {
				add_edge(g, vertex_from, (i-1)*size + j, cost_funcA(dem[i-1][j] - dem[i][j]));	
                                if(j != (size - 1)) {
					add_edge(g, vertex_from, (i)*size + (j + 1), cost_funcA(dem[i][j+1] - dem[i][j]));
					add_edge(g, vertex_from, (i-1)*size + j, cost_funcA(dem[i-1][j] - dem[i][j]));	
				}
                                if(j != 0) {
					add_edge(g, vertex_from, (i)*size + (j - 1), cost_funcA(dem[i][j-1] - dem[i][j]));
					add_edge(g, vertex_from, (i-1)*size + (j - 1), cost_funcA(dem[i-1][j-1] - dem[i][j]));	
				}

			} else {
				add_edge(g, vertex_from, (i-1)*size + (j), cost_funcA(dem[i-1][j] - dem[i][j]));	
				add_edge(g, vertex_from, (i+1)*size + (j), cost_funcA(dem[i+1][j] - dem[i][j]));
                                if(j != (size - 1)) {
					add_edge(g, vertex_from, (i-1)*size + (j+1), cost_funcA(dem[i-1][j+1] - dem[i][j]));	
					add_edge(g, vertex_from, (i)*size + (j+1), cost_funcA(dem[i][j+1] - dem[i][j]));
					add_edge(g, vertex_from, (i+1)*size + (j+1), cost_funcA(dem[i+1][j+1] - dem[i][j]));
				}
                                if(j != 0) {
					add_edge(g, vertex_from, (i-1)*size + (j-1), cost_funcA(dem[i-1][j-1] - dem[i][j]));	
					add_edge(g, vertex_from, (i)*size + (j-1), cost_funcA(dem[i][j-1] - dem[i][j]));	
					add_edge(g, vertex_from, (i+1)*size + (j-1), cost_funcA(dem[i+1][j-1] - dem[i][j]));
				} 

			}
                        
                        
			// i-1, j-1
			// i-1, j
			// i-1, j+1
			// i, j-1
			// i, j+1
			// i+1, j-1
			// i+1, j
			// i+1, j+1
		}
	}
        //printf("%d\n",g[i].V);
        print_2D(dem, size); 
	print_graph(g, size);
        print_2D(calculate_shortest_pathA(g, dem, size, 0, (size*size)-1),size);
        for(i = 0; i < size; i++){
		for(j = 0; j < size; j++){
			vertex_from = i*size + j;
			//printf("%d : ",vertex_from);
                        if(i == 0) {
				add_edge(g1, vertex_from, (i+1)*size + j, cost_funcB(dem[i+1][j]-dem[i][j]));	
				//printf("(%d, %d)",(i+1)*size + j, cost_funcB(dem[i+1][j]-dem[i][j]));
                                if(j != (size - 1)) {
					add_edge(g1, vertex_from, (i)*size + (j + 1), cost_funcB(dem[i][j+1]-dem[i][j]));
					add_edge(g1, vertex_from, (i+1)*size + (j+1), cost_funcB(dem[i+1][j+1]-dem[i][j]));	
				}
                                if(j != 0) {
					add_edge(g1, vertex_from, (i)*size + (j - 1), cost_funcB(dem[i][j-1]-dem[i][j]));
					add_edge(g1, vertex_from, (i+1)*size + (j - 1), cost_funcB(dem[i+1][j-1] - dem[i][j]));	
				}
			} else if( i  == (size - 1)) {
				add_edge(g1, vertex_from, (i-1)*size + j, cost_funcB(dem[i-1][j] - dem[i][j]));	
                                if(j != (size - 1)) {
					add_edge(g1, vertex_from, (i)*size + (j + 1), cost_funcB(dem[i][j+1] - dem[i][j]));
					add_edge(g1, vertex_from, (i-1)*size + j, cost_funcB(dem[i-1][j] - dem[i][j]));	
				}
                                if(j != 0) {
					add_edge(g1, vertex_from, (i)*size + (j - 1), cost_funcB(dem[i][j-1] - dem[i][j]));
					add_edge(g1, vertex_from, (i-1)*size + (j - 1), cost_funcB(dem[i-1][j-1] - dem[i][j]));	
				}

			} else {
				add_edge(g1, vertex_from, (i-1)*size + (j), cost_funcB(dem[i-1][j] - dem[i][j]));	
				add_edge(g1, vertex_from, (i+1)*size + (j), cost_funcB(dem[i+1][j] - dem[i][j]));
                                if(j != (size - 1)) {
					add_edge(g1, vertex_from, (i-1)*size + (j+1), cost_funcB(dem[i-1][j+1] - dem[i][j]));	
					add_edge(g1, vertex_from, (i)*size + (j+1), cost_funcB(dem[i][j+1] - dem[i][j]));
					add_edge(g1, vertex_from, (i+1)*size + (j+1), cost_funcB(dem[i+1][j+1] - dem[i][j]));
				}
                                if(j != 0) {
					add_edge(g1, vertex_from, (i-1)*size + (j-1), cost_funcB(dem[i-1][j-1] - dem[i][j]));	
					add_edge(g1, vertex_from, (i)*size + (j-1), cost_funcB(dem[i][j-1] - dem[i][j]));	
					add_edge(g1, vertex_from, (i+1)*size + (j-1), cost_funcB(dem[i+1][j-1] - dem[i][j]));
				} 

			}
                        
                        
			// i-1, j-1
			// i-1, j
			// i-1, j+1
			// i, j-1
			// i, j+1
			// i+1, j-1
			// i+1, j
			// i+1, j+1
		}
	}
	print_graph(g, size);
        //print_2D(calculate_shortest_pathB(g, dem, size, 0, (size*size)-1),size);
	printf("\npress enter to exit\n");
	getchar();
	return 0;
}
