package cse222_1801042656_hw6;
import java.util.*;

public interface FileHandlerInterface {
    

    /**
     * Writing info in csv to text file.
     */
    public void writeProducts();

    /**
     * @return true if there is at least one customer.
     */
    public boolean checkIfThereIsCustomer();

    /**
     * @return true if there is at least one trader.
     */
    public boolean checkIfThereIsTrader();

   
    /**
     * Writing user info to text file.
     * @param isCustomer if true, customer if not trader.
     * @param name name of user
     * @param id id of user
     * @param password password of user
     */
    public void writeUsers(boolean isCustomer, String name, long id, String password);


    /**
     * Searching for trader products.
     */
    public void searchTrader();

    /**
     * 
     * @param toWrite data to write
     * @param doNotCleanBuffer if false cleans all data and writes again. else, appends
     * @param fileName name of file.
     */
    public void writeToFile(String toWrite, boolean doNotCleanBuffer, String fileName);

    /**
     * Cleaning all files in beginning of new program.(to prevent problems)
     */
    public void cleanFiles();

    /**
     * Prints data in product file.
     * @param lines the line in file.
     */
    public void printData(String lines);

    /**
     * @param output to be print
     * @return to be returned as integer.
     */
    public int scanInt(String output);

    /**
     * Search by name
     * USING SORTS AND NOT LOADING ALL THE PRODUCTS INTO THE MEMORY.
     * @param string string to searched in name and descriptions.
     */
    public void searchByName(String string);
    
    /**
     * Adding all cateogries to collection.
     * @param string to add.
     */
    public void addAllCategory(String string);

    /**
     * Shrinking by prices.
     * @param min min threshold.
     * @param max max threshold.
     */
    public void shrinkByPriceThreshold(int min, int max);

    /**
     * Merge sort algorithm to sort by discount
     * @param products product to sort.
     */
    public void myMergeSortByDiscount(ArrayList<String> products);

    /**
     * Sorting prices in searched data wşth heap sort.
     * @param heap searhed data
     */
    public void myHeapSortByPrices(ArrayList<String> heap);

    /**
     * Fixes the heap helper method .
     * @param heap data collection
     * @param index index of element
     * @param size size for heapify
     */
    public void heapify(ArrayList<String> heap, int index, int size);
    
    /**
     * Quick sort algorithm to sort with product name.
     * @param lines data collection to sort.
     * @param firstIndex first index in data/data partition
     * @param lastIndex last index in data/data partition
     */
    public void myQuickSortForProductName(ArrayList<String> lines, int firstIndex, int lastIndex );

    /**
     * Heap sort alogrithm to sort heap by traders.
     * @param heap heap/data to be sorted.
     */
    public void myHeapSortByTrader(ArrayList<String> heap);

    /**
     * Fixes the heap
     */
    public void heapifyForTraders(ArrayList<String> heap, int index, int size);

    /**Splitting csv line to get data from file. 
     * @param output , the tex to write on console.
     * @return scanned data from input.
     */
    public String scanString(String output);

    /**
     * Adding product to file.
     * @param traderName name of trader will be add.
     */
    public void addProduct(String traderName);

    /**
     * Removes product to system.
     * @param traderName name of trader to add.
     */
    public void removeProduct(String traderName);

    /**
     * Edits product to system.
     * @param traderName name of trader to add.
     */
    public void editProduct(String traderName);

    /**
     * Getting orders of customer by trader
     * @param traderId trader will be see. (Note that trader can only see her products orders by customer.)
     */
    public void seeCustomerOrders(long traderId);
    
    /**
     * Giving new order
     * @param productName name of product.
     * @param userId id of user.
     */
    public void giveOrder(String productName, long userId);

    /**
     * Viewing previous orders of customer.
     * @param userId id of customer to be checked form file.
     */
    public void viewPreviousOrders(long userId);

    /**
     * Checking user info
     * @param userID id of user
     * @param password password of user.
     * @return true if exist.
     */
    public boolean checkUserInfo(long userID, String password);

    /**
     * Filters all by cateogry
     * @param searchedProducts products searched
     */
    public void filterByCategory(ArrayList<String> searchedProducts);
    
}
