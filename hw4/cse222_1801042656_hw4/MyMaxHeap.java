package cse222_1801042656_hw4;
import java.util.*;

public class MyMaxHeap< E extends Comparable<E> > implements MyMaxHeapInterface<E>{

    private int childIndex, parentIndex;
    private ArrayList< Data<E> > heap;

    public MyMaxHeap(){
        heap = new ArrayList< Data<E> >();
    }

    public MyMaxHeap(E e){
        heap = new ArrayList< Data<E> >();
        add(e);
    }

    /**
     * Checks which one is smaller of these generic types
     * @param element1
     * @param element2
     * @return true if element1 is smaller.
     */
    @Override
    public boolean isSmaller(E element1, E element2){
        if(element1.compareTo(element2) >= 0)
            return false;
        return true;
    }


    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     * @return occurence of e.
     */
    @Override
    public int add(E e){
        return add(e, 1);
    }
    
    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     * @return occurence of e.
     */
    @Override
    public int add(E e, int numberOfOccurences){
        ListIterator<Data<E>> iterator = heap.listIterator();
        while(iterator.hasNext() == true){
            if(iterator.next().getValue().compareTo(e) == 0){
                int numberOfOccurences2 = iterator.previous().incrementNumberOfOccurences();
                return numberOfOccurences2;
            }
        }
        //If program reaches here that means there is no occurancy for that value before.
            
        heap.add(heap.size(), new Data<E>(e,numberOfOccurences) ); //Firstly adding element to the end of the list and setting occurency number to 1.

        setInitialTree();
        //int index = heap.size()-1;
        while( isSmaller( heap.get(parentIndex).getValue(), heap.get(childIndex).getValue() )  && 0 <= parentIndex){
            swapChildAndParent();
            swapIndexes();
        }
        return 1;
    }
    
    /**
     * Sets tree condition when element add.
     */
    @Override
    public void setInitialTree(){
        childIndex = heap.size();
        childIndex--;
        parentIndex = (childIndex-1)/(2);
    }

    /**
     * Swap the indexes.
     */
    @Override
    public void swapIndexes(){
        //Changing index values.
        childIndex = parentIndex;
        parentIndex = (childIndex-1)/2;
    }

    /**
     * Swapping child and parent and their indexes to heapify.
     */
    @Override
    public void swapChildAndParent(){
        // Changing heap values
        Data<E> temp = heap.get(parentIndex);
        heap.set(parentIndex, heap.get(childIndex) );
        heap.set(childIndex, temp);
    }

    public ListIterator< Data<E> > listIterator(){
        return heap.listIterator();
    }

    /**
     * Removes the item.
     * @param item to be removed
     * @return occurence of that item.
     */
    @Override
    public int remove(E item){
        int occurence = 0;
        //Checking if i is smaller than size.
        
        for(int i=0; i<heap.size(); i++){
            if( heap.get(i).getValue().compareTo(item) == 0){ //if item found
                if( heap.get(i).getNumberOfOccurences() != 1){ //if just there is just one of them, delete.
                    occurence = heap.get(i).decrementNumberOfOccurences();
                    return occurence;
                }
            }
        }

        // for find index and move to root.
        int i;
        for(i=0; i<heap.size(); i++)
            if( heap.get(i).getValue().compareTo(item) == 0 && heap.get(i).getNumberOfOccurences() == 1){ //if item found there is just 1 of it.
                break;
            }
        
        if(i == size())
            throw new IndexOutOfBoundsException();

        heap.set(i, heap.get( heap.size()-1 ) );
        heap.remove(heap.size()-1);
        heapify();
        
        return 1; 
    }

    /**
     * Fixes the heap
     */
    @Override
    public void heapify(){
        int leftIndex, rightIndex;
        parentIndex = 0;
        while(true){
            leftIndex  = 2*parentIndex+1;
            rightIndex = 2*parentIndex+2;
            if(leftIndex >= size())
                break;
            childIndex = leftIndex; //childIndex will hold the data of minimum child.
            if(rightIndex<size() && isSmaller( heap.get(leftIndex).getValue(), heap.get(rightIndex).getValue() ) )
                childIndex = rightIndex;
            if( isSmaller( heap.get(parentIndex).getValue(), heap.get(childIndex).getValue() ) ){
                swapChildAndParent();
                parentIndex = childIndex;
            }
            else   
                break;

        }
    }

    /**
     * @return root value of heap.
     */
    @Override
    public E getRootValue(){
        return heap.get(0).getValue();
    }

    /**
     * Increments the occurence number for root value.
     * @return new occurence value.
     */
    @Override
    public int incrementRootOccurence(){
        return heap.get(0).incrementNumberOfOccurences();
    }

    /**
     * Searches for that element and returns occurence
     * @param element element to search
     * @return number of occurences.
     */
    @Override
    public int search(E element){
        for(int i=0; i<heap.size(); i++){
            if(heap.get(i).getValue().compareTo(element) == 0)
                return heap.get(i).getNumberOfOccurences();
        }
        return 0;
    }

    /**
     *  increment occurence for element
     * @param element to be find
     * @return number of occurence.
     */
    @Override
    public int incrementNumberOfOccurences(E element){
        for(int i=0; i<heap.size(); i++){
            if(heap.get(i).getValue().compareTo(element) == 0)
                return heap.get(i).incrementNumberOfOccurences();
        }
        return 0;
    }
    
    @Override
    public void display(){
        System.out.println( heap.toString() );
    }
    
    /**
     * Displays heap without entering to new line.
     */
    @Override
    public void displayWithoutNewLine(){
        System.out.print( heap.toString() + " " );
    }

    @Override
    public String toString(){
        return heap.toString();
    }

    @Override
    public int size(){
        return heap.size();
    }

}