package cse222_1801042656_hw8;

import java.util.Comparator;

/** A class for comparing 2 edges.
 *  @author Koffman and Wolfgang
 */

public class CompareEdges
    implements Comparator < Edge > {
  public int compare(Edge e1, Edge e2) {
    return Double.compare(e1.getWeight(), e2.getWeight());
  }
}
