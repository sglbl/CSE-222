package cse222_1801042656_hw3;
import java.util.NoSuchElementException;

public class HybridList<E> {
    //private KWArrayList<E> arrayList;
    private KWLinkedList<KWArrayList<E>> linkedList;

    public static final int MAX_NUMBER = 5;

    public HybridList(){
        linkedList = new KWLinkedList<KWArrayList<E>>();
    }

    public HybridList(KWLinkedList<KWArrayList<E>> linkedList) {
        linkedList = new KWLinkedList<KWArrayList<E>>();
        this.linkedList = linkedList;
    }

    public KWLinkedList<KWArrayList<E>> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(KWLinkedList<KWArrayList<E>> linkedList) {
        this.linkedList = linkedList;
    }

    public void add(E e){
        int llSize = linkedList.size();
        if(llSize == 0) {
            linkedList.add( llSize , new KWArrayList<E>() );
            linkedList.get(0).add(e);
            return;
        }

        if(checkIsArrayListFull() != 0){ //arrayList is not full
            linkedList.get(llSize-1).add( e );
        }
        else{ //arraylist is full.
            linkedList.add( llSize , new KWArrayList<E>() );
            linkedList.get(llSize).add(e);
        }

    }
    
    /**
     * @return 0 if full, and size of array if not full.
     */
    public int checkIsArrayListFull(){
        int llSize = linkedList.size();
        if( linkedList.get(llSize-1).size() == MAX_NUMBER )
            return 0; //it means that this array list is full so create new one.
        else   
            return linkedList.get(llSize-1).size();
    }

    public void remove(int index){
        if(linkedList.size() == 0)
            throw new NoSuchElementException();
        int remaining = index % MAX_NUMBER;
        int quotient  = index / MAX_NUMBER;

        linkedList.get(quotient).remove(remaining); //Removing last element from arrayList.
        //Checking if last arrayList in linkedList is empty or not.
        if( linkedList.get(quotient).size() == 0 )
            /*linkedList.remove();*/ linkedList.listIterator( linkedList.size()-1 ).remove(); //doesn't matter which one to use.
    }

    /**
     * Finds index and removes with remove(int) method
     * @param element element to remove
     */
    public void remove(E element){
        int counter = 0;
        for(int i=0; i<linkedList.size(); i++)
            for(int j=0; j<linkedList.get(i).size(); j++){
                if(linkedList.get(i).get(j) == element){
                    remove(counter);
                    break;
                }
                else
                    counter++;
            }
    }

    public int size(){
        int counter = 0;
        for(int i=0; i<linkedList.size(); i++)
            for(int j=0; j<linkedList.get(i).size(); j++){
                counter++;
            }
        return counter;
    }

    public void clear(){
        linkedList.clear();
    }

    public E get(int index){
        int i, j, counter = 0;
        for(i=0; i<linkedList.size(); i++)
            for(j=0; j<linkedList.get(i).size(); j++){
                if(counter == index)
                    return linkedList.get(i).get(j);
                counter++;
            }
        //If element couldn't find.
        throw new NoSuchElementException();
    }

    /**
     * To print as string.
     */
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i=0; i<linkedList.size(); i++)
            for(int j=0; j<linkedList.get(i).size(); j++){
                if(i == linkedList.size() -1 && j == linkedList.get(i).size()-1)
                    stringBuilder.append(linkedList.get(i).get(j));    
                else if(j == linkedList.get(i).size()-1)
                    stringBuilder.append(linkedList.get(i).get(j) + ", ");    
                else
                    stringBuilder.append(linkedList.get(i).get(j) + ", ");
            }
        return stringBuilder.toString() + "]";
    }






}
