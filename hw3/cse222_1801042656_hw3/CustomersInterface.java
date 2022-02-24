package cse222_1801042656_hw3;
/**
 * All customers will be created with that class and a special customer number will be given.
 * Firstly all subscription's are false(means there is no subscription for that user)
 */
public interface CustomersInterface {
    /**
     * @return special customer number of customer
     */
    public int getCustomerNumber();

    /**
     * @return previous orders array.
     */
    public HybridList<String> getPreviousOrders();

    /**
     * Changing previous order info if there is change in supply of admin or not.
     * @param previousOrder last order to add
     * @param index old previous info's index to delete.
     */
    public void setPreviousOrders(String previousOrder, int index);

    /**
     * Sets special customer number by checking all customers.
     * @param c Company
     */
    public void setCustomerNumber(Company c);

    /**
     * Sets subscription if there is no subscription yet.
     * @param flag false if there is no subscription.
     */
    public void setSubsription(boolean flag);

    /**
     * @return Gets subscription info
     */
    public boolean getSubscription();

    /**
     * List all the products in branch b
     * @param b branch
     */
    public void ListProducts(Branch b);

    /**
     * 
     * @param c
     * @param productType which product it is like Products.Product.BOOK_CASES
     * @param color color of product if there is. (if no color, color should be Colors.NO_COLOR )
     * @return true if found
     */
    public boolean searchForProduct(Company c, Products.Product productType, Colors color, int model);
    
    /**
     * Prints which branches has that product and shows index of product if customer wants to shop
     * @param c comany
     * @param productType type of product
     * @param color color of product
     */
    public int whichBranchIsIn(Company c, Products.Product productType, Colors color, int model);

    /**
     * Shops with adress phone branch name and product index infos
     * @param address adress of customer
     * @param phoneNumber phone number of customer
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     */
    public void shopOnline(String address, int phoneNumber, Company c, String branchName, int productIndex);

    /**
     * Shops with adress phone branch name and product index infos
     * @param address adress of customer
     * @param phoneNumber phone number of customer
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     * @param amount amount to buy
     */
    public void shopOnline(String address, int phoneNumber, Company c, String branchName, int productIndex, int amount);

    /**
     * Shops with adress phone branch name and product index infos
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     * @param amount amount to buy
     * @param employee branch employee
     */
    public void shopInStore(Company c, String branchName, int productIndex, int amount, BranchEmployees employee);


    /**
     * Shops in store so no need for number and adress
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     * @param employee branch employee
     */
    public void shopInStore(Company c, String branchName, int productIndex, BranchEmployees employee);

    
    /**
     * Views all previous orders
     * @param customerNumber special customer number
     */
    public void ViewPreviousOrders(int customerNumber);

    /**
     * Request subscription from branch employee
     * @param bE
     */
    public void requestSubscription(BranchEmployees bE);



}
