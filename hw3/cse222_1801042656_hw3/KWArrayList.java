package cse222_1801042656_hw3;

import java.util.*;
@SuppressWarnings("unchecked")
/** This class implements some of the methods of the Java ArrayList class. It
does not implement the List interface.
*/
public class KWArrayList<E> {
    // Data Fields
    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    public KWArrayList() {
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }

    public boolean add(E anEntry) {
        if (size == capacity)
            reallocate();
        theData[size] = anEntry;
        size++;
        return true;
    }

    public void add(int index, E anEntry) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == capacity) {
            reallocate();
        }
        // Shift data in elements from index to size ‐ 1
        for (int i = size; i > index; i--) {
            theData[i] = theData[i-1];
        }
        // Insert the new item.
        theData[index] = anEntry;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) 
            throw new ArrayIndexOutOfBoundsException(index);
        return theData[index];
    }

    public E set(int index, E newValue){
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) 
            throw new ArrayIndexOutOfBoundsException(index);
        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++)
            theData[i-1] = theData[i];
        size--;
        return returnValue;
    }

    /**
     * Removes element with using i as index counter.
     * @param e element to delete
     * @return true if successful
     */
    public boolean remove(E e){
        for(int i=0; i<size(); i++){
            if( e == theData[i] ){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Clears array
     */
    public void clear(){
        theData = null;
    }

    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**Returns the size */
    public int size(){
        return size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[ ");
        for(int i=0; i<size(); i++){
            if(i != size()-1) sb.append( get(i) + ", ");
            else              sb.append( get(i) + " ");
        }
        sb.append("]");
        return sb.toString();
    } 




 }