package cse222_1801042656_hw4;

public interface BSTHeapTreeInterface<E extends Comparable<E> > {
   
    /**
     * Adding item to root
     * @param item
     * @return occurence of item.
     */
    public int add(E item);

    /**
     * Removing
     * @param item to be removed
     * @return new occurence value.
     */
    public int remove(E item);

    /**
     * Most frequently added element
     * @return occurence of mode element
     */
    public int find_mode();

    /**
     * Most frequently added element
     * @return value of mode element.
     */
    public E find_modeValue();

    /**
     * Finding item's occurence
     * @param item item to be checked for occurence
     * @return occurence
     */
    public int find(E item);

    /**
     * Displaying heap
     */
    public void display();

    /**
     * Displaying max heap (node) that contains 7 element max.
     * @param heap heap node to be display.
     */
    public void display(MyMaxHeap<E> heap);
    /**
     * @return size
     */
    public int size();


}
