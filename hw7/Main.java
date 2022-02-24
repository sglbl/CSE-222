import java.util.*;
import cse222_1801042656_hw7.*;
import cse222_kwbook_implementations.*;

/**
 * @author Suleyman Golbol 1801042656
 * Driver class for homework.
 */

public class Main{

    public static void main(String[] args){
        testPart1_skipList();
        testPart1_avlTree();
        testPart2();
        testPart3Structures();
    }
    
    public static void testPart1_skipList(){
        System.out.println("---Testing Skip List---");
        SkipListWithNavigableSet<Integer> skipList = new SkipListWithNavigableSet<Integer>();
        System.out.println("---Adding elements---");
        for(int i = 0; i < 100; i++){
			skipList.insert(i);
		}
        
        System.out.println("---Checking if an element is added---");
        System.out.println( skipList.contains(3) );
        
        System.out.println("---Removing some elements---");
		for(int i = 99; i > 5; i--){
			skipList.delete(i);
		}

        System.out.println("---Checking two elements if successfully removed---");
        System.out.println( skipList.contains(55) );
        System.out.println( skipList.contains(25) );

        System.out.println("---Iterating through skip list in descending order and printing: ---");
        Iterator<Integer> iter = skipList.descendingIterator();
        while(iter.hasNext())
            System.out.print( iter.next() + ", " );
        System.out.println();
    }

    public static void testPart1_avlTree() {
        System.out.println("---Testing Avl Tree---");
        AVLTreeWithNavigableSet<Integer> avlTree = new AVLTreeWithNavigableSet<Integer>();
        for(int i = 0; i < 100; i++){
			avlTree.insert(i);
		}
        
        System.out.println("---Checking if an element is added successfully---");
        System.out.println( avlTree.contains(3) );

        System.out.println("---Checking an element that doesn't exist in avl tree.---");
        System.out.println( avlTree.contains(59142) );

        System.out.println("---Iterating through AVLTREE and printing: ---");
        Iterator<Integer> iter = avlTree.iterator();
        while(iter.hasNext())
            System.out.print(iter.next() + ",");
        System.out.println("");

        System.out.println("---Using HeadSet and Printing---");
        SortedSet<Integer> avlTreeWithHeadSet = new AVLTreeWithNavigableSet<>();
        avlTreeWithHeadSet = avlTree.headSet(4, true);
        System.out.println( avlTreeWithHeadSet.toString() );

        System.out.println("---Using TailSet and Printing---");
        SortedSet<Integer> avlTreeWithTailSet = new AVLTreeWithNavigableSet<>();
        avlTreeWithTailSet = avlTree.tailSet(96, true);
        System.out.println( avlTreeWithTailSet.toString() );

    }

    
    public static void testPart2(){
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.add(15); avlTree.add(45); avlTree.add(12);

        RedBlackTree<Integer> redBlack = new RedBlackTree<Integer>();
        redBlack.add(15); redBlack.add(45); redBlack.add(12);
        redBlack.add(65); redBlack.add(96); redBlack.add(98);
        //System.out.println(redBlack);
        System.out.println("---TESTING RED-BLACK TREE---");
        System.out.println( whatTreeIsIt(redBlack) );

        //System.out.println(avlTree);
        System.out.println("---TESTING AVL TREE---");
        System.out.println( whatTreeIsIt(avlTree) );

    }
    
    /**
     * Checks tree type for part2 if red-black or avl
     * @param treeToCheck tree to check
     * @return results of checking as string 
     */
    public static String whatTreeIsIt(BinarySearchTree<Integer> treeToCheck){
        if( treeToCheck.whatTreeIsIt() == 1 )
            return "It is a red-black tree.";
        else if( treeToCheck.whatTreeIsIt() == -1 || treeToCheck.isTreeIsBalanced() == true )
            return "It is a AVL tree.";
        else // == 0 which means it is not a red-black or avl tree.
            return "It is not AVL tree nor red-black tree.";
    }

    /**
     * Checking to not adding same element again.
     * @param collect collection to check if contains that element
     * @param toCheck checking if this element exists
     * @return true if contains
     */
    public static boolean checkIsThereSame(RedBlackTree<Integer> collect, int toCheck){
        return collect.contains(toCheck);
    }
    
