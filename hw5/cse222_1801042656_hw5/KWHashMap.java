package cse222_1801042656_hw5;

public interface KWHashMap<K, V> {

    /**
     * @return  the value associated with the specified  key . Returns null if the key is not present
     */
    V get(Object key);

    /**
     * @return true if this table contains no  key‐value mappings
     */
    boolean isEmpty();

    /**
     *  Associates the with the specified  key and value
     * @param key specified  key
     * @param value specified  value 
     * @return Returns the previous value, else null.
     */ 
    V put(K key, V value);

    /**
     *  Removes the mapping for this  key from this table if it is present
     * @param key
     * @return Returns the previous  value associated with the specified  key , or null if there was no mapping.
     */
    V remove(Object key);

    /**
     * @return Returns the size of the table
     */
    int size();

}
