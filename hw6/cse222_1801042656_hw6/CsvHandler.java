package cse222_1801042656_hw6;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
/** Note that all products are never loaded in memory in same time. */

public class CsvHandler implements FileHandlerInterface {    
    /**Projects that searched and found will be held here.*/
    private ArrayList<String> searchedProducts = new ArrayList<String>();
    /** products after shrink with prices will held here */
    private TreeSet<String> shrinkedProducts = new TreeSet<String>();
    /** Categories in searched results will be held here.  */
    private Queue<String> categories = new PriorityQueue<>();
    /** File for csv */
    private static final File FILE = new File("e-commerce-samples.csv");
    /** Delimiter to check if new line.  both unix-like and windows type are used to guarentee) [regex used.]  */
    private static final String NEW_LINE_DELIMITER = "\n|\r|\r\n"; 

    /** All scanners are static final so no need to close scanner for leaks + no need to pass in parameters to other methods */

    /** Integer Scanner to get integer inputs */
    private static final Scanner SCAN_INT = new Scanner(System.in);
    /** String Scanner to get string inputs */
    private static final Scanner SCAN_STR = new Scanner(System.in);
    /** File Scanner to hold file data. */
    private static final Scanner SCAN;
    /* Because of Scanner isn't in method. This is tactic to handle excpetion for non-in-method Scanners. */
    static{ 
        Scanner temp = null;
        try{
            temp = new Scanner(FILE);
        }catch(FileNotFoundException exception){
            exception.printStackTrace();
            System.out.println("Exception caught. File not found.");
        }
        SCAN = temp;
    }

    /** Constructor */
    public CsvHandler() throws FileNotFoundException{
        SCAN.useDelimiter(NEW_LINE_DELIMITER);
        @SuppressWarnings("unused")
        String temp = SCAN.next();
        temp = SCAN.next(); //reading new line character in line 1.
    }

    /**
     * Writing info in csv to text file.
     */
    public void writeProducts(){
        String strToWrite;
        HashSet<String> traders = new HashSet<String>();
        //Creating files directory if doesn't exist.
        File directory = new File("files/");
        if(!directory.exists() && !directory.isDirectory())
            directory.mkdirs();

        //Cleaning all files if not empty.
        cleanFiles();
        //Reading csv and writing to products.txt file
        
        try{
            BufferedWriter bufferString= new BufferedWriter( new FileWriter("files/product.txt" ,true) ); //true is for appending to the file. Not deleting all again.
            while( SCAN.hasNext() ){  
                strToWrite = SCAN.nextLine();
                bufferString.write(strToWrite);                     //Writing the product  to file.
                bufferString.write("\n"); 
                //Writing to users.txt file.
                if(!getTraderName(strToWrite).equals("")) 
                    traders.add( getTraderName(strToWrite) );
            }
            bufferString.close();
            tradersToFile(traders);
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

        System.out.println("File read and wrote to products file successfully.");
    }

    /**
     * @param traders to be write on file.
     */
    private void tradersToFile(HashSet<String> traders){
        try{
            BufferedWriter bufferString = new BufferedWriter( new FileWriter("files/users.txt" ,true) ); //true is for appending to the file. Not deleting all again.
            for(String item : traders){
                bufferString.write( "Trader" + ";" + item + ";" + getRandom() + ";" + getRandom());   //Writing the trader name to file with password and user id generation.
                bufferString.write("\n");     
            }
            bufferString.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

    }

    /**
     * @return true if there is at least one customer.
     */
    public boolean checkIfThereIsCustomer() {
        String readedLine; 

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/users.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( readedLine = bufferRead.readLine() ) != null ){
                if(readedLine.contains("Customer") == true){
                    bufferRead.close();
                    return true;
                }
            }
            bufferRead.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        return false;
    }

    /**
     * @return true if there is at least one trader.
     */
    public boolean checkIfThereIsTrader() {
        String readedLine; 

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/users.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( readedLine = bufferRead.readLine() ) != null ){
                if(readedLine.contains("Trader") == true){
                    bufferRead.close();
                    return true;
                }
            }
            bufferRead.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        return false;
    }

    /**
     * Random Id generator (8 digits).
     * @return id
     */
    private long getRandom() {
        Random rand = new Random();
        long unique = rand.nextInt(89999999) + 10000000;  //nextInt(max-min) + min gives the range ş want. 8 digit.
        return unique;
    }

    /**
     * Writing user info to text file.
     * @param isCustomer if true, customer if not trader.
     * @param name name of user
     * @param id id of user
     * @param password password of user
     */
    public void writeUsers(boolean isCustomer, String name, long id, String password){
        //Creating files directory if doesn't exist.
        File directory = new File("files/");
        if(!directory.exists() && !directory.isDirectory())
            directory.mkdirs();

        if(isCustomer == true){
            String toWrite = "Customer;" + name + ";" + id + ";" + password + "\n";
            writeToFile(toWrite, true, "files/users.txt");       
        }
        else{
            String toWrite = "Trader;" + name + ";" + id + ";" + password + "\n";
            writeToFile(toWrite, true, "files/users.txt");       
        }

    }


