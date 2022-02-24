package cse222_1801042656_hw6;
/**
 * All customer info will be held in this class.
 */
public interface CustomersInterface {
    
    /**
     * @return special customer number of customer
     */
    public long getAndCreateCustomerID();

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     * @param string string to search
     */
    public void searchForProduct(ShopSystem s, String string);

    /**
     * Prints which Shopes has that product and shows index of product if customer wants to shop
     * @param s system
     */
    public void searchTrader(ShopSystem s);

    /**
     * Giving order
     * @param s system
     * @param productName name of product
     * @param userId id of customer to give order
     */
    public void giveOrder(ShopSystem s, String productName, long userId);

    /**
     * Views all previous orders
     * @param userId special customer number
     * @param s system
     */
    public void viewPreviousOrders(ShopSystem s, long userId);

    public String toString();

    /**
     * Searching for  product.
     * @param s system
     * @param string string to search
     */
    public void customerSearching(ShopSystem s, String string);


}
