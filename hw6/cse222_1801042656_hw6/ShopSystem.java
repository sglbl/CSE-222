package cse222_1801042656_hw6;
import java.io.*;
import java.util.*;
/**
 * @author Suleyman Golbol
 * Company's all features like all Shopes, admins and customers. ( Shop employees are in Shop class.)
 */

public class ShopSystem implements ShopInterface{
    private LinkedList<Long> uniqueIDs;
    private CsvHandler rcsv;
    
    /**
     * Constructor
     */
    public ShopSystem(){
        uniqueIDs = new LinkedList<Long>();
    }

    /**
     * Creating CsvHandler object.
     * @throws FileNotFoundException
     */
    public void readCsv() throws FileNotFoundException{
        rcsv = new CsvHandler();
        rcsv.writeProducts();
    }

    /**
     * @return CsvHanler object.
     */
    public CsvHandler getCsvHanler(){
        return rcsv;
    }

    /**
     * @return true if there is at least one customer.
     */
    public boolean checkIfThereIsCustomer(){
        return rcsv.checkIfThereIsCustomer();
    }

    /**
     * 
     * @return true if there is at least one trader.
     */
    public boolean checkIfThereIsTrader() {
        return rcsv.checkIfThereIsTrader();
    }
    
    /**
     * Adds customer to all customers array.
     * @param customer Customer will be added.
     * @return true if name surname and password()must be 6 digit enetered correctly.
     */
    public boolean addCustomer(Customers customer){
        //allCustomers.add(customer);
        //to file.
        if(customer.getPassword().length() != 6 || customer.getName().equals("") || customer.getName().equals(""))
            return false;
        rcsv.writeUsers(true, customer.getName(), customer.getUserID(), customer.getPassword());
        return true;
    }

    /**
     * Adds customer to all customers array.
     * @param trader trader will be added.
     * @return true if name surname and password()must be 6 digit enetered correctly.
     */
    public boolean addTraders(Traders trader){
        //allTraders.add(trader);
        if(trader.getPassword().length() != 6 || trader.getName().equals("") || trader.getName().equals(""))
            return false;
        rcsv.writeUsers(false, trader.getName(), trader.getUserID(), trader.getPassword());
        return true;
    }

    /**
     * Random Id generator (8 digits).
     * @return id
     */
    public long generateUniqueID() {
        long uniqueID = -1;
        do{
            Random rand = new Random();
            uniqueID = rand.nextInt(89999999) + 10000000;  //nextInt(max-min) + min gives the range ş want. 8 digit.
        }while( checkIfIdIsTaken(uniqueID) == true );

        uniqueIDs.add(uniqueID);
        return uniqueID;
    }

    /**
     * Checks all other id's if taken.
     * @param uniqueID id
     * @return false if not taken.
     */
    public boolean checkIfIdIsTaken(long uniqueID){
        Iterator<Long> iter = uniqueIDs.iterator();
        while(iter.hasNext()){
            if(uniqueID == iter.next())
                return true; //It means unique id was taken so change.
        }
        
        return false;
    }

    /**
     * Adds product to system.
     * @param traderName name of trader to add.
     */
    public void addProduct(String traderName) {
        rcsv.addProduct(traderName);
    }

    /**
     * Removes product to system.
     * @param traderName name of trader to add.
     */
    public void removeProduct(String traderName) {
        rcsv.removeProduct(traderName);
    }

    /**
     * Edits product to system.
     * @param traderName name of trader to add.
     */
    public void editProduct(String traderName){
        rcsv.editProduct(traderName);
    }

    
    /**
     * Adds product to system.
     * @param traderName name of trader to add.
     */
    public void addProductTest(String traderName) {
        rcsv.addProductTest(traderName);
    }

    /**
     * Removes product to system.
     * @param traderName name of trader to add.
     */
    public void removeProductTest(String traderName) {
        rcsv.removeProductTest(traderName);
    }

    /**
     * Edits product to system.
     * @param traderName name of trader to add.
     */
    public void editProductTest(String traderName){
        rcsv.editProductTest(traderName);
    }

    /**
     * Searching for a trader.
     */
    public void searchTrader(){
        rcsv.searchTrader();
    }

    /**
     * Searching for a trader.
     */
    public void searchTraderTest(){
        rcsv.searchTraderTest();
    }

    /**
     * Searches a product with name
     * @param string to search 
     */
    public void searchForProduct(String string){
        rcsv.searchByName(string);
    }

    /**
     * Searches a product with name
     * @param string to search 
     */
    public void searchForProductTest(String string){
        rcsv.searchByNameTest(string);
    }

    /**
     * Traders see order of customers
     * @param traderId id of trader.
     */
    public void seeCustomerOrders(long traderId){
        rcsv.seeCustomerOrders(traderId);
    }

    /**
     * Customer gives new order.
     * @param productName name of product
     * @param userId id of user.
     */
    public void giveOrder(String productName, long userId) {
        rcsv.giveOrder(productName, userId);
    }

    /**
     * Previewing previous orders
     * @param userId id of customer to check
     */
    public void viewPreviousOrders(long userId) {
        rcsv.viewPreviousOrders(userId);
    }

    /**
     * 
     * @param userID id of user
     * @param password password of user
     * @return true if user info matches with the files.
     */
    public boolean checkUserInfo(long userID, String password) {
        return rcsv.checkUserInfo(userID, password);
    }   

    /**
     * Searching for product by customer.
     * @param string text to search
     */
    public void customerSearching(String string) {
        rcsv.searchByName(string);
    }


}