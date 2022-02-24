/**
 * @author Suleyman Golbol
 * Date: 2020
 */
package cse222_1801042656_hw4;

import java.util.*;

public class MyIterator<E> implements Iterator<E> {
    private int current;
    private boolean flag_illegalState = true;
    private ArrayList<E> collect;
    private E valueToSet;

    public MyIterator(ArrayList<E> obj){
        collect = null;
        collect = obj;
        current = 0; //Making current 0 again.
    }

    @Override
	public boolean hasNext() {
        if(current == collect.size())
            return false;
        else
            return true;
    }

    @Override
	public E next(){
        flag_illegalState = false;
        boolean flag = false;
        if(hasNext() == true){
            flag = true;
        }
        if(flag == false){
            flag_illegalState = true; //Because IllegalStateException only happens when next() is not called or last next() call.
            throw new NoSuchElementException();
        }
        valueToSet = (E)collect.get(current++);
        return valueToSet;
    }

    public void set(E value){
        if(flag_illegalState == true)
            throw new UnsupportedOperationException("Error! The next method should be used to use this.");
        collect.set(current-1, value);
    }

    @Override
    public void remove(){
        if(flag_illegalState == true)
            throw new IllegalStateException("Without using next() method you cannot remove with iterator! It is a rule of Java.");
    }

}