import java.util.*;
import cse222_1801042656_hw4.*;

/**
 * @author Suleyman Golbol
 * 1801042656
 */

public class Main{

    public static void main(String[] args){
        minHeap_part1();
        maxHeap();
        bst_test();
    }

    public static void minHeap_part1(){ 
        System.out.println("-------PART1--------");
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
        heap.add(4);      
        heap.add(2);
        heap.add(1);

        System.out.println("Heap1 is:");
        heap.display();

        System.out.print("Searching for element (Searching for 3 that isn't in array) : ");
        System.out.println( heap.search(3) );
        System.out.print("Searching for element (Searching for 2 that  is  in  array) : ");
        System.out.println( heap.search(2) );
        
        MyMinHeap<Integer> heap2 = new MyMinHeap<Integer>();
        heap2.add(8);
        heap2.add(6);
        heap2.add(5);
        System.out.println("Heap2 is:");
        heap2.display();
        MyIterator<Integer> iter = heap2.myIterator();
        System.out.println("\nTrying set method without next: ");
        try{
            iter.set(3);
        }catch(UnsupportedOperationException e){
            System.out.println("Exception caught. Please first use next() for iterator before setting.");
        }
        System.out.println("Trying set method with next: ");
        iter.next();
        iter.set(3);

        System.out.println("Heap2 is:");
        heap2.display();
        System.out.println("\nMerged Heap is:");
        heap.mergeWithAnotherHeap(heap2);
        heap.display();

        //Removing
        System.out.println("\nRemoving 5th largest element in heap: ");
        heap.removeIthLargestElement(5);
        heap.display();
        //Removing that is not in heap
        System.out.println("Removing 10th largest element in heap: (Heap doesn't have 10 elements so should give error)");
        heap.removeIthLargestElement(10);
        heap.display();
    }
    
    public static void maxHeap(){
        System.out.println("\nMAXHEAP (Will be used for BSTHeapTree)");
        MyMaxHeap<Integer> heap = new MyMaxHeap<Integer>();
        heap.add(10);      
        heap.add(30);
        heap.add(20);
        heap.add(400);
        heap.add(400);

        System.out.println("Heap1 is:");
        heap.display();

        //Removing
        System.out.println("Removing 400: ");
        heap.remove(400);
        heap.display();
        System.out.println("Removing 30: ");
        heap.remove(30);
        heap.display();
    }

    public static void bst_test(){
        System.out.println("\n--------PART2---------");
        //Creating bst and Random number.
        BSTHeapTree<Integer> bst = new BSTHeapTree<Integer>();
        int[] array = new int[3000];
        Random random = new Random();
        System.out.println("\nTest 1, Inserting 3000 elements and storing in array and BSTHeapTree.");
        int number;
        for(int i=0; i<3000; i++){ //Adding numbers to array and bst.
            number = random.nextInt(5000);
            bst.add(number);    
            array[i] = number;
        }
        Arrays.sort(array);  //Sorting array.
        // System.out.println( Arrays.toString(array) ); 
        // bst.display();


        //Test 2, searching for 100 numbers that in array.
        System.out.println("\nTest 2, searching for 100 numbers that in array:");
        boolean errorFlag = false;
        for(int i=0; i<100; i++){
            // Every time checking how many array[5*i] are there.
            int counter = 0;
            for(int j=0; j<3000; j++){
                if(array[5*i] == array[j])
                    counter++;
            }
            if(bst.find( array[5*i] ) != counter){ //It means that occurences are not correct because array and bst's freq not same.
                errorFlag = true;
                break;
            }
        }
        if(errorFlag == true) System.out.println("Error found! Occurences doesn't match.");
        else                  System.out.println("Error not found for Q3.1. Occurences are correct.");

        //Test 2, searching for 10 numbers that isn't in array.
        System.out.println("\nTest 2, searching for 10 numbers that is not in array.");
        errorFlag = false;
        for(int i=0; i<10; i++){
            int counter = 0;
            for(int j=0; j<3000; j++){
                if(i+5000 == array[j]){
                    counter++;
                }
            }
            if(bst.find(i+5000) == counter && counter != 0){ //i+5000 cannot be in bst so this means error found.
                errorFlag = true;
                break;
            }
        }
        if(errorFlag == true) System.out.println("Error found! Occurences for the numbers not in array match.");
        else                  System.out.println("Error not found for Q3.2. Occurences for the nubmers not in array and occurence is true.");


        //Test 3, finding mode and checking if true.
        System.out.println("\nTest 3, finding mode and checking if true.");
        int modeOccurence = bst.find_mode();
        int modeValue = bst.find_modeValue();
        if( bst.find(modeValue) == modeOccurence )
            System.out.println("Mode value is correct\n");
        else
            System.out.println("Mode value is not correct.\n");


        //Test 4, removing 100 numbers in the array.
        System.out.println("Test 4, removing 100 numbers in the array.");
        errorFlag = false;
        for(int i=0; i<100; i++){
            // Every time removing array[5*i].
            bst.remove( array[5*i] );
            int counter = 0;
            for(int j=0; j<3000; j++){
                if( array[5*i] == array[j] )
                    counter++;
            }
            // Counter is counting occurences from array, and find returns occurence from bst.
            // If removal is successful if condition shouldn't be successful.
            if(bst.find(array[5*i]) != counter-1){  //Counter's number have to 1 bigger because element removed from bst, not array.
                System.out.println("Error found so removal is not successful.");
                errorFlag = true;
                break;
            }
        }
        if(errorFlag == true)
            System.out.println("Error found for Q4.1. After deleting occurences are not correct.\n");
        else
            System.out.println("Error not found for Q4.1. After deleting occurences are correct.\n");


        //Test 4, removing 10 numbers that are not in array.
        System.out.println("Test 4, removing 10 numbers that aren't in the array.");
        errorFlag = false;
        for(int i=0; i<10; i++){
            // Every time removing 5000*i. Wont be removed becaouse there no such element.
            try{
                bst.remove( 5000+i );
            }catch(IndexOutOfBoundsException e){
                System.out.println("Exception caught. There are no such element for removing.");
            }
            int counter = 0;
            for(int j=0; j<3000; j++){
                if( 5000+i == array[j] )
                    counter++;
            }
            // Counter is counting occurences from array, and find returns occurence from bst.
            if(bst.find(i+5000) == counter-1 && counter != 0){
                errorFlag = true;
                break;
            }
        }
        if(errorFlag == true)
            System.out.println("Error found for Q4.2. All after deleting occurences are not correct.");
        else
            System.out.println("Error not found for Q4.2. All after, occurences are correct.");

    }


}