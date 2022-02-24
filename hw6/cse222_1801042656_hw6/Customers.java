package cse222_1801042656_hw6;
/**
 * All customers will be created with that class and a special customer number will be given.
 * 
 */
public class Customers extends Users implements CustomersInterface {

    /**
     * Constructor for customer
     * @param name name of customer
     * @param surname surname of customer
     * @param userID id of customer
     * @param password password of customer
     */
    public Customers(String name, String surname, long userID, String password ) {
        super(name, surname, userID, password);
    }    

    /**
     * Constructor for customer
     * @param userID id of user
     * @param password password of user
     */
    public Customers(long userID, String password ) {
        super("tempCustomer", userID, password);
        System.out.println("Entered. Your customer number is " + userID);
    } 

    /**
     * @return special customer number of customer
     */
    public long getAndCreateCustomerID() {
        return userID;
    }

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     * @param string string to search
     */
    public void searchForProduct(ShopSystem s, String string){
        s.searchForProduct(string);
    }

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     * @param string string to search
     */
    public void searchForProductTest(ShopSystem s, String string){
        s.searchForProductTest(string);
    }

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     */
    public void searchTrader(ShopSystem s){
        s.searchTrader();
    }

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     */
    public void searchTraderTest(ShopSystem s){
        s.searchTraderTest();
    }

    /**
     * Giving order
     * @param s system
     * @param productName name of product
     * @param userId id of customer to give order
     */
    public void giveOrder(ShopSystem s, String productName, long userId){
        s.giveOrder(productName, userId);
    }

    /**
     * Views all previous orders
     * @param userId special customer number
     * @param s system
     */
    public void viewPreviousOrders(ShopSystem s, long userId){
        s.viewPreviousOrders(userId);
    }

    @Override
    public String toString(){
        return "Name: " + name + " Surname: " + surname + " Id: " + userID;
    }

    /**
     * Searching for  product.
     * @param s system
     * @param string string to search
     */
    public void customerSearching(ShopSystem s, String string) {
        s.customerSearching(string);
    }


}