    /**
     * Searching for trader products.
     */
    public void searchTrader(){
        System.out.print("Enter the trader name that you want to see products: ");
        String searched = SCAN_STR.nextLine();
        String readedLine; 

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/product.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( readedLine = bufferRead.readLine() ) != null ){
                if(getTraderName(readedLine).contains(searched) == true){
                    System.out.println("Readline is " + readedLine);
                    printData(readedLine);
                }
            }

            bufferRead.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

    }

    /**
     * Searching for trader products. THIS IS A TEST METHOD THE METHOD ABOVE TO TEST ALL CASES.
     */
    public void searchTraderTest(){
        System.out.print("Enter the trader name that you want to see products: ");
        String searched = "RosesNRibbons";
        String readedLine; 

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/product.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( readedLine = bufferRead.readLine() ) != null ){
                if(getTraderName(readedLine).contains(searched) == true){
                    System.out.println("Readline is " + readedLine);
                    printData(readedLine);
                }
            }

            bufferRead.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

    }

    /**
     * 
     * @param toWrite data to write
     * @param doNotCleanBuffer if false cleans all data and writes again. else, appends
     * @param fileName name of file.
     */
    public void writeToFile(String toWrite, boolean doNotCleanBuffer, String fileName) {
        BufferedWriter bufferString;

        //Cleaning file if not empty.
        if(doNotCleanBuffer == false)
            try {       
                bufferString= new BufferedWriter( new FileWriter(fileName, false) );
                bufferString.write("");
                bufferString.close();
                return;
            }
            catch (IOException e) {
                System.out.println("File writing error. Control permissions.");
                e.printStackTrace();
            }

        //If program reaches here it means append to file without cleaning context.    
        try {
            bufferString= new BufferedWriter( new FileWriter(fileName,true) ); //true is for appending to the file. Not deleting all again.
            bufferString.write( toWrite );
            bufferString.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch

    }

    /**
     * Cleaning all files in beginning of new program.(to prevent problems)
     */
    public void cleanFiles(){
        String temp = "";
        //This cleans all the files. 
        //False means to clean the inside of files.
        writeToFile(temp, false, "files/product.txt");
        writeToFile(temp, false, "files/orders.txt");
        writeToFile(temp, false, "files/users.txt");
    }

    /**
     * Prints data in product file.
     * @param lines the line in file.
     */
    public void printData(String lines) {
        //Printing search info as product name price and description.
        List<String> toPrint = new LinkedList<String>();
        toPrint = Arrays.asList( lines.split(";") );
        System.out.println("ID is: " + toPrint.get(0));
        System.out.print("PRODUCT is: " + toPrint.get(1));
        System.out.print(" | PRICE is: " + toPrint.get(3));
        System.out.println(" | DISCOUNT is: " + toPrint.get(4));
        System.out.println("DESCRIPTION: " + toPrint.get(5));
        // 1 3 and 5 represents csv columns for product name, price and descprition.
    }

    /**
     * Calls other printData method for all searched products
     */
    public void printData() {
        System.out.println("Printing: ");
        for(int i=searchedProducts.size()-1; i >= 0; i--){
            printData( searchedProducts.get(i) );
        }
    }

    /**
     * Calls other printData method for all price shrinked products
     */
    private void printDataForShrinkedPrices(){
        System.out.println("Printing datas with shrinked list: ");
        /** Way 0 */
        //System.out.println( shrinkedProducts.toString() );

        /** Way 1 */
        // //shrinkedProducts.stream().forEach(s -> System.out.println("11->"+s));

        /** Way 2 */
        // for(String item : shrinkedProducts ){
        //     System.out.println("\nITEMM:  " + item);
        // }

        /** Way 3 */
        // for(int i=shrinkedProducts.size()-1; i >= 0; i--){
        //     printData( shrinkedProducts.get(i) );
        // }

        /** Way 4 */
        Iterator<String> treeIterator = shrinkedProducts.iterator();
        while(treeIterator.hasNext()){
            printData( treeIterator.next() );
        }

    }

    /**
     * @param output to be print
     * @return to be returned as integer.
     */
    public int scanInt(String output){
        System.out.printf("%s: ", output);
        int choose = 0;
        while(true){    //Loop until right input.
            try{
                choose = SCAN_INT.nextInt();
                SCAN_INT.nextLine();
                break;
            }
            catch(InputMismatchException e){
                System.out.print(e.getMessage() + " input mismatch exception caught. Try Again: ");
                SCAN_INT.nextLine();
                continue;
            }
        }
        return choose;
    }

    /**
     * Search by name
     * USING SORTS AND NOT LOADING ALL THE PRODUCTS INTO THE MEMORY.
     * @param string string to searched in name and descriptions.
     */
    public void searchByName(String string) {
        //Cleaning searched products if searched before.
        searchedProducts = null; searchedProducts = new ArrayList<String>();
        //Using java 8 stream + forEach to get all lines that contains string.
        try (Stream<String> stream = Files.lines( Paths.get( "files/product.txt" ) ) ) {
            stream.filter(line -> line.contains(string))
                        .forEach( line -> {
                            searchedProducts.add(line);
                        } );
            //Sorting before printing
            myQuickSortForProductName(searchedProducts, 0, searchedProducts.size()-1);
            printData();
            while(true){
                int i = scanInt("Sort by 1) Prices  2) % Discount 3) Name 4) Filter by Threshold 5) Filter by category  6) Back | Select: ");
                System.out.println("\n");
                if(i == 1){ //Search with heap sort
                    myHeapSortByPrices(searchedProducts);
                    printData(); 
                }
                else if(i == 2){ //Search with merge sort.
                    myMergeSortByDiscount(searchedProducts);
                    printData(); 
                }
                else if(i == 3){ //Search with quick sort
                    myQuickSortForProductName(searchedProducts, 0, searchedProducts.size()-1);
                    printData();
                }
                else if(i == 4){ //Filter with threshold.
                    int min = scanInt("Min Threshold: ");
                    int max = scanInt("Max Threshold: ");
                    shrinkByPriceThreshold(min, max);
                }
                else if(i == 5){ //Filters by category.
                    filterByCategory(searchedProducts);
                }
                else if(i == 6){
                    break;
                }
                else{
                    System.out.println("You entered wrong input. Try again.");
                }
            }

        }
        catch (IOException e) { //If error caught
            e.printStackTrace();
        }
    }

    /**
     * Search by name
     * USING SORTS AND NOT LOADING ALL THE PRODUCTS INTO THE MEMORY.
     * @param string string to searched in name and descriptions.
     */
    public void searchByNameTest(String string) {
        //Cleaning searched products if searched before.
        searchedProducts = null; searchedProducts = new ArrayList<String>();
        //Using java 8 stream + forEach to get all lines that contains string.
        try (Stream<String> stream = Files.lines( Paths.get( "files/product.txt" ) ) ) {
            stream.filter(line -> line.contains(string))
                        .forEach( line -> {
                            searchedProducts.add(line);
                        } );
            //Sorting before printing
            myQuickSortForProductName(searchedProducts, 0, searchedProducts.size()-1);
            printData();
        }
        catch (IOException e) { //If error caught
            e.printStackTrace();
        }
    }

    /**
     * Filters all by cateogry
     * @param searchedProducts products searched
     */
    public void filterByCategory(ArrayList<String> searchedProducts){
        boolean flag = false;
        // Getting Category From File.
        
        //Add all categories to collection.
        for( String strToWrite: searchedProducts ){  
            addAllCategory(strToWrite);
        }

        System.out.println("Categories from your search are: ");
        System.out.println( categories.toString() );
        String search = scanString(" category you want to shrink: ");
        if(categories.toString().contains(search) == true){
            for( String strToPrint: searchedProducts ){  
                if(strToPrint.contains(search))
                    printData(strToPrint);
            }
            flag= true; //if input is/from category .
        }

        if(flag == false)
            System.out.println("Coudln't found try again.");

    }

    /**
     * Filters all by cateogry
     * @param searchedProducts products searched
     */
    public void filterByCategoryTest(ArrayList<String> searchedProducts){
        boolean flag = false;
        // Getting Category From File.
        
        //Add all categories to collection.
        for( String strToWrite: searchedProducts ){  
            addAllCategory(strToWrite);
        }

        System.out.println("Categories from your search are: ");
        System.out.println( categories.toString() );
        String search = "Steelseries Headphones (With Mic)";
        if(categories.toString().contains(search) == true){
            for( String strToPrint: searchedProducts ){  
                if(strToPrint.contains(search))
                    printData(strToPrint);
            }
            flag= true; //if input is/from category .
        }

        if(flag == false)
            System.out.println("Coudln't found try again.");
    }
    
    /**
     * Adding all cateogries to collection.
     * @param string to add.
     */
    public void addAllCategory(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 2 represent csv column for product categories.
        if(splitedString.size() <= 6)
            return;
        String tempString = splitedString.get(2);
        //Category found from ""[" to "]""
        String categoryString;
        categoryString = tempString.substring(4, tempString.length()-4) + " ";
        //Removing "[" and "]"

        //Splitting cateogies.
        List<String> splitedCategories = new LinkedList<String>();
        splitedCategories = Arrays.asList( categoryString.split(">> ") );
        // 2 represent csv column for product categories.
        categories.addAll(splitedCategories);
    }

    /**
     * Shrinking by prices.
     * @param min min threshold.
     * @param max max threshold.
     */
    public void shrinkByPriceThreshold(int min, int max){
        //myHeapSortByPrices(searchedProducts);
        for(String item : searchedProducts){
            if( getPriceAsInt(item) >= min && getPriceAsInt(item) <= max){
                //System.out.println(item);
                shrinkedProducts.add("Price: " + getPriceAsInt(item) + " " + item);
            }
        }
        printDataForShrinkedPrices();

    }

    /**
     * Merge sort algorithm to sort by discount
     * @param products product to sort.
     */
    public void myMergeSortByDiscount(ArrayList<String> products){
        if( products.size() <= 1)
            return;
        
        ArrayList<String> leftTable;
        ArrayList<String> rightTable;

        int middle = products.size() / 2;
        leftTable  = new ArrayList<String>();
        rightTable = new ArrayList<String>();
        for(int i=0; i<products.size(); i++){
            if(i < middle) // it is odd number.
                leftTable.add(  products.get(i) );
            else
                rightTable.add( products.get(i) );
        }

        myMergeSortByDiscount(leftTable);
        myMergeSortByDiscount(rightTable);

        merge(leftTable, rightTable, products);
    }

    /**
     * Helper method in merge sort.
     * @param leftTable table divided 2 and this is the left one.
     * @param rightTable table divided to 2 and this is the right one.
     * @param realTable the table to merged at the end.
     */
    private void merge(ArrayList<String> leftTable, ArrayList<String> rightTable, ArrayList<String> realTable) {
        int index, leftIndex, rightIndex;
        index = 0;
        leftIndex = 0;
        rightIndex = 0;
        while(leftIndex < leftTable.size() && rightIndex < rightTable.size()){
            if( getDiscountAsInt( leftTable.get(leftIndex) ) < getDiscountAsInt( rightTable.get(rightIndex) ) ){
                realTable.set(index, leftTable.get(leftIndex));
                leftIndex++;
                index++;
            }
            else{
                realTable.set(index, rightTable.get(rightIndex));
                rightIndex++;
                index++;
            }
        }

        while( leftIndex < leftTable.size() ){
            realTable.set(index, leftTable.get(leftIndex) );
            leftIndex++;
            index++;
        }

        while( rightIndex < rightTable.size() ){
            realTable.set(index, rightTable.get(rightIndex) );
            rightIndex++;
            index++;
        }

    }

    /**
     * Helper method to parse string to find price between ;.
     * @param string to be parsed
     * @return discount percentage of product as int
     */
    private int getDiscountAsInt(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 4 represent csv column for discounted price of product.
        String strToReturn = splitedString.get(4);
        return Integer.parseInt(strToReturn);
    }

    /**
     * Sorting prices in searched data wşth heap sort.
     * @param heap searhed data
     */
    public void myHeapSortByPrices(ArrayList<String> heap){
        for(int i= heap.size() /2; i>=0; i--)
            heapify(heap, i, heap.size());

        //Moving current index (root) to end of heap.
        for(int i=heap.size()-1; i>0; i--){                    
            // Changing heap values top and 
            String temp = heap.get(0);
            heap.set(0, heap.get(i) );
            heap.set(i, temp);
            
            heapify(heap, 0, i);
        }   
    }

    /**
     * Fixes the heap helper method .
     * @param heap data collection
     * @param index index of element
     * @param size size for heapify
     */
    public void heapify(ArrayList<String> heap, int index, int size){
        int leftIndex, rightIndex;
        
        while(true){
            leftIndex  = 2*index+1;
            rightIndex = 2*index+2;
            if(leftIndex >= size)
                break;
            int biggestChild;
            //Comparing prices of left child and right child.
            if(rightIndex >= size || getPriceAsInt( heap.get(leftIndex) ) >=  getPriceAsInt( heap.get(rightIndex) )) 
                biggestChild = leftIndex;
            else
                biggestChild = rightIndex;
            if(getPriceAsInt( heap.get(biggestChild) ) <= getPriceAsInt( heap.get(index) ) )
                break;
            
            String temp = heap.get(index);
            heap.set(index, heap.get(biggestChild) );
            heap.set(biggestChild, temp);
            index = biggestChild;
        }
    }
    
    /**
     * Helper method to parse string to find price between ;.
     * @param string to be parsed
     * @return price of product as int
     */
    private int getPriceAsInt(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 3 represent csv column for price of product.
        String strToReturn = splitedString.get(3);
        return Integer.parseInt(strToReturn);
    }

    /**
     * Quick sort algorithm to sort with product name.
     * @param lines data collection to sort.
     * @param firstIndex first index in data/data partition
     * @param lastIndex last index in data/data partition
     */
    public void myQuickSortForProductName(ArrayList<String> lines, int firstIndex, int lastIndex ){
        int partit;
        if(firstIndex < lastIndex){
            partit = partitionForProductName(lines, firstIndex, lastIndex);
            int leftSide = partit-1;
            myQuickSortForProductName(lines, firstIndex, leftSide);
            int rightSide = partit+1;
            myQuickSortForProductName(lines, rightSide, lastIndex);
        }
    }

    /**
     * Quick sort algorithm to sort with product name.
     * @param lines data collection to sort.
     * @param firstIndex first index in data/data partition
     * @param lastIndex last index in data/data partition
     */
    private int partitionForProductName(ArrayList<String> lines, int firstIndex, int lastIndex) {
        String pivot; 
        int down = firstIndex-1;

        //Printing search info as product name price and description.
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( lines.get(lastIndex).split(";") );
        // 1 represent csv column for product name.
        
        //Starting pivot from last index of partition.
        pivot = splitedString.get(1);
        //System.out.println("Pivot is " + pivot);
        splitedString = null; splitedString = new LinkedList<String>(); 
        //System.out.println("Pivot2 is " + pivot);

        int counter = firstIndex;
        while(counter <= lastIndex-1){ //In a loop checking every element with pivot value.
            splitedString = null; splitedString = new LinkedList<String>(); 
            splitedString = Arrays.asList( lines.get(counter).split(";") );
            if(splitedString.get(1).compareTo(pivot) < 0){
                down++;
                //swapping
                String temp = lines.get(down);
                lines.set(down, lines.get(counter));
                lines.set(counter, temp);
            }
            counter++;
        }

        int returnValue;
        returnValue = down+1;
        //Swapping 
        String temp = lines.get(returnValue);
        lines.set(returnValue, lines.get(lastIndex) );
        lines.set(lastIndex, temp);

        return returnValue;
    }

    /**
     * Heap sort alogrithm to sort heap by traders.
     * @param heap heap/data to be sorted.
     */
    public void myHeapSortByTrader(ArrayList<String> heap){
        for(int i= heap.size() /2; i>=0; i--)
            heapifyForTraders(heap, i, heap.size());

        //Moving current index (root) to end of heap.
        for(int i=heap.size()-1; i>0; i--){                    
            // Changing heap values top and 
            String temp = heap.get(0);
            heap.set(0, heap.get(i) );
            heap.set(i, temp);
            
            heapifyForTraders(heap, 0, i);
        }   
    }

    /**
     * Fixes the heap
     */
    public void heapifyForTraders(ArrayList<String> heap, int index, int size){
        int leftIndex, rightIndex;
        
        while(true){
            leftIndex  = 2*index+1;
            rightIndex = 2*index+2;
            if(leftIndex >= size)
                break;
            int biggestChild;
            //Comparing prices of left child and right child.
            if(rightIndex >= size || getTraderName( heap.get(leftIndex) ).compareTo( getTraderName( heap.get(rightIndex) ) ) >= 0 ) 
                biggestChild = leftIndex;
            else
                biggestChild = rightIndex;
            if(getTraderName( heap.get(biggestChild) ).compareTo( getTraderName( heap.get(index) ) ) <= 0 )
                break;
            
            String temp = heap.get(index);
            heap.set(index, heap.get(biggestChild) );
            heap.set(biggestChild, temp);
            index = biggestChild;
        }
    }

    /**Splitting csv line to get id from file. 
     * @param string , the line from file.
     * @return id.
     */
    private String getUniqueIdOfProduct(String string){
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 0 represent csv column for product id.
        if(splitedString.size() <= 6)
            return "";
        String strToReturn = splitedString.get(0);
        return strToReturn;
    }

    /**Splitting csv line to get trader name from file. 
     * @param string , the line from file.
     * @return name of trader.
     */
    private String getTraderName(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 6 represent csv column for product trader.
        if(splitedString.size() <= 6)
            return "";
        String strToReturn = splitedString.get(6);
        return strToReturn;
    }

    /**Splitting csv line to get data from file. 
     * @param string , the line from file.
     * @return product name.
     */
    private String getProductName(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 1 represent csv column for product name.
        if(splitedString.size() <= 6)
            return "";
        String strToReturn = splitedString.get(1);
        return strToReturn;
    }

    
    /**
     * Helper method to parse string to find id ;.
     * @param string to be parsed
     * @return id
     */
    private long getUserIdFromUsersFile(String string){
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 2 represent csv/txt column for user id.
        if(splitedString.size() <= 3)
            return 0;
        String strToReturn = splitedString.get(2);
        return Long.parseLong(strToReturn);
    }

    /**
     * Helper method to parse string to find password;.
     * @param string to be parsed
     * @return password to check.
     */
    private String getPasswordFromUsersFile(String string){
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 3 represent csv/txt column for password.
        if(splitedString.size() <= 3)
            return "";
        String strToReturn = splitedString.get(3);
        return strToReturn;
    }

    /**
     * Helper method to parse string to find name of trader.
     * @param string to be parsed
     * @return name of trader
     */
    private String getTraderNameFromUsersFile(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 1 represent csv/txt column for name of user.
        if(splitedString.size() <= 3)
            return "";
        String strToReturn = splitedString.get(1);
        return strToReturn;
        
    }

    /**Splitting csv line to get data from file. 
     * @param output , the tex to write on console.
     * @return scanned data from input.
     */
    public String scanString(String output){
        System.out.printf("Enter %s: ", output);
        return SCAN_STR.nextLine();
    }

    /**
     * Adding product to file.
     * @param traderName name of trader will be add.
     */
    public void addProduct(String traderName) {
        Random rand = new Random();
        long uniqueID = rand.nextInt(89999999) + 10000000;  //nextInt(max-min) + min gives the range ş want. 8 digit.

        try{
            BufferedWriter bufferString = new BufferedWriter( new FileWriter("files/product.txt" ,true) ); 
            bufferString.write( uniqueID + ";" + scanString("product name") + ";\"[\"\"" + scanString("category(split by \" >> \" (with space,without brackets))") + "\"\"]\";" + scanInt("price") + ";" + scanInt("discount") + ";" + scanString("description") + ";" + traderName);   //Writing the trader name to file.
            bufferString.write("\n");
            bufferString.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

    }

    /**
     * Removes product to system.
     * @param traderName name of trader to add.
     */
    public void removeProduct(String traderName){
        String uniqueID = scanString("Enter id of the product you want to remove: ");
        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/product.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempProduct.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if( getUniqueIdOfProduct(lineCurrent).equals(uniqueID) == true)
                    continue;
                else{
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

    }

    /**
     * Edits product to system.
     * @param traderName name of trader to add.
     */
    public void editProduct(String traderName) {
        String uniqueID = scanString("Enter id of the product you want to edit: ");
        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.
        boolean flagIfFound = false;

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/product.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempProduct.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if( getUniqueIdOfProduct(lineCurrent).equals(uniqueID) == true){
                    //Writing new info
                    System.out.println("Old line info was like that. For new data enter info: ");
                    printData(lineCurrent);
                    System.out.println("Enter the new info: ");
                    bufferString2.write( uniqueID + ";" + scanString("product name") + ";\"[\"\"" + scanString("category(split by \" >> \" (with space,without brackets))") + "\"\"]\";" + scanInt("price") + ";" + scanInt("discount") + ";" + scanString("description") + ";" + traderName);   //Writing the trader name to file.
                    bufferString2.write("\n");
                    flagIfFound = true;
                    continue;
                }
                else{
                    //Writing old info
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            if(flagIfFound == false)
                System.out.println("The product you looked couldn't found.");
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
        
    }
    //*********************************** THESE 3 METHODS ARE FOR TEST CASES  *********************************** */
    /**
     * Adding product to file.
     * @param traderName name of trader will be add.
     */
    public void addProductTest(String traderName) {
        Random rand = new Random();
        long uniqueID = rand.nextInt(89999999) + 10000000;  //nextInt(max-min) + min gives the range ş want. 8 digit.

        try{
            BufferedWriter bufferString = new BufferedWriter( new FileWriter("files/product.txt" ,true) ); 
            bufferString.write( uniqueID + ";" + "Random product" + ";\"[\"\"" + "category1 >> category2 >> category3" + "\"\"]\";" + 150 + ";" + 14 + ";" + "Temp description" + ";" + traderName);   //Writing the trader name to file.
            bufferString.write("\n");
            bufferString.close();
        }  //End of try
        catch (IOException e) {
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
        System.out.println("Added successfully");
    }

    /**
     * Removes product to system.
     * @param traderName name of trader to add.
     */
    public void removeProductTest(String traderName){
        String uniqueID = "CNBEJ9EDXWN8HQUU";
        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/product.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempProduct.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if( getUniqueIdOfProduct(lineCurrent).equals(uniqueID) == true)
                    continue;
                else{
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
        System.out.println("Removed successfully");
    }

    /**
     * Edits product to system.
     * @param traderName name of trader to add.
     */
    public void editProductTest(String traderName) {
        String uniqueID = "SHOEA4HADYGNUZFM";
        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.
        boolean flagIfFound = false;

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/product.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempProduct.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if( getUniqueIdOfProduct(lineCurrent).equals(uniqueID) == true){
                    //Writing new info
                    System.out.println("Old line info was like that. ");
                    printData(lineCurrent);
                    System.out.println("Entering the new info: ");
                    bufferString2.write( uniqueID + ";" + "temp edit name" + ";" + "temp edit category >> category2" + ";" + 343 + ";" + 12 + ";" + "Description" + ";" + traderName);   //Writing the trader name to file.
                    bufferString2.write("\n");
                    flagIfFound = true;
                    continue;
                }
                else{
                    //Writing old info
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            if(flagIfFound == false)
                System.out.println("The product you looked couldn't found.");
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
        System.out.println("Edited successfully");
    }

    /**
     * Helper method to parse string to find price between ;.
     * @param string to be parsed
     * @return discount percentage of product as int
     */
    private long getCustIdFromOrdersFile(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 1 represent csv/txt column for user id.
        if(splitedString.size() <= 1)
            return 0;
        String strToReturn = splitedString.get(1);
        return Long.parseLong(strToReturn);
    }

    /**
     * Helper method to parse string to find id ;.
     * @param string to be parsed
     * @return trader id
     */
    private long getTraderIdFromOrdersFile(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 2 represent csv/txt column for user id.
        if(splitedString.size() <= 3)
            return 0;
        String strToReturn = splitedString.get(2);
        return Long.parseLong(strToReturn);
    }

    /**
     * Helper method to parse string to find status.
     * @param string to be parsed
     * @return status of order like not met, met , canceled.
     */
    private String getStatusFromOrdersFile(String string){
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 1 represent csv/txt column for user id.
        if(splitedString.size() <= 3)
            return "";
        String strToReturn = splitedString.get(3);
        return strToReturn;
    }

    /**
     * Helper method to parse string to find name.
     * @param string to be parsed
     * @return name of product which ordered.
     */
    private String getProductNameFromOrdersFile(String string) {
        List<String> splitedString = new LinkedList<String>();
        splitedString = Arrays.asList( string.split(";") );
        // 1 represent csv/txt column for user id.
        if(splitedString.size() <= 3)
            return "";
        String strToReturn = splitedString.get(0);
        return strToReturn;
    }

    /**
     * Getting orders of customer by trader
     * @param traderId trader will be see. (Note that trader can only see her products orders by customer.)
     */
    public void seeCustomerOrders(long traderId){
        long customerId;
        while(true){    //Loop until right input.
            try{
                System.out.print("Enter customer id you want to see: ");
                customerId = SCAN_STR.nextLong();
                SCAN_STR.nextLine();
                break;
            }
            catch(InputMismatchException e){
                System.out.print(e.getMessage() + " input mismatch exception caught. Try Again: ");
                SCAN.nextLine();
                continue;
            }
        }

        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.
        boolean flagIfFound = false;

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/orders.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempOrders.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if(getCustIdFromOrdersFile(lineCurrent) == customerId && getTraderIdFromOrdersFile(lineCurrent) == traderId){
                    System.out.println("Customer ordered: " + getProductNameFromOrdersFile(lineCurrent) + getStatusFromOrdersFile(lineCurrent));
                    int orderStatus;
                    do{
                        orderStatus = scanInt("1) Meet order 2) Cancel order | Select status: ");
                        if     (orderStatus == 1) bufferString2.write( getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Met\n");
                        else if(orderStatus == 2) bufferString2.write( getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Canceled\n");
                    }while(orderStatus != 1 && orderStatus != 2);
                    bufferString2.write( getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "not Met\n");
                    flagIfFound = true;
                    continue;
                }
                else{
                    //Writing old info
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }                
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            if(flagIfFound == false)
                System.out.println("Order you looked couldn't found you trader.");
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
    
    }

    /**
     * Getting orders of customer by trader
     * @param traderId trader will be see. (Note that trader can only see her products orders by customer.)
     */
    public void seeCustomerOrdersTest(long customerId, long traderId, boolean meetOrder){
        String lineCurrent; //Data to hold the readed line.
        File inputToChange, tempFile; //inputToChange is product file and tempFile is new product file.
        boolean flagIfFound = false;

        try{
            BufferedReader bufferString  = new BufferedReader( new FileReader( inputToChange = new File("files/orders.txt") ) ); //true is for appending to the file. Not deleting all again.
            BufferedWriter bufferString2 = new BufferedWriter( new FileWriter( tempFile      = new File("files/tempOrders.txt") ,true) ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferString.readLine() ) != null ){
                if(getCustIdFromOrdersFile(lineCurrent) == customerId && getTraderIdFromOrdersFile(lineCurrent) == traderId){
                    System.out.println("Customer ordered: " + getProductNameFromOrdersFile(lineCurrent) + getStatusFromOrdersFile(lineCurrent));
                    if(meetOrder == true){
                        bufferString2.write( getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Met\n"); //Meeting order.
                        System.out.println(getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Met");
                    }
                    else if(meetOrder == false){
                        bufferString2.write( getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Canceled\n"); //Cancelling order.
                        System.out.println(getProductNameFromOrdersFile(lineCurrent) + ";" + customerId + ";" + traderId + ";" + "Canceled");
                    }
                    flagIfFound = true;
                    continue;
                }
                else{
                    //Writing old info
                    bufferString2.write(lineCurrent);
                    bufferString2.write("\n");
                }                
            }
            //Remove the original file
            inputToChange.delete();
            //Change name of new file to old's.
            tempFile.renameTo(inputToChange);
            bufferString.close();
            bufferString2.close();
            if(flagIfFound == false)
                System.out.println("Order you looked couldn't found you trader.");
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.
    
    }
    
    /**
     * Giving new order
     * @param productName name of product.
     * @param userId id of user.
     */
    public void giveOrder(String productName, long userId) {
        String lineCurrent; //Data to hold the readed line.
        String traderName = "";
        boolean flagIfFound = false;

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/product.txt") );  //reading file
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if(getProductName(lineCurrent).equals(productName)  ){
                    traderName = getTraderName(lineCurrent);
                    flagIfFound = true;
                    continue;
                }              
            }
            bufferRead.close();
            if(flagIfFound == false){
                System.out.println("Porduct looked couldn't found.");
                return;
            }
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

        //checking if product name exists
        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/product.txt") ); //reading file
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if(getProductName(lineCurrent).equals(productName)){
                    flagIfFound = true;
                    continue;
                }              
            }
            bufferRead.close();
            if(flagIfFound == false){
                System.out.println("Porduct looked couldn't found.");
                return;
            }
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.

        //Finding trader id from trader name.
        flagIfFound = false;
        Long traderId = (long)0;

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/users.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if( getTraderNameFromUsersFile(lineCurrent).equals(traderName) == true ){
                    traderId = getUserIdFromUsersFile(lineCurrent);
                    flagIfFound = true;
                    break;
                }              
            }
            bufferRead.close();
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        if(flagIfFound == false){
            System.out.println("Id couldn't found.");
            return;
        }

        //Giving order.
        try{
            BufferedWriter bufferString= new BufferedWriter( new FileWriter("files/orders.txt" ,true) ); //true is for appending to the file. Not deleting all again.
            bufferString.write(productName + ";" + userId + ";" + traderId + ";" + "not Met");                     //Writing the order to file.
            bufferString.write("\n"); 
            System.out.println("File added.");
            bufferString.close();
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Control permissions.");
            e.printStackTrace();
        }   //End of catch.


    }

    /**
     *@return user id.
     */
    public long getTraderIdFromNane(String traderName){
        String lineCurrent;
        boolean flagIfFound = false;
        Long traderId = (long)0;

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/users.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if( getTraderNameFromUsersFile(lineCurrent).equals(traderName) == true ){
                    traderId = getUserIdFromUsersFile(lineCurrent);
                    flagIfFound = true;
                    break;
                }              
            }
            bufferRead.close();
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        if(flagIfFound == false){
            System.out.println("Id couldn't found.");
            return 0;
        }
        return traderId;
    }

    /**
     * Viewing previous orders of customer.
     * @param userId id of customer to be checked form file.
     */
    public void viewPreviousOrders(long userId){
        String lineCurrent; 
        boolean flagIfFound = false;

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/orders.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if(getCustIdFromOrdersFile(lineCurrent) == userId){
                    System.out.println("Customer ordered: " + getProductNameFromOrdersFile(lineCurrent));
                    System.out.println("Status is: " + getStatusFromOrdersFile(lineCurrent));
                    flagIfFound = true;
                    continue;
                }              
            }
            bufferRead.close();
            if(flagIfFound == false)
                System.out.println("Order looked couldn't found.");
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        
    }

    /**
     * Checking user info
     * @param userID id of user
     * @param password password of user.
     * @return true if exist.
     */
    public boolean checkUserInfo(long userID, String password){
        String lineCurrent; 
        boolean flagIfFound = false;

        try{
            BufferedReader bufferRead = new BufferedReader( new FileReader("files/users.txt") ); //true is for appending to the file. Not deleting all again.
            while( ( lineCurrent = bufferRead.readLine() ) != null ){
                if(getUserIdFromUsersFile(lineCurrent) == userID && getPasswordFromUsersFile(lineCurrent).equals(password) == true ){
                    flagIfFound = true;
                    break;
                }              
            }
            bufferRead.close();
            return flagIfFound;
        }  //End of try
        catch (IOException e){
            System.out.println("File writing error. Check permissions.");
            e.printStackTrace();
        }   //End of catch.

        return flagIfFound;
    }

    public ArrayList<String> getSearchedProducts() {
        return searchedProducts;
    }
    
    

}
