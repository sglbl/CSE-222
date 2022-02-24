package cse222_1801042656_hw8;
/** An Edge represents a relationship between two
 *  vertices.
 *  @author Koffman and Wolfgang
*/

public class Edge {
  /**** BEGIN EXERCISE ****/
  // Data Fields
  /** The source vertix */
  private int source;

  /** The destination vertix */
  private int dest;

  /** The weight of property */
  private double propertyInfo;

  private int propertyTypeForWeight;
  //Other properties.
  public static final int TIME_WEIGHT = 1;
  public static final int QUALITY_WEIGHT = 2;
  public static final int POWER_WEIGHT = 3;

  // Constructor
  /** Construct an Edge with a source of from
      and a destination of to. Set the weight
      to 1.0.
      @param from - The source vertix
      @param to - The destination vertix
   */
  public Edge(int source, int dest) {
    this.source = source;
    this.dest = dest;
    propertyInfo = 1.0;
  }

  /** Construct a weighted edge with a source
      of from and a destination of to. Set the
      weight to w.
      @param from - The source vertix
      @param to - The destination vertix
      @param w - The weight
   */
  public Edge(int source, int dest, double w) {
    this.source = source;
    this.dest = dest;
    propertyInfo = w;
  }

  
  /** Construct a weighted edge with a source
      of from and a destination of to. Set the
      weight to w.
      @param from - The source vertix
      @param to - The destination vertix
      @param propertyType type of property
      @param propertyInfo edge information
   */
  public Edge(int source, int dest, int propertyInfo, int propertyType) {
    this.source = source;
    this.dest = dest;
    //weight = w;
    this.propertyInfo = propertyInfo;
    
    if     (propertyType == TIME_WEIGHT) propertyTypeForWeight = TIME_WEIGHT;
    else if(propertyType == QUALITY_WEIGHT) propertyTypeForWeight = QUALITY_WEIGHT;
    else if(propertyType == POWER_WEIGHT) propertyTypeForWeight = POWER_WEIGHT;
    else throw new IllegalStateException(); //Throwing exception because there is no other property

  }


  // Methods
  /** Get the source
      @return The value of source
   */
  public int getSource() {
    return source;
  }

  /** Get the destination
      @return The value of dest
   */
  public int getDest() {
    return dest;
  }

  /** Get the weight
      @return the value of weight (any property)
   */
  public double getWeight() {
    return propertyInfo;
  }

  /**
   * 
   * @return info of the property like time quality or power.
   */
  public double getPropertyInfo(){
      return propertyInfo;
  }

  /**
   * 
   * @return type of property
   */
  public double getPropertyType(){
    return propertyTypeForWeight;
}

  /** Return a String representation of the edge
      @return A String representation of the edge
   */
  public String toString() {
    StringBuffer sb = new StringBuffer("[(");
    sb.append(Integer.toString(source));
    sb.append(", ");
    sb.append(Integer.toString(dest));
    sb.append("): ");
    //sb.append(Double.toString(weight));
    sb.append(Double.toString(propertyInfo));
    sb.append("]");
    return sb.toString();
  }

  /** Return true if two edges are equal. Edges
      are equal if the source and destination
      are equal. Weight is not conidered.
      @param obj The object to compare to
      @return true if the edges have the same source
      and destination
   */
  public boolean equals(Object obj) {
    if (obj instanceof Edge) {
      Edge edge = (Edge) obj;
      return (source == edge.source && dest == edge.dest);
    }
    else {
      return false;
    }
  }

  /** Return a hash code for an edge.  The hash
      code is the source shifted left 16 bits
      exclusive or with the dest
      @return a hash code for an edge
   */
  public int hashCode() {
    return (source << 16) ^ dest;
  }

  /**** END EXERCISE ****/
}
