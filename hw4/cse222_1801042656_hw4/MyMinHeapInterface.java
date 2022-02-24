package cse222_1801042656_hw4;

public interface MyMinHeapInterface<E extends Comparable<E> > {
    
    /**
     * Checks which one is bigger of these generic types
     * @param element1 
     * @param element2
     * @return true if element1 is bigger.
     */
    public boolean isBigger(E element1, E element2);

    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     */
    public void add(E e);
    
    /**
     * Tree condition when element add.
     */
    public void setInitialTree();

    /**
     * Swapping child and parent and their indexes to heapify.
     */
    public void swapChildAndParent();

    /**
     * Searches for that element and returns true if found
     * @param e element to search
     * @return true if found, and false if couldn't find.
     */
    public boolean search(E e);

    /**
     * Merges heap with another heap
     * @param heapToBeMerged heap to be merged.
     */
    public void mergeWithAnotherHeap(MyMinHeap<E> heapToBeMerged);

    /**
     * When element going to be delete, checks index of it.
     * @param largestIndex index the largest.
     * @return which index that contains.
     */
    public int findIndexOfLargest(int largestIndex);


    /**
     * Remove's i'th element
     * @param i index to remove.
     */
    public void removeIthLargestElement(int i);

    public String toString();

    public void display();
    
    public int size();


    
}
