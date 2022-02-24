package cse222_1801042656_hw4;
import java.util.*;

public class MyMinHeap<E extends Comparable<E>> implements MyMinHeapInterface<E>{
    private int childIndex, parentIndex;
    private ArrayList<E> heap;
    private ArrayList<E> largestList;


    public MyMinHeap(ArrayList<E> heap) {
        this.heap = heap;
    }

    public MyMinHeap(){
        heap = new ArrayList<E>();
        largestList = new ArrayList<E>();
    }

    /**
     * Checks which one is bigger of these generic types
     * @param element1 
     * @param element2
     * @return true if element1 is bigger.
     */
    @Override
    public boolean isBigger(E element1, E element2){
        if(element1.compareTo(element2) <= 0)
            return false;
        return true;
    }

    /**
     * Adds e to heap and fixes the heap to be proper.
     * @param e element to be add.
     */
    @Override
    public void add(E e){
        heap.add(heap.size(), e); //Firstly adding element to the end of the list.
        largestList.add(e);
        Collections.sort(largestList, Collections.reverseOrder());

        setInitialTree();
        
        while( isBigger(heap.get(parentIndex), heap.get(childIndex))  && 0 <= parentIndex){   
            swapChildAndParent();
        }

    }
    
    /**
     * Tree condition when element add.
     */
    @Override
    public void setInitialTree(){
        childIndex = heap.size();
        childIndex--;
        parentIndex = (childIndex-1)/(2);
    }

    /**
     * Swapping child and parent and their indexes to heapify.
     */
    @Override
    public void swapChildAndParent(){
        // Changing heap values
        E temp = heap.get(parentIndex);
        heap.set(parentIndex, heap.get(childIndex) );
        heap.set(childIndex, temp);
        //Changing index values.
        int temp2 = childIndex;
        childIndex = parentIndex;
        parentIndex = (temp2-1)/2; 
    }

    /**
     * Searches for that element and returns true if found
     * @param e element to search
     * @return true if found, and false if couldn't find.
     */
    @Override
    public boolean search(E e){
        if(heap.contains(e) == false)
            return false;
        return true;
    }

    /**
     * Merges heap with another heap
     * @param heapToBeMerged heap to be merged.
     */
    @Override
    public void mergeWithAnotherHeap(MyMinHeap<E> heapToBeMerged){
        MyIterator<E> iterator2 = heapToBeMerged.myIterator();
        while(iterator2.hasNext() ){
            add( iterator2.next() );
        }

    }

    /**
     * Iterator for heap.
     * @return heap's list iterator.
     */
    public MyIterator<E> myIterator(){
        MyIterator<E> myIterator = new MyIterator<E>(heap);
        return myIterator;
    }

    /**
     * When element going to be delete, checks index of it.
     * @param largestIndex index the largest.
     * @return which index that contains.
     */
    @Override
    public int findIndexOfLargest(int largestIndex){
        for(int i=0; i<heap.size(); i++){
            if(heap.get(i) == largestList.get(largestIndex) )
                return i;
        }
        throw new IllegalStateException("The item you wanted to delete couldn't find.");
    }


    /**
     * Remove's i'th element
     * @param i index to remove.
     */
    @Override
    public void removeIthLargestElement(int i){
        //Checking if i is smaller than size.
        if(i > heap.size()){
            System.out.println("Error! Your index is bigger than size. There is no such element to remove!");
            return;
        }

        i = findIndexOfLargest(i-1);

        //Copying to a temp list.
        ArrayList<E> tempList = new ArrayList<E>();
        for(int j=0; j<heap.size(); j++){
            if(j < i)
                tempList.add(j, heap.get(j) );
            else if(j == i)
                continue;
            else
                tempList.add(j-1, heap.get(j));
        }

        heap.clear();
        for(int j=0; j<tempList.size(); j++)
            heap.add( tempList.get(j) );

    }

    @Override
    public String toString(){
        return heap.toString();
    }

    @Override
    public void display(){
        System.out.println( heap.toString() );
    }
    
    @Override
    public int size(){
        return heap.size();
    }



    
}
