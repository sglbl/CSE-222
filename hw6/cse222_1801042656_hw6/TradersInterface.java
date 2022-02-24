package cse222_1801042656_hw6;
/**
 * @author Suleyman Golbol
 */

public interface TradersInterface {
    /**
     * @return name and surname of employee
     */
    public String getName();

    /**
     * @return user id
     */
    public long getUserID();

    /**
     * Add product to system
     * @param s system to edit product.
     */
    public void addProduct(ShopSystem s);

    /**
     * Remove from system
     * @param s system to edit product.
     */
    public void removeProduct(ShopSystem s);

    /**
     * Edit product info.
     * @param s system to edit product.
     */
    public void editProduct(ShopSystem s);

    /**
     * Seeing customer's orders (only if customer bought from this trader.)
     * @param s system to edit product.
     */
    public void seeCustomerOrders(ShopSystem s);

    
}
