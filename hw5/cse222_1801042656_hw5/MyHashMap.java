package cse222_1801042656_hw5;

import java.util.*;

public class MyHashMap<K,V> extends HashMap<K,V>{

    private static final long serialVersionUID = 1L;

    public MyHashMap(){
        super();
    }
    /**
     * Calling one parameter constructor.
     * @param key key to be begin for iterating.
     * @return new iterator.
     */
    public MapIterator MapIterator(K key){
        return new MapIterator(key);
    }

    /**
     * Calling zero parameter constructor
     * @return zero parameter constructor
     */
    public MapIterator MapIterator(){
        return new MapIterator();
    }

    // Inner class
    /**
     * I wrote MapIterator as inner class, if I wrote it in a different file, I couldn't call with 1 parameter.
    */
    public class MapIterator implements MapIteratorInterface<K,V>{
        private int currentIndex;
        private K currentKey;
        private K firstKey;
        private K lastKey;
        private ArrayList<Boolean> isChecked;

        /**
         * Constructor
         * @param key to begin to iterate.
         */
        public MapIterator(K key){
            isChecked = new ArrayList<Boolean>();
            Set<Entry<K,V>> entries = MyHashMap.this.entrySet();
            for( Map.Entry<K,V> entryOfMap : entries){
                MyHashMap.this.put(entryOfMap.getKey(), entryOfMap.getValue());
                lastKey = entryOfMap.getKey();
                isChecked.add(false);
            }
            findKeyIndex(key);
        }

        /**
         * Zero parameter constructor. I begin to iterate from last element if exists.
         */
        public MapIterator(){
            isChecked = new ArrayList<Boolean>();
            Set<Entry<K,V>> entries = MyHashMap.this.entrySet();
            for( Map.Entry<K,V> entryOfMap : entries){
                MyHashMap.this.put(entryOfMap.getKey(), entryOfMap.getValue());
                lastKey = entryOfMap.getKey();
                isChecked.add(false);
            }
            findKeyIndex(lastKey);
        }

        /**
         * Helper method to find key index(index is private and there is no get method)
         * Because if there was, it would break the rule for map.
         * @param key
         */
        @Override
        public void findKeyIndex(K key){
            currentIndex = 0;
            for(K tempKey : MyHashMap.this.keySet() ){
                currentKey = tempKey;
                if(currentIndex == 0)
                    firstKey = tempKey;
                if( tempKey.equals( key ) )
                    break;
                currentIndex++;
            }
            isChecked.set(currentIndex, true);
        }

        /**
         * @return next element key.
         * If there is not, returns first key.
         */
        @Override
        public K next(){
            if( hasNext() == false){ //if there is no more 
                currentIndex = 0; //getting to the first key again.
                isChecked.set(currentIndex, true);
                return firstKey;
            }

            boolean stopFlag = false;
            for(K tempKey : MyHashMap.this.keySet() ){
                if(stopFlag == true){
                    currentKey = tempKey;
                    currentIndex++;
                    isChecked.set(currentIndex, true);
                    return tempKey;
                }
                if( tempKey.equals(currentKey) ){
                    stopFlag = true;
                }
            }

            if(stopFlag == true){
                currentIndex = 0;
                isChecked.set(currentIndex, true);
                currentKey = firstKey;
                return firstKey;
            }

            throw new IllegalStateException();
        }

        /**
         * @return previous element. 
         * If there is not, returns last key.
         */
        @Override
        public K prev(){
            if(currentIndex == 0){
                currentIndex = MyHashMap.this.size();
                isChecked.set(currentIndex, true);
                currentKey = lastKey;
                return lastKey;
            }
            K prevKey = firstKey;
            for(K tempKey : MyHashMap.this.keySet() ){
                if(tempKey == currentKey){
                    --currentIndex;
                    isChecked.set(currentIndex, true);
                    currentKey = prevKey;
                    return prevKey;
                }
                prevKey = tempKey;
            }
            throw new IllegalStateException();
        }

        /**
         * Checking if all iterated.
         * @return true if all iterated, else false.
         * The method is helper and private.
         */
        private boolean isAllIterated(){
            for(int i=0; i<MyHashMap.this.size(); i++)
                if(isChecked.get(i) == false){
                    return false;
                }
            return true;
        }

        /**
         * @return If isAllIterated is false, returns true.
         * Else return false
         */
        @Override
        public boolean hasNext() {
            return !( isAllIterated() );
        }

    }

}