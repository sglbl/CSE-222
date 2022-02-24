package cse222_1801042656_hw7;

import java.util.*;
import cse222_kwbook_implementations.*;

public class AVLTreeWithNavigableSet<E extends Comparable<E>> implements NavigableSet<E> {
    
    AVLTree<E> avl = new AVLTree<E>();

    /**
     * Inserting to avl tree
     * @param element element to insert
     * @return true if successfully added and false if not successful.
     */
    public boolean insert(E element) {
        return avl.add(element);
    }  

    public String toString(){
        return avl.toString();
    }
    
    /**
     * Iterating through iteraotr
     */
    @Override
    public Iterator<E> iterator() {
        return avl.iterator();
    }

    /**
     * @param element element to divide from.
     * @param isInclusive if inclusive is true than include.
     * @return new avl tree as navigable set.
     *  Dviding to head set to new avl tree..
     */
    @Override
    public NavigableSet<E> headSet(E element, boolean isInclusive) {
        AVLTreeWithNavigableSet<E> avlToReturn = new AVLTreeWithNavigableSet<E>();
        Iterator<E> iter = this.iterator();

        E tempElement;
        while(iter.hasNext()){
            if(isInclusive == true)
                if(element.compareTo( tempElement = iter.next() ) >= 0) //include if equal
                    avlToReturn.insert(tempElement);
            else if(isInclusive == false)
                if(element.compareTo( tempElement = iter.next() ) > 0) //dont include if not equal.
                avlToReturn.insert(tempElement);
        }

        return avlToReturn;
    }
    
    /**
     * @param element element to divide from.
     * @param isInclusive if inclusive is true than include.
     *  Dviding to tail set to new avl tree..
     */
    @Override
    public NavigableSet<E> tailSet(E element, boolean isInclusive) {
        AVLTreeWithNavigableSet<E> avlToReturn = new AVLTreeWithNavigableSet<E>();
        Iterator<E> iter = this.iterator();

        E tempElement;
        while(iter.hasNext()){
            if(isInclusive == true)
                if(element.compareTo( tempElement = iter.next() ) <= 0) //include if equal
                    avlToReturn.insert(tempElement);
            else if(isInclusive == false)
                if(element.compareTo( tempElement = iter.next() ) < 0) //dont include if not equal.
                avlToReturn.insert(tempElement);
        }

        return avlToReturn;
    }

   
    @Override
    public boolean add(E arg0) {
        return avl.add(arg0);
    }  

    @Override
    public boolean remove(Object arg0) {
        /** Intentionally Empty */
        return false;
    }

    
    @Override
    public Iterator<E> descendingIterator() {
    
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E first() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E last() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public void clear() {
        /** Intentionally Empty */
        
    }

    @Override
    public boolean contains(Object arg0) {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public boolean isEmpty() {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        /** Intentionally Empty */
        return false;
    }

    @Override
    public int size() {
        /** Intentionally Empty */
        return 0;
    }

    @Override
    public Object[] toArray() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E ceiling(E arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E floor(E arg0) {
        /** Intentionally Empty */
        return null;
    }


    @Override
    public E higher(E arg0) {
        /** Intentionally Empty */
        return null;
    }


    @Override
    public E lower(E arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E pollFirst() {
        /** Intentionally Empty */
        return null;
    }

     
    @Override
    public SortedSet<E> headSet(E element) {
        /** Intentionally empty */
        return null;
    }

    
    @Override
    public SortedSet<E> tailSet(E arg0) {
        /** Intentionally Empty */
        return null;
    }
   

    @Override
    public E pollLast() {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public SortedSet<E> subSet(E arg0, E arg1) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E arg0, boolean arg1, E arg2, boolean arg3) {
        /** Intentionally Empty */
        return null;
    }

    
    
    
}
