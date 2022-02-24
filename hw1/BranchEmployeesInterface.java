public interface BranchEmployeesInterface {
    
    /**
     * @return which branch employee works.
     */
    public Branch getBranchIn();

    /**
     * Adds new products to that branch
     * @param branchIn employee's branch that working on
     * @param product product will be added.
     */
    public void addProduct(Branch branchIn, Products product);

    /**
     * Adds new products to that branch.
     * @param branchIn branch will have products
     * @param products products will be added
     */
    public void addProduct(Branch branchIn, MutableArray<Products> products);

    /**
     * Removes products from that branch
     * @param branchIn employee's branch that working on
     * @param product  product will be deleted.
     */
    public void removeProduct(Branch branchIn, Products product);

    /**
     * Removes products from that branch
     * @param branchIn employee's branch that working on
     * @param product  product will be deleted.
     * @param model model of product
     */
    public void removeProduct(Branch branchIn, Products.Product product, Colors color, int model);

    /**
     * Inquires stock.
     * @param productType type of product
     * @param color color of product if has any.
     * @param model model of product
     * @return stock number if there is that product. If there is not return -1
     */
    public int inquireStock(Products.Product productType, Colors color, int model);

    /**
     * @param productType type of product
     * @param color color of product if has any.
     * @param requestedAmount how many requested.
     * @param model model of product
     * @return If requested amount is less than stock return false.
     */
    public boolean isAmountEnough(Products.Product productType, Colors color, int model, int requestedAmount);

    /**
     * If amount is not enough inform admin.
     * @param c company
     * @param a admin
     * @param productType type of prduct
     * @param color color of product if it has color
     * @param requestedAmount amount requested
     */
    public void informManager(Company c, Admins a, Products.Product productType, Colors color, int model, int requestedAmount);

    /**
     * Prints customer's previous orders.
     * @param customer customer
     */
    public void accessPreviousOrderInfo(Customers customer);

    /**
     * Sales product from shop
     * @param branchIn branch that employee works
     * @param type type of product
     * @param color Color of product
     * @param model Model of product
     * @param customer Customer which will buy.
     */
    public void sale(Branch branchIn, Products.Product type, Colors color,int model, Customers customer);

    /**
     * Adds a new order for customer
     * @param customer customer
     * @param product product to sell
     * @param address adress of customer
     * @param phoneNumber number of customer
     */
    public void addNewOrderForCustomer(Customers customer, Products product, String address, int phoneNumber);

    /**
     * This method will be invoked by branch employee in-store sales so no need to product supply(not online)
     * @param c company
     * @param branchName name of branch
     * @param index branch index in array
     * @param productIndex product's index in array
     * @param cust customer
     */
    public void updatePreviousOrders(Company c, String branchName, int index, int productIndex, Customers cust);

    /**
     * Create a subscription for customer if she is shopping for the first time.
     * @param customerNumber customer number
     * @param customer customer
     */
    public void createSubscriptionForCustomer(int customerNumber, Customers customer);


}
