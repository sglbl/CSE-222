package cse222_1801042656_hw5;

public interface MapIteratorInterface<K,V> {

        /**
         * Helper method to find key index(index is private and there is no get method)
         * Because if there was, it would break the rule for map.
         * @param key
         */
        public void findKeyIndex(K key);

        /**
         * @return next element key.
         * If there is not, returns first key.
         */
        public K next();

        /**
         * @return previous element. 
         * If there is not, returns last key.
         */
        public K prev();

        /**
         * @return If isAllIterated is false, returns true.
         * Else return false
         */
        public boolean hasNext();

}