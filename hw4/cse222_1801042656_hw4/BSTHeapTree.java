package cse222_1801042656_hw4;

public class BSTHeapTree<E extends Comparable<E> > implements BSTHeapTreeInterface<E>{

    private static class Mode<E>{
        private E value;
        private int occurence;
    }

    private static class Node<E extends Comparable<E>> {
        private MyMaxHeap<E> heap;
        private Node<E> right = null, left = null;

        public Node(E item){
            heap = new MyMaxHeap<E>(item);
            right = null;
            left = null;
        }

        public int size(){
            return heap.size();
        }

    }//End of inner class.

    private Node<E> root;
    private Mode<E> mode;

    public BSTHeapTree(){
        root = null;
        mode = new Mode<E>();
    }

    /**
     * Finding occurence recursively
     * @param root tree's root will be changed to find the child that has item.
     * @param item item to be checked for occurence
     * @param rootFlag will be transher to occrence method.
     */
    public void occurenceRecursive(Node<E> root, E item, boolean rootFlag){
        if(root == null)    return;
        if( root.heap.search(item) != 0){
            occurence(root, item, rootFlag);
            return;
        }

        occurenceRecursive(root.left, item, rootFlag);
        occurenceRecursive(root.right, item, rootFlag);
    }

    /**
     * Finding occurence of root.
     * @param root node that contains item
     * @param item item to be checked for occurence
     * @param rootFlag if true, it means item is in first parent node so just increment the root value.
     */
    public void occurence(Node<E> root, E item, boolean rootFlag){
        //Incrementing size and checking if this is mode.
        int tempOcc = root.heap.incrementNumberOfOccurences(item);
        if(mode.occurence < tempOcc && rootFlag == false){
            mode.occurence = tempOcc;
            mode.value = item;
        }
        else if(rootFlag == true){
            mode.occurence = root.heap.incrementRootOccurence();
            mode.value = root.heap.getRootValue();
        }
    }

    /**
     * Adding item to root
     * @param item
     * @return occurence of item.
     */
    @Override
    public int add(E item){
        root = add(root,item);
        return find(item);
    }

    /**
     * Adding item to root recursively
     * @param root it will change to find node that contains element
     * @param item item to be add.
     * @return node just for recursive saving.
     */
    public Node<E> add(Node<E> root, E item){

        if(root == null){
            root = new Node<E>( item );
            return root;
        }

        if(find(item) != 0){    //if element found increase occurence
            occurenceRecursive(root, item, false); //false means that this is not root occurence.
            return root;
        }

        if(root != null && root.heap.size() != 7){
            root.heap.add(item);
            return root;
        }
        
        //if root's depth is full create new nodes in bst.
        if(root.heap.size() == 7 && root.left == null && item.compareTo( root.heap.getRootValue() ) < 0){
            root.left  = new Node<E>(item);
            return root;
        }
        else if(root.heap.size() == 7 && root.right == null && item.compareTo( root.heap.getRootValue() ) > 0){
            root.right = new Node<E>(item);
            return root;
        }

        if(item.compareTo( root.heap.getRootValue() ) < 0){
            if(root.heap.search(item) != 0){
                occurenceRecursive(root, item, false);
                return root;
            }
            root.left = add(root.left, item);
        }
        else if( item.compareTo( root.heap.getRootValue() ) > 0)
            root.right = add(root.right, item);
        else{ //if element to be add equals to element in heap, just increment the occrence.
            //occurence(root, item, true); //true means that this is root element occurence
            if(root.heap.search(item) != 0){
                occurenceRecursive(root, item, false);
                return root;
            }
            return root;
        }

        return root;
    }

    /**
     * Removing
     * @param item to be removed
     * @return new occurence value.
     */
    @Override
    public int remove(E item){
        remove(root, item);
        return root.heap.search(item);
    }

    /**
     * Root to be 
     * @param root it will change to find node that contains element.
     * @param item item to be removed.
     * @return node just for recursive saving.
     */
     private Node<E> remove(Node<E> root, E item){
        if(root == null ) //If element couldn't find just return 0.
            return root;
        
        if(item.compareTo( root.heap.getRootValue() ) < 0){ //if in left side
            if(root.heap.search(item) != 0){
                root.heap.remove(item); //cc
                return root;
            }
            else if(root.left != null){
                root.left = remove(root.left, item);
                return root;
            }
        }
        else if(root.right != null && item.compareTo( root.heap.getRootValue() ) > 0){ //if in right side
            root.right = remove(root.right, item);
            return root;
        }
        else{ //then it means that our item is in root node.
            root.heap.remove(item);
            return root;
        }

        return root;
    }

    /**
     * Most frequently added element
     * @return occurence of mode element
     */
    @Override
    public int find_mode(){
        System.out.println("Mode value is " + mode.value + " and occurence of that value is "+ mode.occurence);
        return mode.occurence;
    }

    /**
     * Most frequently added element
     * @return value of mode element.
     */
    @Override
    public E find_modeValue(){
        return mode.value;
    }

    /**
     * Finding item's occurence
     * @param item item to be checked for occurence
     * @return occurence
     */
    @Override
    public int find(E item){
        return find(root, item);
    }

    /**
     * Finding occurence recursively //YOU SHOULD HAVE COMPARED...
     * @param root root will be changed to find the node that contains item
     * @param item item to check
     * @return occurence value of item
     */
    private int find(Node<E> root, E item){
        if(root == null)    return 0;
        if( root.heap.search(item) != 0){
            return root.heap.search(item);
        }
        return find(root.left, item) + find(root.right, item);
    }
    
    /**
     * Displaying heap
     */
    @Override
    public void display(){
        display(root);
        System.out.println();
    }

    /**
     * Displaying heap recursively
     * @param item to be changed to find the other tree nodes to print.
     */
    public void display(Node<E> item){
        if(item != null){
            display(item.left);
            display(item.heap);
            display(item.right);
        }
    }

    /**
     * Displaying max heap (node) that contains 7 element max.
     * @param heap heap node to be display.
     */
    @Override
    public void display(MyMaxHeap<E> heap){
        heap.displayWithoutNewLine();
    }
    /**
     * @return size
     */
    @Override
    public int size(){
        return size(root);
    }

    /**
     * 
     * @param item will be changed to find other tree nodes to check for their size
     * @return all the nodes in tree's size sum.
     */
    private int size(Node<E> item){
        if(item == null)    return 0;
        return item.size() + size(item.left) + size(item.right) + 1;    
    }



}