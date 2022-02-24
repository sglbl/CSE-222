package cse222_1801042656_hw7;

import java.util.*;

public class SkipListWithNavigableSet<E extends Comparable<E> > implements NavigableSet<E> {

    /** Private inner class for node to hold every data. */
    private static class Node<E>{
        Node<E>[] elements;
        private E val; 

		@SuppressWarnings("unchecked")
        public Node(int nodeLevel, E val){
            elements = (Node<E>[]) new Node[nodeLevel];
            this.val = val;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(val.toString() + "|level" + this.size() + "|, ");
            return sb.toString();
        }

        public int size(){
            try{
                return elements.length;
            }catch(NullPointerException exception){
                return 0;
            }
        }

    }

    /** Will hold the min data when adding first time. */
	static final int MIN = Integer.MIN_VALUE;

    /** size of skiplist */
    private int size;
    /** max level of max level */
    private int maxLevel;
    /** Smallest power of 2 gretarer than skiplist size.*/
    private int maxCap; 
    /** Head of skip list. */
    public Node<E> head;
    /** Tail of skip list to use when iterating in descending order. */
    public Node<E> tail;

    /** Constructor to initalize */
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public SkipListWithNavigableSet(){
        size = 0;
        maxLevel = 0;
        maxCap = (int)Math.pow((int)2, maxLevel) -1;
        head = new Node(maxLevel,MIN);
    }

    /**
     * Searching predator.
     * @param target target to search for predator
     * @return the predator node array.
     */
    @SuppressWarnings("unchecked")
    public Node<E>[] search(E target){
        Node<E>[] pred = (Node<E>[]) new Node[maxLevel];
        Node<E> currentNode = head;
        //currentNode.elements = new ArrayList<Node<E>>();
        for(int i=currentNode.size()-1; i >= 0; --i){
            while(currentNode.elements[i] != null &&  currentNode.elements[i].val.compareTo(target) < 0 ){
                currentNode = currentNode.elements[i];
            }
            pred[i] = currentNode;
            tail = pred[i];
        }
        
        return pred;
    }

    /**
     * Inserting new element
     * @param element to insert
     * @return true if successfully added.
     */
    public boolean insert(E element){
        return add(element);
    }
    
    /**
     * Adding element 
     * @param element to be add
     * @return true if successful
     */
    @Override
    public boolean add(E element) {
        size++;
        Node<E>[] pred = search(element);
        if(size > maxCap){
            maxLevel++;
            head.elements = Arrays.copyOf(head.elements, maxLevel);
            pred = Arrays.copyOf(pred, maxLevel);
            maxCap = (int)Math.pow((int)2, maxLevel) -1;            
            pred[maxLevel-1] = head;

        }
        
        Node<E> newNode = new Node<E>(randomNodeLevel() , element);
        //System.out.println("newNode element size->" + newNode.elements.size() );
        for(int i=0; i<newNode.size(); i++){
            newNode.elements[i] = pred[i].elements[i];
            pred[i].elements[i] = newNode;
            tail = pred[i].elements[i]; //Making tail as predator.
        }

        return true;
    }

    /**
     * Sets new random node level
     * @return level to created.
     */
    public int randomNodeLevel(){
        Random random = new Random();
        
        int gapValue = (int)(Math.log( random.nextInt( (int)Math.pow((int)2, maxLevel) -1 ) + 1) / Math.log(2));
        if( maxLevel-1 < gapValue )
            gapValue = maxLevel-1;

        return maxLevel-gapValue;
    }
    
    public boolean delete(Object item){
        return remove(item);
    }

    /**
     * Removing item from list.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object item){
        Node<E>[] pred = (Node<E>[])new Node[maxLevel] ;
        pred = search((E)item);
        tail = pred[0];
        if(pred[0].elements != null && pred[0].elements[0].val.compareTo((E)item) != 0)
            return false;
        size--;
        Node<E> nodeToRemove = pred[0];
        for(int i=0; i<nodeToRemove.size(); i++){
            if(pred[i].elements[i] != null)
                pred[i].elements[i] = pred[i].elements[i].elements[i];
        }
        return true;
    }

    /***
     * Iterating descening order
     * @return an iterator in descending order.
     * NOTE: next will iterate in reverse order. hasNext will check for smaller.
     */
    @Override
    public Iterator<E> descendingIterator() {
        Iterator<E> iter = new Iterator<E>(){
            Node<E> temp2 = tail;
            boolean isNextUsed = false;

            /**  returns next element and make cursor to hold next one */
            public E next(){
                if(isNextUsed == false){
                    Node<E> returnValue = temp2;
                    //System.out.print("return value -"+returnValue);
                    isNextUsed = true;
                    temp2 = search(temp2.val)[0];
                    return returnValue.val;
                }
                temp2 = search(temp2.val)[0];
                return temp2.val;
            }

            /**Ckecks if has next */
            public boolean hasNext(){
                try{
                    if( search(temp2.val)[0] != head)
                        return true;
                    return false;
                }catch(NullPointerException e){
                    return false;
                }
            }
            
        };

        return iter;
    }

    /**
     * @return skip list datas in string .
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> iterator = head;
        sb.append("HeadLevel " + maxLevel + " -> ");
        while( iterator.size() != 0 && iterator.elements[0] != null ){
            iterator = iterator.elements[0];
            sb.append(iterator.toString() + " -> ");
        }
        

        return sb.toString();


    }

    /** 
     * To check in main if elemnt is contained
     * @param element element to check
     * */ 
    public boolean contains(E element){
        Iterator<E> iter = this.descendingIterator();
        while(iter.hasNext())
            if( iter.next().equals(element) == true ) 
                return true;
        return false;
    }
    
    @Override
    public boolean contains(Object arg0) {
        /** Intentionally Empty */
        return false;
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
    public SortedSet<E> headSet(E arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E arg0, boolean arg1) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public E higher(E arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public Iterator<E> iterator() {
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

    @Override
    public SortedSet<E> tailSet(E arg0) {
        /** Intentionally Empty */
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E arg0, boolean arg1) {
        /** Intentionally Empty */
        return null;
    }

    
    
}