    /**
     * Testing data structures from book.
     */
    public static void testPart3Structures(){
        Random random = new Random();

        System.out.println("---TESTING BST, RBT, 2-3 TREE, B TREE AND SKIP LIST---");

        int randomNum, randomNum2;
        long nano_start = System.currentTimeMillis();
        long nano_end = System.currentTimeMillis();
        // To calculate average running time.
        float bstTimeAverage=0, rbtTimeAverage=0, ttTreeTimeAverage=0, bTreeTimeAverage=0, skipListTimeAverage=0;
        float bstTimeAverage2=0, rbtTimeAverage2=0, ttTreeTimeAverage2=0, bTreeTimeAverage2=0, skipListTimeAverage2=0;
        float bstTimeAverage3=0, rbtTimeAverage3=0, ttTreeTimeAverage3=0, bTreeTimeAverage3=0, skipListTimeAverage3=0;
        float bstTimeAverage4=0, rbtTimeAverage4=0, ttTreeTimeAverage4=0, bTreeTimeAverage4=0, skipListTimeAverage4=0;
        // To calculate average running time for inserting 100 extra.
        float bstTimeInsertAverage=0, rbtTimeInsertAverage=0, ttTreeTimeInsertAverage=0, bTreeTimeInsertAverage=0, skipListTimeInsertAverage=0;
        float bstTimeInsertAverage2=0, rbtTimeInsertAverage2=0, ttTreeTimeInsertAverage2=0, bTreeTimeInsertAverage2=0, skipListTimeInsertAverage2=0;
        float bstTimeInsertAverage3=0, rbtTimeInsertAverage3=0, ttTreeTimeInsertAverage3=0, bTreeTimeInsertAverage3=0, skipListTimeInsertAverage3=0;
        float bstTimeInsertAverage4=0, rbtTimeInsertAverage4=0, ttTreeTimeInsertAverage4=0, bTreeTimeInsertAverage4=0, skipListTimeInsertAverage4=0;

        for(int i=0; i<10; i++){    
            // To calculate running time for each
            int bstTime=0, rbtTime=0, ttTreeTime=0, bTreeTime=0, skipListTime=0;
            int bstTime2=0, rbtTime2=0, ttTreeTime2=0, bTreeTime2=0, skipListTime2=0;
            int bstTime3=0, rbtTime3=0, ttTreeTime3=0, bTreeTime3=0, skipListTime3=0;
            int bstTime4=0, rbtTime4=0, ttTreeTime4=0, bTreeTime4=0, skipListTime4=0;
            // To calculate running time for inserting 100 extra number
            int bstTimeInsert=0, rbtTimeInsert=0, ttTreeTimeInsert=0, bTreeTimeInsert=0, skipListInsert=0;
            int bstTimeInsert2=0, rbtTimeInsert2=0, ttTreeTimeInsert2=0, bTreeTimeInsert2=0, skipListInsert2=0;
            int bstTimeInsert3=0, rbtTimeInsert3=0, ttTreeTimeInsert3=0, bTreeTimeInsert3=0, skipListInsert3=0;
            int bstTimeInsert4=0, rbtTimeInsert4=0, ttTreeTimeInsert4=0, bTreeTimeInsert4=0, skipListInsert4=0;
            /***************************** FOR 10K ITEMS  *******************************************/
            BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
            RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
            BTree<Integer> ttTree = new BTree<Integer>(3); //Using btree with 3 order makes it 2-3 tree.
            BTree<Integer> bTree = new BTree<Integer>(4);
            SkipList<Integer> skipList = new SkipList<Integer>();

            int j;
            for(j=0; j<10000; j++){
                
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum) == true);

                nano_start = System.currentTimeMillis();
                bst.add(randomNum);
                nano_end = System.currentTimeMillis();
                bstTime += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                rbt.add(randomNum);
                nano_end = System.currentTimeMillis();
                rbtTime += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                ttTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                ttTreeTime += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                bTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                bTreeTime += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                skipList.add(randomNum);
                nano_end = System.currentTimeMillis();
                skipListTime += (nano_end-nano_start);       
                
            }
            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bst.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bstTimeInsert += (nano_end-nano_start);
            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                rbt.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            rbtTimeInsert += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                ttTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            ttTreeTimeInsert += (nano_end-nano_start);

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bTreeTimeInsert += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                skipList.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            skipListInsert += (nano_end-nano_start);


