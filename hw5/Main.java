import cse222_1801042656_hw5.*;

/**
 * @author Suleyman Golbol
 * 1801042656
 */

public class Main{
    public static void main(String[] args){
        System.out.println("--------------PART1--------------");
        iterating_part1();

        System.out.println("\n---------------PART2--------------");
        part2_linkedlist();
        part2_treeset();
        part2_coalesced();
    }

    /**
     * Testing part1 with creating iterator and using prev,next and hasNext methods.
     */
    public static void iterating_part1(){ 
        MyHashMap<Integer, String> myMap = new MyHashMap<Integer, String>();
        
        myMap.put(1801042656, "Suleyman Golbol");
        myMap.put(1801042601, "Baran Solmaz");
        myMap.put(1801042634, "Muhammed Oguz");
        myMap.put(1801042647, "Kadir Saglam");
        
        System.out.println("Map<Integer,String> is:");
        System.out.println( myMap.toString() );

        //Creating iterator with MapIterator that begins with 1801042656 key.
        System.out.println("USING ONE PARAMETER CONSTRUCTOR:");
        MyHashMap<Integer, String>.MapIterator iter = myMap.MapIterator(1801042656);
        System.out.println("USING PREV() METHOD:");
        System.out.println( iter.prev() );
        System.out.println( iter.prev() );
        System.out.println( myMap.get(1801042634) );
        System.out.println( myMap.get(1801042656) );

        System.out.println("USING HASNEXT() AND NEXT() METHOD:");
        while( iter.hasNext() == true){
            System.out.println( iter.next() );
        }
        System.out.println("Printing myMap:");
        System.out.println( myMap.toString() );

        System.out.println("USING ZERO PARAMETER CONSTRUCTOR:");
        MyHashMap<Integer, String>.MapIterator iter2 = myMap.MapIterator();
        System.out.println( iter2.next() );

        System.out.println("USING CONCTRUCTOR IN EMPTY MAP(TRY CATCH TO CATCH THE THROWED EXCEPTION):");
        try{
            MyHashMap<Integer, String> emptyMap = new MyHashMap<Integer, String>();
            MyHashMap<Integer, String>.MapIterator iter3 = emptyMap.MapIterator();
        }catch(IndexOutOfBoundsException i){
            System.out.println("IndexOutOfBoundsException caught. Please first add elements.\n");
        }


    }

    public static void part2_linkedlist(){
        System.out.println("-------LINKEDLIST TEST--------");
        HashTableChainwithLinkedList<Integer, String> table_ll = new HashTableChainwithLinkedList<Integer,String>();
       
        System.out.println("-----FOR SMALL DATA-----");

        System.out.println("---ADDING ELEMENTS---");
        long nano_start = System.nanoTime();

        for(int i=0; i<10; i++)
            table_ll.put(i, "value" + i );

        long nano_end = System.nanoTime();
        System.out.println( table_ll.toString() );      

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll.get(8) );
        System.out.println( table_ll.get(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll.get(15) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll.remove(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll.remove(15) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll.get(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ll_2 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR MEDIUM DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();
        for(int i=0; i<500; i++)
            table_ll_2.put(i, "value" + i );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_2.get(243) );
        System.out.println( table_ll_2.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_2.get(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_2.remove(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );  

        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_2.remove(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_2.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ll_3 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR LARGE DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();
        for(int i=0; i<10000; i++)
            table_ll_3.put(i, "value" + i );
        nano_end = System.nanoTime();

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_3.get(8702) );
        System.out.println( table_ll_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_3.get(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_3.remove(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_3.remove(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ll_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


    }

    public static void part2_treeset(){
        System.out.println("\n-------TREESET TEST--------");
        HashTableChainwithTreeSet<Integer, String>    table_ts = new HashTableChainwithTreeSet<Integer,String>();
        System.out.println("-----FOR SMALL DATA-----");

        System.out.println("---ADDING ELEMENTS---");
        long nano_start = System.nanoTime();

        for(int i=0; i<10; i++)
            table_ts.put(i, "value" + i );

        long nano_end = System.nanoTime();
        System.out.println( table_ts.toString() );  

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.get(3) );
        System.out.println( table_ts.get(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.get(15) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.remove(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.remove(15) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.get(5) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ts_2 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR MEDIUM DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();
        for(int i=0; i<500; i++)
            table_ts_2.put(i, "value" + i );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_2.get(211) );
        System.out.println( table_ts_2.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_2.get(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_2.remove(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_2.remove(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ts_3 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR LARGE DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();
        for(int i=0; i<10000; i++)
            table_ts_3.put(i, "value" + i );
        nano_end = System.nanoTime();

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_3.get(5456) );
        System.out.println( table_ts_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_3.get(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_3.remove(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_3.remove(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ts_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


    }

    public static void part2_coalesced(){
        System.out.println("\n-------HASHTABLE COALESCED HASHING CLASS TEST--------");
        HashTableCoalescedHashing<Integer, String>    table_ch = new HashTableCoalescedHashing<Integer,String>();

        System.out.println("-----FOR SMALL DATA-----");

        System.out.println("---ADDING ELEMENTS---");
        long nano_start = System.nanoTime();
        table_ch.put(3, "valueOf3");
        table_ch.put(12, "valueOf12");
        table_ch.put(13, "valueOf13");
        table_ch.put(25, "valueOf25");
        table_ch.put(23, "valueOf23");
        table_ch.put(51, "valueOf51");
        table_ch.put(42, "valueOf42");
        long nano_end = System.nanoTime();

        System.out.println("---PRINTINGG ELEMENTS---\n(Note: If next=-1 it means null)");
        System.out.println( table_ch.toString() );

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch.get(12) );
        System.out.println( table_ch.get(13) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch.get(49) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA: Key13)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch.remove(13) );
        System.out.println( table_ch.toString() );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch.remove(49) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch.get(13) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );


        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ch_2 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR MEDIUM DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();

        for(int i=0; i<500; i++)
            table_ch_2.put(i, "value" + i );
        nano_end = System.nanoTime();

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_2.get(211) );
        System.out.println( table_ch_2.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_2.get(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA: Key280)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_2.remove(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_2.remove(650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_2.get(280) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        //###################################################################
        HashTableChainwithLinkedList<Integer, String> table_ch_3 = new HashTableChainwithLinkedList<Integer,String>();
        System.out.println("\n-----FOR LARGE DATA-----");

        System.out.println("---ADDING ELEMENTS---");

        nano_start = System.nanoTime();
        for(int i=0; i<10000; i++)
            table_ch_3.put(i, "value" + i );
        nano_end = System.nanoTime();

        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_3.get(8764) );
        System.out.println( table_ch_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---GETTING ELEMENTS(NON-EXISTING KEY)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_3.get(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

        System.out.println("---REMOVING ELEMENT(EXISTING DATA: Key1402)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_3.remove(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---TRYING REMOVE ELEMENT(NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_3.remove(15650) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );
        
        System.out.println("---SEARCHING FOR REMOVED ELEMENT(SO NON-EXISTING DATA)---");
        nano_start = System.nanoTime();
        System.out.println( table_ch_3.get(1402) );
        nano_end = System.nanoTime();
        System.out.println("Performance time-> " + (nano_end-nano_start) + " nanosconds." );

    }
    

}