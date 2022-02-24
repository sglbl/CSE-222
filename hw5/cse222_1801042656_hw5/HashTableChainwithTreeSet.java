package cse222_1801042656_hw5;

import java.util.*;

@SuppressWarnings("unchecked")
/** Hash table implementation using chaining. */
public class HashTableChainwithTreeSet<K extends Comparable<K>, V> implements KWHashMap<K, V> {
    // Insert inner class Entry<K, V> here.
    
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K extends Comparable<K>, V> implements Comparable< Entry<K,V> > {
        /** The key */
        private final K key;
        /** The value */
        private V value;
        /** Creates a new key‐value pair.
            @param key The key
            @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /** Retrieves the key.
            @return The key
        */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
            @return The value
        */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         *  @param val The new value
         *  @return The old value
        */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
        @Override
        public int compareTo(Entry<K, V> entry) {
            K keyToCompare = entry.getKey();
            return keyToCompare.compareTo(key);
        }
        
    }


    /** The table */
    private TreeSet<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    // Constructor
    public HashTableChainwithTreeSet() {
        table = new TreeSet[CAPACITY];
    }

    /** Method get for class HashtableChain.
      @param key The key being sought
      @return The value associated with this key if found;
              otherwise, null
   */
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.

        // Search the list at table[index] to find the key.
        for (Entry < K, V > nextItem : table[index]) {
            if (nextItem.key.equals(key))
                return nextItem.value;
        }

        // assert: key is not in the table.
        return null;
    }
    
    /** Method put for class HashtableChain.
      @post This key-value pair is inserted in the
            table and numKeys is incremented. If the key is already
            in the table, its value is changed to the argument
            value and numKeys is not changed.
      @param key The key of item being inserted
      @param value The value for this key
      @return The old value associated with this key if
              found; otherwise, null
   */
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new TreeSet < Entry < K, V >> ();
        }

        // Search the list at table[index] to find the key.
        for (Entry < K, V > nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.key.equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }

        // assert: key is not in the table, add new item.
        table[index].add(new Entry < K, V > (key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }   

    /**
     * Rehashing when LOAD_THRESHOLD * length is smaller than numKeys.
     */
    public void rehash(){
        TreeSet< Entry<K,V> >[] tableTemp;
        tableTemp = table;
        int newSize = 2*tableTemp.length+1;
        table = new TreeSet[newSize];

        numKeys = 0;
        for(int i=0; i<tableTemp.length; i++){
            if(tableTemp[i] == null) continue;
            for(Entry<K,V> next : tableTemp[i]){
                K key = next.key;
                V value = next.value;
                this.put( key,value ); //add entry to new table.
            }
        }//End of for block
    }//End of rehash() method

    /**
     * @return true if empty (numKeys = 0).
     */
    public boolean isEmpty(){
        if(numKeys != 0)
            return false;
        return true;
    }

    /**
     * Returns size (how many entries) in map.
     */
    public int size(){
        return numKeys;
    }

    /**
     * Removing
     * @param key to be removed.
     * @return removed element, if not found returns null.
     */
    public V remove(Object key){
        int index;
        index = key.hashCode() % table.length;
        if(index < 0)   index += table.length;
        if(table[index] == null)
            return null; //Key is not in the table.
        Iterator< Entry<K,V> > myIterator;
        Entry<K,V> next;
        Entry<K,V> tempDelete;
        myIterator = table[index].iterator();
        for(; myIterator.hasNext() == true ;){
            next = myIterator.next();
            if(next.key.equals(key) == true){
                tempDelete = next;
                myIterator.remove();
                return tempDelete.getValue();
            }
        }
        //Not in table so return null.
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[ \n");
        Iterator< Entry<K,V> > myIterator;
        Entry<K,V> next;
        for(int i=0; i<table.length; i++){
            if(table[i] != null){
                myIterator = table[i].iterator();
                for(; myIterator.hasNext() == true ;){
                    next = myIterator.next();
                    sb.append(next.key + "=>" + next.value+ ", ");   
                }
                sb.append("\n");
            }
        }

        return sb.toString() + " ]";
    }

        
}