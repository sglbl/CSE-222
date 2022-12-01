package src.book_implementation;

import java.io.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class CalculateLocation {
    public static double  calculate(String currentLocation,String endLocation) {
        int start,end;
        start=locate(currentLocation);
        end=locate(endLocation);


        int numV = 0; // The number of vertices.
        Graph graph = null;
        //Load the graph from a file.
        try {
            File myObj = new File("./src/book_implementation/graph.txt"); /* ./src/book_implementation/ */
            Scanner scan = new Scanner(myObj);
            graph = AbstractGraph.createGraph(scan, false, "List");
            numV = graph.getNumV();
        } catch (IOException ex) {
            System.err.println("IO Error while reading graph");
            System.err.println(ex.toString());
            System.exit(1);
        }
        //arrays for predecessors and distances
        int[] pred = new int[numV];
        double[] dist = new double[numV];

        //Execute Dijkstra's algorithm on the graph
        dijkstrasAlgorithm(graph, start, pred, dist);
        return dist[end];


    }

    public static int locate(String location){
        if(location.contains("Kartal"))
            return 0;
        else if(location.contains("Pendik"))
            return 1;
        else if(location.contains("Tuzla"))
            return 2;
        else if(location.contains("Kadıköy"))
            return 3;
        else if(location.contains("Maltepe"))
            return 4;
        else if(location.contains("Beykoz"))
            return 5;
        else if(location.contains("Beşiktaş"))
            return 6;
        else
        {
            return -1;

        }
    }

    @SuppressWarnings("deprecation")
    public static void dijkstrasAlgorithm(Graph graph,
                                          int start,
                                          int[] pred,
                                          double[] dist){
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV);
        //Initialize V - S
        for(int i = 0; i < numV; i++){
            if(i != start)
                vMinusS.add(i);
        }
        // Initialize pred and dist
        for(int v : vMinusS){
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        //Main loop
        while(vMinusS.size() != 0){
            //Find the value u in V - S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for(int v : vMinusS){
                if(dist[v] < minDist){
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS
            vMinusS.remove(u);
            //Update the distances
            Iterator<Edge> edgeIter = graph.edgeIterator(u);
            while(edgeIter.hasNext()){
                Edge edge = edgeIter.next();
                int v = edge.getDest();
                if(vMinusS.contains(new Integer(v))){
                    double weight = edge.getWeight();
                    if(dist[u] + weight < dist[v]){
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}
