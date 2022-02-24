package cse222_1801042656_hw3;
import java.util.*;                
@SuppressWarnings({ "unchecked", "rawtypes" })

/** Class KWLinkedList implements a double‐linked list and a ListIterator. */
public class KWLinkedList<E> {
// Data Fields
/** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;


    private static class Node<E> {
        // Data Fields
        private Node<E> prev = null; /* The reference previous node*/
        private E data;     /** The reference to the data. */
        private Node<E> next; /** The reference to the next node. */

        // Constructors
        /** Creates a new node with a null next field.
            @param dataItem The data stored
        */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }
        /** Creates a new node that references another node.
        @param dataItem The data stored
        @param nodeRef The node referenced by new node
        */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    } //End of Node class.



    /** Add an item at position index.
        @param index The position at which the object is to beinserted
        @param obj The object to be inserted
        @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i > size())
    */
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    /** Get the element at position index.
        @param index Position of item to be retrieved
        @return The item at index
    */
    public E get(int index) {
        ListIterator<E> iter = listIterator(index); 
      	return iter.next();
    }

    /**Returns the size */
    public int size(){
        return size;
    }



    public ListIterator listIterator(int index){
        return new KWListIter(index);
    }

    /** Inner class to implement the ListIterator interface. */
    private class KWListIter implements ListIterator<E> {
        private Node<E> nextItem;   /** A reference to the next item. */
        private Node<E> lastItemReturned;    /** A reference to the last item returned. */
        private int index = 0;  /** The index of the current item. */

        /** Construct a KWListIter that will reference the ith item.
            @param i The index of the item to be referenced
        */
        public KWListIter(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            }
            else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**Returns previous index */
        public int previousIndex(){
            return index - 1;    
        }
        /**Returns index number */
        public int nextIndex(){
            return index;    
        }


        /** Indicate whether movement forward is defined.
            @return true if call to next will not throw an exception
        */
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
            @return The next item in the list
            @throws NoSuchElementException if there is no such object
        */
        public E next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /** Indicate whether movement backward is defined.
            @return true if call to previous will not throw an exception
        */
        public boolean hasPrevious() {
            return (nextItem == null && size != 0) || nextItem.prev != null;
        }

        /** Move the iterator backward and return the previous item.
            @return The previous item in the list
            @throws NoSuchElementException if there is no such object
        */
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator is past the last element
                nextItem = tail;
            }
            else{
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /** Add a new item between the item that will be returned
            by next and the item that will be returned by previous.
            If previous is called after add, the element added is
            returned.
            @param obj The item to be inserted
        */
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<E>(obj);
                tail = head;
            } 
            else if (nextItem == head) { // Insert at head.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 1
                // Link nextItem to the new node.
                nextItem.prev = newNode; // Step 2
                // The new node is now the head.
                head = newNode; // Step 3
            }
            else if (nextItem == null) { // Insert at tail.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link the tail to the new node.
                tail.next = newNode; // Step 1
                // Link the new node to the tail.
                newNode.prev = tail; // Step 2
                // The new node is the new tail.
                tail = newNode; // Step 3
            } 
            else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev; // Step 1
                nextItem.prev.next = newNode; // Step 2
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 3
                nextItem.prev = newNode; // Step 4
            }

            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.

        public void set(E obj){ //throws IllegalStateException
            if (lastItemReturned == null)
                throw new IllegalStateException();
            else
                lastItemReturned.data = obj;
        }

        public void remove(){
            if(lastItemReturned != null){
                lastItemReturned.next.prev = lastItemReturned.prev;
                lastItemReturned.prev.next = lastItemReturned.next;
                lastItemReturned = null;
            }
            else{
                tail = tail.prev;
               // tail.next = null;
                size--;
            }
        }



    }

    public void remove(){
        if(tail==null)
            throw new IllegalStateException();
        tail = tail.prev;
        tail.next = null;
        size--;     
    }

    public void remove(int index) {
        listIterator(index).remove();
    }


    /**
     * /It creates a counter to find index of element to remove
     * @param element element to remove
     */
    public void remove(E element){
        if(tail==null)
            throw new IllegalStateException();
        Node<E> temp = head;
        int counter=0;  //Counter to find index of element to remove
        while(temp != null){
            counter++;
            if(temp.data == element){   
                remove(counter);
                break;
            }
            temp = temp.next;
        }
    }

    /** Making head and tail null is enough to clear, Garbage collecter handles other nodes. 
     * Clearing linkedlist
     */
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public String toString(){
        Node<E> temp = head;
        StringBuilder sb= new StringBuilder("[ ");
        for(int i=0; i<size(); i++){
            if(i != size()-1) sb.append( temp.data + ", ");
            else              sb.append( temp.data + " " );
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }



}