        /***************************** FOR 20K ITEMS  *******************************************/
            bst = null; bst = new BinarySearchTree<Integer>();
            rbt = new RedBlackTree<Integer>();
            ttTree = new BTree<Integer>(3); //Using btree with 3 order makes it 2-3 tree.
            bTree = new BTree<Integer>(4);
            skipList = new SkipList<Integer>();
            for(j=0; j<20000; j++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum) == true);

                nano_start = System.currentTimeMillis();
                bst.add(randomNum);
                nano_end = System.currentTimeMillis();
                bstTime2 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                rbt.add(randomNum);
                nano_end = System.currentTimeMillis();
                rbtTime2 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                ttTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                ttTreeTime2 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                bTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                bTreeTime2 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                skipList.add(randomNum);
                nano_end = System.currentTimeMillis();
                skipListTime2 += (nano_end-nano_start);              
            }

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bst.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bstTimeInsert2 += (nano_end-nano_start);
            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                rbt.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            rbtTimeInsert2 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                ttTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            ttTreeTimeInsert2 += (nano_end-nano_start);

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bTreeTimeInsert2 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                skipList.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            skipListInsert2 += (nano_end-nano_start);
   
        /***************************** FOR 40K ITEMS  *******************************************/
        bst = null; bst = new BinarySearchTree<Integer>();
            rbt = new RedBlackTree<Integer>();
            ttTree = new BTree<Integer>(3); //Using btree with 3 order makes it 2-3 tree.
            bTree = new BTree<Integer>(4);
            skipList = new SkipList<Integer>();
            
            for(j=0; j<40000; j++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum) == true);

                nano_start = System.currentTimeMillis();
                bst.add(randomNum);
                nano_end = System.currentTimeMillis();
                bstTime3 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                rbt.add(randomNum);
                nano_end = System.currentTimeMillis();
                rbtTime3 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                ttTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                ttTreeTime3 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                bTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                bTreeTime3 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                skipList.add(randomNum);
                nano_end = System.currentTimeMillis();
                skipListTime3 += (nano_end-nano_start);              
            }

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bst.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bstTimeInsert3 += (nano_end-nano_start);
            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                rbt.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            rbtTimeInsert3 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                ttTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            ttTreeTimeInsert3 += (nano_end-nano_start);

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bTreeTimeInsert3 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                skipList.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            skipListInsert3 += (nano_end-nano_start);
            
        /********************************* FOR 80K ITEMS  *******************************************/
            bst = null; bst = new BinarySearchTree<Integer>();
            rbt = new RedBlackTree<Integer>();
            ttTree = new BTree<Integer>(3); //Using btree with 3 order makes it 2-3 tree.
            bTree = new BTree<Integer>(4);
            skipList = new SkipList<Integer>();
            for(j=0; j<80000; j++){   
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum) == true);

                nano_start = System.currentTimeMillis();
                bst.add(randomNum);
                nano_end = System.currentTimeMillis();
                bstTime4 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                rbt.add(randomNum);
                nano_end = System.currentTimeMillis();
                rbtTime4 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                ttTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                ttTreeTime4 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                bTree.add(randomNum);
                nano_end = System.currentTimeMillis();
                bTreeTime4 += (nano_end-nano_start);

                nano_start = System.currentTimeMillis();
                skipList.add(randomNum);
                nano_end = System.currentTimeMillis();
                skipListTime4 += (nano_end-nano_start);              
            }

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bst.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bstTimeInsert4 += (nano_end-nano_start);
            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                rbt.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            rbtTimeInsert4 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                ttTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            ttTreeTimeInsert4 += (nano_end-nano_start);

            
            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                bTree.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            bTreeTimeInsert4 += (nano_end-nano_start);

            nano_start = System.currentTimeMillis();
            for(int k=0; k<100; k++){
                do{ //Until finding non-repeating number, generate another random number.
                    randomNum2 = random.nextInt(1000000);
                }while(checkIsThereSame(rbt, randomNum2) == true);
                skipList.add(randomNum2);
            }
            nano_end = System.currentTimeMillis();
            skipListInsert4 += (nano_end-nano_start);

            
            System.out.println("\nTest" + (i+1) + " with milliSeconds:" );
            System.out.println( "For 10k element | Bst : " + bstTime  + ", Rbt : " + rbtTime  + ", ttTree : " + ttTreeTime +  ", bTree : " + bTreeTime  + ", skiplist : " + skipListTime);
            System.out.println( "Insert 100 elements to Bst : " + bstTimeInsert  + ", Insert to Rbt : " + rbtTimeInsert  + ", Insert to ttTree : " + ttTreeTimeInsert +  ", Insert to bTree : " + bTreeTimeInsert  + ", Insert to skiplist : " + skipListInsert);
            System.out.println( "For 20k element | Bst2: " + bstTime2 + ", Rbt2: " + rbtTime2 + ", ttTree2: " + ttTreeTime2 + ", bTree2: " + bTreeTime2 + ", skiplist2: " + skipListTime2);
            System.out.println( "Insert 100 elements to Bst : " + bstTimeInsert2  + ", Insert to Rbt : " + rbtTimeInsert2  + ", Insert to ttTree : " + ttTreeTimeInsert2 +  ", Insert to bTree : " + bTreeTimeInsert2  + ", Insert to skiplist : " + skipListInsert2);
            System.out.println( "For 40k element | Bst3: " + bstTime3 + ", Rbt3: " + rbtTime3 + ", ttTree3: " + ttTreeTime3 + ", bTree3: " + bTreeTime3 + ", skiplist3: " + skipListTime3);
            System.out.println( "Insert 100 elements to Bst : " + bstTimeInsert3  + ", Insert to Rbt : " + rbtTimeInsert3  + ", Insert to ttTree : " + ttTreeTimeInsert3 +  ", Insert to bTree : " + bTreeTimeInsert3  + ", Insert to skiplist : " + skipListInsert3);
            System.out.println( "For 80k element | Bst4: " + bstTime4 + ", Rbt4: " + rbtTime4 + ", ttTree4: " + ttTreeTime4 + ", bTree4: " + bTreeTime4 + ", skiplist4: " + skipListTime4);
            System.out.println( "Insert 100 elements to Bst : " + bstTimeInsert4  + ", Insert to Rbt : " + rbtTimeInsert4  + ", Insert to ttTree : " + ttTreeTimeInsert4 +  ", Insert to bTree : " + bTreeTimeInsert4  + ", Insert to skiplist : " + skipListInsert4);

            bstTimeAverage += bstTime;              bstTimeAverage2 += bstTime2;             bstTimeAverage3 += bstTime3;               bstTimeAverage4 += bstTime4;
            bstTimeInsertAverage += bstTimeInsert;  bstTimeInsertAverage2 += bstTimeInsert2; bstTimeInsertAverage3 += bstTimeInsert3;   bstTimeInsertAverage4 += bstTimeInsert4; 

            rbtTimeAverage += rbtTime;              rbtTimeAverage2 += rbtTime2;             rbtTimeAverage3 += rbtTime3;               rbtTimeAverage4 += rbtTime4;
            rbtTimeInsertAverage += rbtTimeInsert;  rbtTimeInsertAverage2 += rbtTimeInsert2; rbtTimeInsertAverage3 += bstTimeInsert3;   rbtTimeInsertAverage4 += rbtTimeInsert4; 

            ttTreeTimeAverage += ttTreeTime;              ttTreeTimeAverage2 += ttTreeTime2;             ttTreeTimeAverage3 += ttTreeTime3;            ttTreeTimeAverage4 += ttTreeTime4;
            ttTreeTimeInsertAverage += ttTreeTimeInsert;  ttTreeTimeInsertAverage2 += ttTreeTimeInsert2; ttTreeTimeInsertAverage3 += bstTimeInsert3;   ttTreeTimeInsertAverage4 += ttTreeTimeInsert4; 
            
            bTreeTimeAverage += bTreeTime;              bTreeTimeAverage2 += bTreeTime2;             bTreeTimeAverage3 += bTreeTime3;            bTreeTimeAverage4 += bTreeTime4;
            bTreeTimeInsertAverage += bTreeTimeInsert;  bTreeTimeInsertAverage2 += bTreeTimeInsert2; bTreeTimeInsertAverage3 += bstTimeInsert3;   bTreeTimeInsertAverage4 += bTreeTimeInsert4; 
            
            skipListTimeAverage += skipListTime;          skipListTimeAverage2 += skipListTime2;         skipListTimeAverage3 += skipListTime3;          skipListTimeAverage4 += skipListTime4;
            skipListTimeInsertAverage += skipListInsert;  skipListTimeInsertAverage2 += skipListInsert2; skipListTimeInsertAverage3 += bstTimeInsert3;   skipListTimeInsertAverage4 += skipListInsert4;            
                
        } //End of loop for repeating for 10 times.

        //Finding average after ten times for everything.
        bstTimeAverage /= 10;              bstTimeAverage2 /= 10;             bstTimeAverage3 /= 10;               bstTimeAverage4 /= 10;
        bstTimeInsertAverage /= 10;  bstTimeInsertAverage2 /= 10; bstTimeInsertAverage3 /= 10;   bstTimeInsertAverage4 /= 10;

        rbtTimeAverage /= 10;              rbtTimeAverage2 /= 10;             rbtTimeAverage3 /= 10;               rbtTimeAverage4 /= 10;
        rbtTimeInsertAverage /= 10;  rbtTimeInsertAverage2 /= 10; rbtTimeInsertAverage3 /= 10;   rbtTimeInsertAverage4 /= 10;

        ttTreeTimeAverage /= 10;            ttTreeTimeAverage2 /= 10;       ttTreeTimeAverage3 /= 10;          ttTreeTimeAverage4 /= 10;
        ttTreeTimeInsertAverage /= 10;      ttTreeTimeInsertAverage2 /= 10; ttTreeTimeInsertAverage3 /= 10;    ttTreeTimeInsertAverage4 /= 10; 
        
        bTreeTimeAverage /= 10;              bTreeTimeAverage2 /= 10; bTreeTimeAverage3 /= 10;         bTreeTimeAverage4 /= 10;
        bTreeTimeInsertAverage /= 10;  bTreeTimeInsertAverage2 /= 10; bTreeTimeInsertAverage3 /= 10;   bTreeTimeInsertAverage4 /= 10;
        
        skipListTimeAverage /= 10;       skipListTimeAverage2 /= 10;        skipListTimeAverage3 /= 10;          skipListTimeAverage4 /= 10;
        skipListTimeInsertAverage /= 10; skipListTimeInsertAverage2 /= 10;  skipListTimeInsertAverage3 /= 10;    skipListTimeInsertAverage4 /= 10;           

        //Acerage results.
        System.out.println("\n---AVERAGE RESULTS:---");
        System.out.println( "For 10k element | Bst : " + bstTimeAverage  + ", Rbt : " + rbtTimeAverage  + ", ttTree : " + ttTreeTimeAverage +  ", bTree : " + bTreeTimeAverage  + ", skiplist : " + skipListTimeAverage);
        System.out.println( "Insert to Bst : " + bstTimeInsertAverage  + ", Insert to Rbt : " + rbtTimeInsertAverage  + ", Insert to ttTree : " + ttTreeTimeInsertAverage +  ", Insert to bTree : " + bTreeTimeInsertAverage  + ", Insert to skiplist : " + skipListTimeInsertAverage);
        System.out.println( "For 20k element | Bst2: " + bstTimeAverage2 + ", Rbt2: " + rbtTimeAverage2 + ", ttTree2: " + ttTreeTimeAverage2 + ", bTree2: " + bTreeTimeAverage2 + ", skiplist2: " + skipListTimeAverage2);
        System.out.println( "Insert to Bst : " + bstTimeInsertAverage2  + ", Insert to Rbt : " + rbtTimeInsertAverage2  + ", Insert to ttTree : " + ttTreeTimeInsertAverage2 +  ", Insert to bTree : " + bTreeTimeInsertAverage2  + ", Insert to skiplist : " + skipListTimeInsertAverage2);
        System.out.println( "For 40k element | Bst3: " + bstTimeAverage3 + ", Rbt3: " + rbtTimeAverage3 + ", ttTree3: " + ttTreeTimeAverage3 + ", bTree3: " + bTreeTimeAverage3 + ", skiplist3: " + skipListTimeAverage3);
        System.out.println( "Insert to Bst : " + bstTimeInsertAverage3  + ", Insert to Rbt : " + rbtTimeInsertAverage3  + ", Insert to ttTree : " + ttTreeTimeInsertAverage3 +  ", Insert to bTree : " + bTreeTimeInsertAverage3  + ", Insert to skiplist : " + skipListTimeInsertAverage3);
        System.out.println( "For 80k element | Bst4: " + bstTimeAverage4 + ", Rbt4: " + rbtTimeAverage4 + ", ttTree4: " + ttTreeTimeAverage4 + ", bTree4: " + bTreeTimeAverage4 + ", skiplist4: " + skipListTimeAverage4);
        System.out.println( "Insert to Bst : " + bstTimeInsertAverage4  + ", Insert to Rbt : " + rbtTimeInsertAverage4  + ", Insert to ttTree : " + ttTreeTimeInsertAverage4 +  ", Insert to bTree : " + bTreeTimeInsertAverage4  + ", Insert to skiplist : " + skipListTimeInsertAverage4);
    }
    
    
}