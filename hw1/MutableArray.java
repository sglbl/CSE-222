@SuppressWarnings("unchecked") //This is for not showing unchecked type convension between Object and E.

public class MutableArray<E> {
    private E[] obj; //Simple E array. 
    
    /**
     * @param index index of array
     * @return element in that index
     */
    public E getIndex(int index){
        try{
            return obj[index];
        }
        catch(IndexOutOfBoundsException e){
            System.err.println("Index " + index + " is not in range. Try again..");
            return obj[0];
        }
    }

    /**
     * Adds a new element to array.
     * @param e element to add
     * @return true if successful
     */
    public boolean add(E e){
        int i;
        if( size() == 0 ){
            obj = (E[])new Object[size()+1];
            obj[0] = e;
            return true;
        }
        for(E i2: obj){  //If there is same element don't add it.
            if(e == i2)
                return false;
        }

        E[] temp = (E[])new Object[size() + 1]; //Copying to temp and adding e.
        i=0;
        while( i++ < size() )
            temp[i-1] = obj[i-1];
        temp[size()] = e;

        int size = size();

        obj = (E[])new Object[size + 1];
        i=0;
        while( i++ < size() )
            obj[i-1] = temp[i-1];
        
        return true;
    }

    /**
     * Removes e element
     * @param e element
     * @return true if successful
     */
    public boolean remove(E e){
        int size = size();
        int i_temp = -1;

        for(int i=0; i<size; i++)
            if(obj[i] == e)
                i_temp = i;
        if(i_temp == -1)    //That means couldn't find e, so return false.
            return false; 

        Object[] temp = new Object[size()];
        for(int i=0; i<size; i++)
            temp[i] = obj[i];
        clear();    //Clearing object
        for(int i=0; i<size; i++){  //Adding without the deleted one.
            if(i == i_temp) 
                continue;
            add( (E) temp[i] );
        }

        return true;
    }

    /**
     * Removes element in that index
     * @param index index
     * @return true if successfully removed.
     */
    public boolean remove(int index){
        int size = size();
        Object[] temp = new Object[size()];
        try{
            for(int i=0; i<size; i++)
                temp[i] = obj[i];
            clear();
            for(int i=0; i<size; i++){
                if(i==index) continue;
                add( (E) temp[i] );
            }
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Size of array
     * @return size of array.
     * catch returns 0 so that means array is empty.
     */
    public int size() {
        try{
            return obj.length;
        }
        catch(NullPointerException e){  //If null(empty)
            return 0;
        }
    }

    /**
     * Inside of Array
     * @return array elements
     */
    @Override
    public String toString(){
        String thisCol = "[ ";
        int i=0;
        while(i < size() ){
            if(i != size()-1) thisCol += obj[i] + ", ";
            else              thisCol += obj[i] + " ";
            i++;
        }
    
        return thisCol + "]";
    }

    /**
     * If array contains that e element.
     * @param e element to look.
     * @return true if found
     */
    public boolean contains(E e) {
        int size = size();
        int i=0;
        while( i++ < size ){
            if(e == obj[i-1])   
                return true;
        }
        return false;
    }

    /**
     * Clears array
     */
    public void clear(){
        obj = null;
    }

    /**
     * @return true if array is empty.
     */
    public boolean isEmpty() {
        if(size() == 0)
            return true;
        return false;
    }





}
