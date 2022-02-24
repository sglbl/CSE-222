package cse222_1801042656_hw4;

public interface MyMaxHeapInterface<E extends Comparable<E> >{

    /**
     * Checks which one is smaller of these generic types
     * @param element1
     * @param element2
     * @return true if element1 is smaller.
     */
    public boolean isSmaller(E element1, E element2);

    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     * @return occurence of e.
     */
    public int add(E e);
    
    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     * @return occurence of e.
     */
    public int add(E e, int numberOfOccurences);
    
    /**
     * Sets tree condition when element add.
     */
    public void setInitialTree();

    /**
     * Swap the indexes.
     */
    public void swapIndexes();

    /**
     * Swapping child and parent and their indexes to heapify.
     */
    public void swapChildAndParent();

    /**
     * Removes the item.
     * @param item to be removed
     * @return occurence of that item.
     */
    public int remove(E item);

    /**
     * Fixes the heap
     */
    public void heapify();

    /**
     * @return root value of heap.
     */
    public E getRootValue();

    /**
     * Increments the occurence number for root value.
     * @return new occurence value.
     */
    public int incrementRootOccurence();

    /**
     * Searches for that element and returns true if found
     * @param element element to search
     * @return number of occurences.
     */
    public int search(E element);

    /**
     *  increment occurence for element
     * @param element to be find
     * @return number of occurence.
     */
    public int incrementNumberOfOccurences(E element);

    /**
     * Displays heap without entering to new line.
     */
    public void displayWithoutNewLine();

    public void display();

    public String toString();

    public int size();


}
