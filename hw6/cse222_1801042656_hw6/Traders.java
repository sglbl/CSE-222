package cse222_1801042656_hw6;

public class Traders extends Users implements TradersInterface{
    
    /**
     * Constructor for trader
     * @param name name of trader
     * @param surname surname of trader
     * @param userID id of trader
     * @param password password of trader
     */
    public Traders(String name, String surname, long userID, String password){
        super(name, surname, userID, password);
    }

    /**
     * Constructor for trader
     * @param userID id of trader
     * @param password password of trader
     */
    public Traders(long userID, String password) {
        super("tempTrader",userID, password);
    }

    /**
     * @return name and surname of employee
     */
    public String getName(){
        return name + " " + surname + " ";
    }

    /**
     * @return user id
     */
    public long getUserID(){
        return userID;
    }

    /**
     * Add product to system
     * @param s system to edit product.
     */
    public void addProduct(ShopSystem s){
        s.addProduct(name);
    }

    /**
     * Remove from system
     * @param s system to edit product.
     */
    public void removeProduct(ShopSystem s){
        s.removeProduct(name);
    }

    /**
     * Edit product info.
     * @param s system to edit product.
     */
    public void editProduct(ShopSystem s){
        s.editProduct(name);
    }

    /**
     * Add product to system
     * @param s system to edit product.
     */
    public void addProductTest(ShopSystem s){
        s.addProductTest(name);
    }

    /**
     * Remove from system
     * @param s system to edit product.
     */
    public void removeProductTest(ShopSystem s){
        s.removeProductTest(name);
    }

    /**
     * Edit product info.
     * @param s system to edit product.
     */
    public void editProductTest(ShopSystem s){
        s.editProductTest(name);
    }

    /**
     * Seeing customer's orders (only if customer bought from this trader.)
     * @param s system to edit product.
     */
    public void seeCustomerOrders(ShopSystem s){
        s.seeCustomerOrders(userID);
    }

}