package cse222_1801042656_hw8;
import java.util.*;

/** A class for calling Dijkstra's algorithm.
 *  @author Slueyman Golbol
 */

public class DijkstrasAlgorithm {

    public static final int ADDITION = 1;
    public static final int MULTIPLICATION = 2;
    public static final int ASTERIKS = 3;

    /** Dijkstras Shortest-Path algorithm.
      @param graph The weighted graph to be searched
      @param start The start vertex
      @param pred Output array to contain the predecessors
                  in the shortest path
      @param dist Output array to contain the distance
                  in the shortest path
    */
  public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist, int operator){
    int numV = graph.getNumV();
    HashSet < Integer > vMinusS = new HashSet < Integer > (numV);
    // Initialize V-S.
    for (int i=0; i<numV; i++){
        if (i != start){
            vMinusS.add(i);
        }
    }
    // Initialize pred and dist.
    for (int v : vMinusS){
      pred[v] = start;
      dist[v] = graph.getEdge(start, v).getWeight();
    }

    // Main loop
    while (vMinusS.size() != 0) {
        // Find the value u in V-S with the smallest dist[u].
        double minDist = Double.POSITIVE_INFINITY;
        int u = -1;
        for (int v : vMinusS) {
            if (dist[v] < minDist) {
                minDist = dist[v];
                u = v;
            }
        }

        // Remove u from vMinusS.
        vMinusS.remove(u);
        // Update the distances.
        for (int v : vMinusS) {
            if (graph.isEdge(u, v)){
                double weight = graph.getEdge(u, v).getWeight();
                if(operator == ADDITION){
                    if (dist[u] + weight < dist[v]){
                        // System.out.println("Using Addition Operation");
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
                else if(operator == MULTIPLICATION){
                    if (dist[u] * weight < dist[v]){
                        //System.out.println("Using Multiplication Operation");
                        dist[v] = dist[u] * weight;
                        pred[v] = u;
                    }
                }
                else if(operator == ASTERIKS){
                    if ( (dist[u] + weight - dist[u]*weight) < dist[v]) {
                        //System.out.println("Using Asteriks Operation");
                        dist[v] = dist[u] + weight - dist[u]*weight;
                        pred[v] = u;
                    }
                }
            }
        }

    }
  }

}
