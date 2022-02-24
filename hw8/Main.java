import java.util.*;
import cse222_1801042656_hw8.*;
import cse222_1801042656_hw8.ListGraph;
/**
 * @author Suleyman Golbol 1801042656
 * Driver class for project
 */
 

public class Main{

    public static void main(String[] args){
        System.out.println("---- PART1 ----");
        part1();
        System.out.println("---- PART2 ----");
        part2();
        //part2_try();
        System.out.println("---- PART3 ----");
        part3();
    }

    @SuppressWarnings("unused")
    /** Driver code for part1 */
    public static void part1(){

        System.out.println( "\n---CREATING EDGES---" );
        Edge edge  = new Edge(0, 1, 10, Edge.TIME_WEIGHT);
        Edge edge2 = new Edge(0, 4, 100, Edge.TIME_WEIGHT);
        Edge edge3 = new Edge(0, 3, 30, Edge.TIME_WEIGHT);
        Edge edge4 = new Edge(2, 4, 10, Edge.TIME_WEIGHT);
        Edge edge5 = new Edge(3, 2, 20, Edge.TIME_WEIGHT);
        Edge edge6 = new Edge(3, 4, 60, Edge.TIME_WEIGHT);
        Edge edge7 = new Edge(1, 2, 50, Edge.TIME_WEIGHT);
        System.out.println("---Catching exception that throwed when creating edge---"); int NEW_WEIGHT = 4;
        try{
            Edge edge8 = new Edge(1, 3, 56, NEW_WEIGHT);
        }catch(IllegalStateException exception){
            System.out.println("Exception catched.");
        }

        System.out.println( "\n---LIST GRAPH---" );
        System.out.println( "---Creating list graph---" );
        ListGraph listGraph = new ListGraph(5, true);

        System.out.println( "---Inserting edges to list graph---" );
        listGraph.insert(edge);
        listGraph.insert(edge2);
        listGraph.insert(edge3);
        listGraph.insert(edge4);
        listGraph.insert(edge5);
        listGraph.insert(edge6);
        listGraph.insert(edge7);

        System.out.println( "\n---Creating pred and dist arrays.---" );
        int pred[] = new int[5];
        double dist[] = new double[5];
        for(int i=0; i<pred.length; i++) pred[i] = 0;
        for(int i=0; i<dist.length; i++) dist[i] = listGraph.getEdge(0, i).getWeight();

        System.out.println( "---LIST GRAPH Dijkstra's Algorithm TESTING---" );
        System.out.println( "---Using Dijkstra's Algorithm for Addition---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(listGraph, 0, pred, dist, DijkstrasAlgorithm.ADDITION);
        System.out.println( Arrays.toString(pred) );
        System.out.println( "---Using Dijkstra's Algorithm for Multiplication---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(listGraph, 0, pred, dist, DijkstrasAlgorithm.MULTIPLICATION);
        System.out.println( Arrays.toString(pred) );
        System.out.println( "---Using Dijkstra's Algorithm for Asteriks(*)---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(listGraph, 0, pred, dist, DijkstrasAlgorithm.ASTERIKS);
        System.out.println( Arrays.toString(pred) );

        System.out.println( "\n---MATRIX GRAPH---" );
        System.out.println( "---Creating matrix graph---" );
        MatrixGraph matrixGraph = new MatrixGraph(5, false);

        System.out.println("---Inserting edges to matrix graph---");
        matrixGraph.insert(edge);
        matrixGraph.insert(edge2);
        matrixGraph.insert(edge3);
        matrixGraph.insert(edge4);
        matrixGraph.insert(edge5);
        matrixGraph.insert(edge6);
        matrixGraph.insert(edge7);

        System.out.println( "---Creating pred and dist arrays.---" );
        int pred2[] = new int[5];
        double dist2[] = new double[5];
        for(int i=0; i<pred2.length; i++) pred2[i] = 0;
        for(int i=0; i<dist2.length; i++) dist2[i] = matrixGraph.getEdge(0, i).getWeight();
        
        System.out.println( "---MATRIX GRAPH Dijkstra's Algorithm TESTING---" );
        System.out.println( "---Using Dijkstra's Algorithm for Addition---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(matrixGraph, 0, pred2, dist2, DijkstrasAlgorithm.ADDITION);
        System.out.println( Arrays.toString(pred2) );
        System.out.println( "---Using Dijkstra's Algorithm for Multiplication---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(matrixGraph, 0, pred2, dist2, DijkstrasAlgorithm.MULTIPLICATION);
        System.out.println( Arrays.toString(pred2) );
        System.out.println( "---Using Dijkstra's Algorithm for Asteriks(*)---" );
        DijkstrasAlgorithm.dijkstrasAlgorithm(matrixGraph, 0, pred2, dist2, DijkstrasAlgorithm.ASTERIKS);
        System.out.println( Arrays.toString(pred2) );

    }

    /** Just a basic trying method for part2 */
    public static void part2_try(){

        System.out.println( "\n---CREATING EDGES---" );
        Edge edge  = new Edge(0, 1, 10, Edge.TIME_WEIGHT);
        Edge edge2 = new Edge(0, 4, 100, Edge.TIME_WEIGHT);
        Edge edge3 = new Edge(3, 2, 20, Edge.TIME_WEIGHT);
        Edge edge4 = new Edge(3, 4, 20, Edge.TIME_WEIGHT);

        System.out.println( "\n---LIST GRAPH---" );
        System.out.println( "---Creating list graph---" );
        ListGraph listGraph = new ListGraph(5, false);

        System.out.println( "---Inserting edges to list graph---" );
        listGraph.insert(edge);
        listGraph.insert(edge2);
        listGraph.insert(edge3);
        listGraph.insert(edge4);
        System.out.println("BREADTH FIRST");
        int num1 = BreadthFirstSearch.findNumOfConnectedComponents(listGraph);
        System.out.println( " num1 is "  +  num1 );

        System.out.println( "\n---MATRIX GRAPH---" );
        System.out.println( "---Creating matrix graph---" );
        MatrixGraph matrixGraph = new MatrixGraph(5, false);
        System.out.println("---Inserting edges to matrix graph---");
        matrixGraph.insert(edge);
        matrixGraph.insert(edge2);
        matrixGraph.insert(edge3);
        matrixGraph.insert(edge4);
        
        int num2 = BreadthFirstSearch.findNumOfConnectedComponents(matrixGraph);
        System.out.println( " num2 is "  +  num2 );

        System.out.println("DEPTH FIRST");
        DepthFirstSearch listGraph_dfs = new DepthFirstSearch(listGraph);
        System.out.println("lg " + listGraph_dfs.findNumOfConnectedComponents() );

        DepthFirstSearch matrixGraph_dfs = new DepthFirstSearch(matrixGraph);
        System.out.println("mg " + matrixGraph_dfs.findNumOfConnectedComponents() );


    }

    /** Part2 driver code */
    public static void part2(){
        Random random = new Random();
        long nano_start = System.currentTimeMillis();
        long nano_end = System.currentTimeMillis();

        //Create average values for all. It will be printed at last.
        float runningTime_1000_avg_dfs  = 0;    float runningTime_1000_avg_bfs  = 0;
        float runningTime_2000_avg_dfs  = 0;    float runningTime_2000_avg_bfs  = 0;
        float runningTime_5000_avg_dfs  = 0;    float runningTime_5000_avg_bfs  = 0;
        float runningTime_10000_avg_dfs = 0;    float runningTime_10000_avg_bfs = 0;

        for(int count=0; count<10; count++){
            System.out.println("\n" + (count+1) + ". Time");

            int MAX_VERTICES = 1000;
            ListGraph graph1_list = new ListGraph(MAX_VERTICES, false);
            MatrixGraph graph1_matrix = new MatrixGraph(MAX_VERTICES, false);

            int size = random.nextInt( MAX_VERTICES );
            for(int i=3; i < size; i++){
                int u = random.nextInt(i+1);
                int v = i-3;
                graph1_list.insert( new Edge(u, v, i+5, Edge.QUALITY_WEIGHT) );
                graph1_matrix.insert( new Edge(u, v, i+5, Edge.QUALITY_WEIGHT) );
            }
            nano_start = System.currentTimeMillis();
            System.out.println("For 1000 elements + List   Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph1_list));
            System.out.println("For 1000 elements + Matrix Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph1_matrix));
            nano_end = System.currentTimeMillis();
            runningTime_1000_avg_dfs += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            System.out.println("For 1000 elements + List   Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph1_list));
            System.out.println("For 1000 elements + Matrix Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph1_matrix) + "\n");
            nano_end = System.currentTimeMillis();
            runningTime_1000_avg_bfs += (nano_end-nano_start);

            MAX_VERTICES = 2000;
            ListGraph graph2_list = new ListGraph(MAX_VERTICES, false);
            MatrixGraph graph2_matrix = new MatrixGraph(MAX_VERTICES, false);
            
            size = random.nextInt( MAX_VERTICES );
            for(int i=1; i < size; i++){
                int u = random.nextInt(i+1);
                int v = i+3;
                graph2_list.insert( new Edge(u, v, i+5, Edge.TIME_WEIGHT) );
                graph2_matrix.insert( new Edge(u, v, i+5, Edge.TIME_WEIGHT) );
            }
            nano_start = System.currentTimeMillis();
            System.out.println("For 2000 elements + List   Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph2_list));
            System.out.println("For 2000 elements + Matrix Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph2_matrix));
            nano_end = System.currentTimeMillis();
            runningTime_2000_avg_dfs += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            System.out.println("For 2000 elements + List   Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph2_list));
            System.out.println("For 2000 elements + Matrix Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph2_matrix) + "\n");
            nano_end = System.currentTimeMillis();
            runningTime_2000_avg_bfs += (nano_end-nano_start);

            MAX_VERTICES = 5000;
            ListGraph graph3_list = new ListGraph(MAX_VERTICES, false);
            MatrixGraph graph3_matrix = new MatrixGraph(MAX_VERTICES, false);

            size = random.nextInt( MAX_VERTICES );
            for(int i=0; i < size; i++){
                int u = random.nextInt(i+1);
                int v = i + 3;
                graph3_list.insert( new Edge(u, v, i+5, Edge.QUALITY_WEIGHT) );
                graph3_matrix.insert( new Edge(u, v, i+5, Edge.QUALITY_WEIGHT) );
            }
            nano_start = System.currentTimeMillis();
            System.out.println("For 5000 elements + List   Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph3_list));
            System.out.println("For 5000 elements + Matrix Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph3_matrix));
            nano_end = System.currentTimeMillis();
            runningTime_5000_avg_dfs += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            System.out.println("For 5000 elements + List   Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph3_list));
            System.out.println("For 5000 elements + Matrix Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph3_matrix) + "\n");
            nano_end = System.currentTimeMillis();
            runningTime_5000_avg_bfs += (nano_end-nano_start);
            
            MAX_VERTICES = 10000;
            ListGraph graph4_list = new ListGraph(MAX_VERTICES, false);
            MatrixGraph graph4_matrix = new MatrixGraph(MAX_VERTICES, false);
            
            nano_start = System.currentTimeMillis();
            size = random.nextInt( MAX_VERTICES );
            for(int i=0; i < size; i++){
                int u = random.nextInt(i+1);
                int v = i+1;
                graph4_list.insert( new Edge(u, v, i+5, Edge.TIME_WEIGHT) );
                graph4_matrix.insert( new Edge(u, v, i+5, Edge.TIME_WEIGHT) );
            }
            nano_start = System.currentTimeMillis();
            System.out.println("For 10000 elements + List   Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph4_list));
            System.out.println("For 10000 elements + Matrix Graph + DFS | Num of connect components: " + findNumOfConnectedComponentsWithDFS(graph4_matrix));
            nano_end = System.currentTimeMillis();
            runningTime_10000_avg_dfs += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            System.out.println("For 10000 elements + List   Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph4_list));
            System.out.println("For 10000 elements + Matrix Graph + BFS | Num of connect components: " + findNumOfConnectedComponentsWithBFS(graph4_matrix) + "\n");
            nano_end = System.currentTimeMillis();
            runningTime_10000_avg_bfs += (nano_end-nano_start);
        }
        
        //Getting average values of all results.
        runningTime_1000_avg_dfs  /= 10.0;      runningTime_1000_avg_bfs  /= 10.0;
        runningTime_2000_avg_dfs  /= 10.0;      runningTime_2000_avg_bfs  /= 10.0;
        runningTime_5000_avg_dfs  /= 10.0;      runningTime_5000_avg_bfs  /= 10.0;
        runningTime_10000_avg_dfs /= 10.0;      runningTime_10000_avg_bfs /= 10.0;
        System.out.println("Running time average for 1000 elements  DFS " + runningTime_1000_avg_dfs);
        System.out.println("Running time average for 1000 elements  BFS " + runningTime_1000_avg_bfs);
        System.out.println("Running time average for 2000 elements  DFS " + runningTime_2000_avg_dfs);
        System.out.println("Running time average for 2000 elements  BFS " + runningTime_2000_avg_bfs);
        System.out.println("Running time average for 5000 elements  DFS " + runningTime_5000_avg_dfs);
        System.out.println("Running time average for 5000 elements  BFS " + runningTime_5000_avg_bfs);
        System.out.println("Running time average for 10000 elements DFS " + runningTime_10000_avg_dfs);
        System.out.println("Running time average for 10000 elements BFS " + runningTime_10000_avg_bfs);

    }

    /**
     * Finding number of connected vertices with Breadth first algorithm.
     * @param graph graph to check
     * @return # of compoments
     */
    public static int findNumOfConnectedComponentsWithBFS(Graph graph){
        return BreadthFirstSearch.findNumOfConnectedComponents(graph);
    }

    /**
     * Finding number of connected vertices with Depth first algorithm.
     * @param graph graph to check
     * @return # of compoments
     */
    public static int findNumOfConnectedComponentsWithDFS(Graph graph){     
        DepthFirstSearch graphDfs = new DepthFirstSearch(graph);
        return graphDfs.findNumOfConnectedComponents();
    }


    /** Driver code for Part3 */
    public static void part3(){
        // Creating a graph to calculata normalized importance
        System.out.println("Testing part3 for list graph: ");
        Graph g = new ListGraph(5, false);
        Edge edge1 = new Edge(0, 4); g.insert( edge1 );
        Edge edge2 = new Edge(1, 3); g.insert( edge2 );
        Edge edge3 = new Edge(2, 4); g.insert( edge3 );
        Edge edge4 = new Edge(3, 2); g.insert( edge4 );
        Edge edge5 = new Edge(3, 4); g.insert( edge5 );
        System.out.println( "Normalized importance is : " + calcNormalizedImportance(g) );

        System.out.println("Testing part3 for matrix graph: ");
        Graph g2 = new MatrixGraph(5, false);
        g2.insert( edge1 );
        g2.insert( edge2 );
        g2.insert( edge3 );
        g2.insert( edge4 );
        g2.insert( edge5 );
        System.out.println( "Normalized importance is : " + calcNormalizedImportance(g2) );
    }

    /**
     * Calculates importance
     * @param g graph
     * @return importance value that calculated
     */
    public static double calcNormalizedImportance(Graph g){
        int summatation_nominator = 0;
        int summatation_denominator = 0;
        double summatation = 0.0;
        
        int size = g.getNumV();
        for(int i=0; i<size; i++){ //Go all vertices
            summatation_nominator = 0; 
            summatation_denominator = 0;
                int pred[] = new int[size]; //Create predecesoor to hold path.
                for(int m=0; m<pred.length; m++) 
                    pred[m] = 0;
                try{
                    pred = BreadthFirstSearch.breadthFirstSearch(g, i); //Find shortest path with breadth first(no need dijkstra algorithm because unweighted)
                }catch( IndexOutOfBoundsException e){ e.printStackTrace(); }
                //Make sure that u and w is not equal.
                if(pred[0] != pred[size-1]){
                    // System.out.println( Arrays.toString(pred) );
                    for(int j=1; j<size-1; j++){ //Finding v value or values between u and w.
                        if(pred[j] != pred[0] && pred[j] != pred[size-1]){ //If found and number is different than u and w add nom and denom.
                            summatation_denominator++;
                            summatation_nominator++;
                        }else  //If not found just increase denominator.
                            summatation_denominator++;
                    }
                }
            //Add to summation value.    
            summatation += (summatation_nominator*1.0)/summatation_denominator;
            // System.out.println(summatation);
        }

        //Find fair importance value by dividing the vertice's square value.
        double fairImportance = summatation / (g.getNumV()*g.getNumV()) ;
        // System.out.println(fairImportance);
        return fairImportance;

    }

    
}