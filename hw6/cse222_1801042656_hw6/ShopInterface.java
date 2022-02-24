package cse222_1801042656_hw6;

import java.io.FileNotFoundException;

/**
 * @author Suleyman Golbol
 * All features about system will be held in here.
 */
public interface ShopInterface {
    
    /**
     * Creating CsvHandler object.
     * @throws FileNotFoundException
     */
    public void readCsv() throws FileNotFoundException;

    /**
     * @return CsvHanler object.
     */
    public CsvHandler getCsvHanler();

    /**
     * @return true if there is at least one customer.
     */
    public boolean checkIfThereIsCustomer();

    /**
     * 
     * @return true if there is at least one trader.
     */
    public boolean checkIfThereIsTrader();
    
    /**
     * Adds customer to all customers array.
     * @param customer Customer will be added.
     * @return true if name surname and password()must be 6 digit enetered correctly.
     */
    public boolean addCustomer(Customers customer);

    /**
     * Adds customer to all customers array.
     * @param trader trader will be added.
     * @return true if name surname and password()must be 6 digit enetered correctly.
     */
    public boolean addTraders(Traders trader);

    /**
     * Random Id generator (8 digits).
     * @return id
     */
    public long generateUniqueID();

    /**
     * Checks all other id's if taken.
     * @param uniqueID id
     * @return false if not taken.
     */
    public boolean checkIfIdIsTaken(long uniqueID);

    /**
     * Adds product to system.
     * @param traderName name of trader to add.
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
     * Searching for a trader.
     */
    public void searchTrader();

    /**
     * Searches a product with name
     * @param string to search 
     */
    public void searchForProduct(String string);

    /**
     * Traders see order of customers
     * @param traderId id of trader.
     */
    public void seeCustomerOrders(long traderId);

    /**
     * Customer gives new order.
     * @param productName name of product
     * @param userId id of user.
     */
    public void giveOrder(String productName, long userId);

    /**
     * Previewing previous orders
     * @param userId id of customer to check
     */
    public void viewPreviousOrders(long userId);

    /**
     * 
     * @param userID id of user
     * @param password password of user
     * @return true if user info matches with the files.
     */
    public boolean checkUserInfo(long userID, String password);   

    /**
     * Searching for product by customer.
     * @param string text to search
     */
    public void customerSearching(String string);






}