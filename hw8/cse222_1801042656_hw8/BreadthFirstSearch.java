package cse222_1801042656_hw8;
import java.util.*;

/** Class to implement the breadth-first search algorithm.
 *  @author Koffman and Wolfgang
 * */

public class BreadthFirstSearch {
    private static LinkedList<Integer> visited = new LinkedList<Integer>();
    private static int numberOfConnectedComponents = 0;

    public static void addToVisited(int item){
        visited.add(item);
    }

    public static boolean ifExistAlready(int item){
        Iterator<Integer> iter = visited.iterator();
        while( iter.hasNext() ){
            if(iter.next() == item)
                return true;
        }
        return false;
    }

  /** Perform a breadth-first search of a graph.
      post: The array parent will contain the predecessor
            of each vertex in the breadth-first search tree.
      @param graph The graph to be searched
      @param start The start vertex
      @return The array of parents
   */
  public static int[] breadthFirstSearch(Graph graph, int start) {
    Queue < Integer > theQueue = new LinkedList < Integer > ();
    // Declare array parent and initialize its elements to 1.
    int[] parent = new int[graph.getNumV()];
    for (int i = 0; i < graph.getNumV(); i++) {
      parent[i] = -1;
    }

    // Declare array identified and
    // initialize its elements to false.
    boolean[] identified = new boolean[graph.getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
    identified[start] = true;
    theQueue.offer(start);
    /* While the queue is not empty */
    while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
      int current = theQueue.remove();
      /* Examine each vertex, neighbor, adjacent to current. */
      Iterator < Edge > itr = graph.edgeIterator(current);
      while (itr.hasNext()) {
        Edge edge = itr.next();
        int neighbor = edge.getDest();
        // If neighbor has not been identified
        if (!identified[neighbor]) {
          // Mark it identified.
          identified[neighbor] = true;
          // Place it into the queue.
          theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor) into the tree. */
          parent[neighbor] = current;
        }
      }
      // Finished visiting current.
    }
    return parent;
  }

  /**
   * Checks if item is already find in finded components.
   * @param item to check
   * @return true if found so not increase number of components value.
   */
  public static int findNumOfConnectedComponents(Graph graph){

    if(graph instanceof ListGraph){
        List<Edge>[] list = graph.getAllEdges();
        for(int i=0; i<list.length; i++){
            findNumOfConnectedComponents(graph, i);
        }
    }
    else if(graph instanceof MatrixGraph){
        double[][] matrix = graph.getAllEdges(0);
        for(int i=0; i< matrix.length; i++){
            findNumOfConnectedComponents(graph, i);
        }
    }
    // Making all null and creating a new linked list and making num of compoenents 0 again, because it is static method.
    //If it will be called again, not to create a problem.
      visited = null; //because static
      visited = new LinkedList < Integer > ();
      int returnVal = numberOfConnectedComponents;
      numberOfConnectedComponents = 0;
      return  returnVal;
  }

  /**
   * Helper method actually  for finding num of components that start from start.
   * @param graph to check
   * @param start  start index vertice
   */
  public static void findNumOfConnectedComponents(Graph graph, int start){
    Queue < Integer > theQueue = new LinkedList < Integer > ();
    // Declare array parent and initialize its elements to 1.
    int[] parent = new int[graph.getNumV()];
    for (int i = 0; i < graph.getNumV(); i++) {
        parent[i] = -1;
    }

    boolean isAdded = false;

    // Declare array identified and
    // initialize its elements to false.
    boolean[] identified = new boolean[graph.getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
    identified[start] = true;
    theQueue.offer(start);

    /* While the queue is not empty */
    while (!theQueue.isEmpty()) {
        /* Take a vertex, current, out of the queue. (Begin visiting current). */
        int current = theQueue.remove();
        //If exist just return
        if( ifExistAlready( current ) ){
            //System.out.println("edge source is " + current );
            return;
        }
        else{
            visited.add( current );
            if( isAdded == false){  //Increasing number of components.
                numberOfConnectedComponents++;
                isAdded = true; // to not to increment again .
            }
        }
        
      /* Examine each vertex, neighbor, adjacent to current. */
      Iterator < Edge > itr = graph.edgeIterator(current);
      while (itr.hasNext()) {
        Edge edge = itr.next();
        int neighbor = edge.getDest();
        // If neighbor has not been identified
        if (!identified[neighbor]){
            // Mark it identified.
            identified[neighbor] = true;
            // Place it into the queue.
            theQueue.offer(neighbor);
            /* Insert the edge (current, neighbor) into the tree. */
            parent[neighbor] = current;   
        }
      }
      // Finished visiting current.
    }

  }
}