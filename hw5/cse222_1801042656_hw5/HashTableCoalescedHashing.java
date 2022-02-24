package cse222_1801042656_hw5;

import java.util.*;

/** Hash table implementation using coalesced hashing. */
public class HashTableCoalescedHashing<K extends Comparable<K>, V> implements KWHashMap<K, V> {
    // Insert inner class Entry<K, V> here.
    
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K extends Comparable<K>, V> {
        /** The key */
        private K key;
        /** The value */
        private V value;
        /** The next item index */
        private int next;
        /** Creates a new key‐value pair.
            @param key The key
            @param value The value
            @param next next value
        */
        public Entry(K key, V value, int next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        /** Retrieves the key.
            @return The key
        */
        public K getKey() {
            return key;
        }

        private void setKey(K key){
            this.key = key;
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
        /** Sets i to next item index. */
        public void setNext(int i){
            next = i;
        }
    }


    /** The table */
    private ArrayList< Entry<K, V> > table;
    /** The capacity */
    private static final int CAPACITY = 10;
    /** The maximum load factor */
    //private static final double LOAD_THRESHOLD = 3.0;
    // Constructor
    public HashTableCoalescedHashing() {
        table = new ArrayList<>();
        for(int i=0; i<CAPACITY; i++)
            table.add(new Entry<K,V>(null, null, -1));
    }

    /** Method get for class HashtableChain.
      @param key The key being sought
      @return The value associated with this key if found;
              otherwise, null
   */
    public V get(Object key) {
        int index = key.hashCode() % table.size();
        if (index < 0)
            index += table.size();
        if (table.get(index) == null)
            return null; // key is not in the table.

        // Search the list at table[index] to find the key.
        for (Entry < K, V > nextItem : table) {
            if ( nextItem.key != null && nextItem.key.equals(key))
                return nextItem.value;
        }

        // assert: key is not in the table.
        return null;
    }
    
    /**
      @param key The key of item being inserted
      @param value The value for this key
      @return The old value associated with this key if
              found; otherwise, null
   */
    public V put(K key, V value) {
        int index;
        if(table.size() == 0) 
            index = 0;
        else 
            index = key.hashCode() % table.size();
        if(index<0) 
            index += table.size();
        
        int newHashIndex = index;
        // if index'th entry is null.
        if(table.get(index).getKey() == null){
            table.get(index).setKey(key);
            table.get(index).setValue(value);
        }
        else{ //if not null
            int square = 1; //will take the square of this variable.
            //And everytime hashIndex is empty it will increment by 1 to find another hash index.
            int tempHash = index;
            while(table.get(newHashIndex).getValue() != null){
                int tempSquare = square;
                while( table.get(newHashIndex).next != -1){
                    if(table.get(tempHash).next == -1)
                        tempHash = newHashIndex;
                    else if(table.get(tempHash).next != -1 )
                        newHashIndex = index + tempSquare*tempSquare;
                    tempSquare++;
                    if(newHashIndex >= table.size())
                        rehash();
                }
                
                table.get(tempHash).setNext(newHashIndex);
                tempHash = newHashIndex;
                newHashIndex = index + square*square; //quadratic probing
                if(newHashIndex >= table.size())
                    rehash();    

                if(!checkNextFull(newHashIndex) && tempHash != newHashIndex) //setting next item
                    table.get(tempHash).setNext(newHashIndex);
                 
                square += 1;
            }
        
            table.get(newHashIndex).setKey(key);
            table.get(newHashIndex).setValue(value);
        }
    
        return table.get(newHashIndex).getValue();
    }   

    /**
     * @param index to be checked
     * @return true if full, false if not full.
     */
    public boolean checkNextFull(int index){
        if( table.get(index).next == -1)
            return false;
        return true;
    }

    /**
     * Rehashing.
     */
    public void rehash(){
        ArrayList< Entry<K,V> > tableTemp;
        tableTemp = table;
        int newSize = 2*table.size() + 1;
        //table = null;
        table = new ArrayList< Entry<K,V> >();
        for(int i=0; i<newSize; i++){
            table.add(new Entry<K,V>(null, null, -1));
        }

        for(int i=1; i<tableTemp.size(); i++){
            if(tableTemp.get(i).key != null)
                this.put(tableTemp.get(i).key,tableTemp.get(i).value);
        }

    }//End of rehash() method

    /**
     * @return true if empty (numKeys = 0).
     */
    public boolean isEmpty(){
        if(table.size() != 0)
            return false;
        return true;
    }

    /**
     * Returns size (how many entries) in map.
     */
    public int size(){
        return table.size();
    }

    /**
     * Removing 
     */
    public V remove(Object key){
        for(int i=0; i<table.size(); i++){

            if(table.get(i).getKey() != null && table.get(i).getKey().equals(key) == true)
                if(table.get(i).next != -1){
                    V removed = table.get( i ).value;
                    table.get(i).setKey( table.get( table.get(i).next ).key );
                    table.get( table.get(i).next ).key = null;
                    table.get( table.get(i).next ).value = null;
                    table.get(i).next = -1;
                    return removed;
                }else{
                    V removed = table.get( i ).value;
                    table.get( i ).setKey( null );
                    table.get( i ).setValue( null );
                    for(int j=0; j<table.size(); j++){
                        if(table.get(j).next == i){
                            table.get(j).next = -1;
                            return removed;
                        }
                    }
                }  
        }

        //Not in table so return null.
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[ \n");
        for(int i=0; i<table.size(); i++){
            sb.append("HashIndex is " + i + " key is: " + table.get(i).key + " next:" +table.get(i).next+ "\n");   
        }
        return sb.toString() + " ]";
    }

        
}