package cse222_1801042656_hw3;

public interface BranchInterface {
    /**
     * @return branch name
     */
    public String getBranchName();

    /**
     * @return branch employees in that branch
     */
    public KWLinkedList<BranchEmployees> getBranchEmployees();

    /**
     * Add product to that branch
     * @param product product
     */
    public void addProduct(Products product);

    /**
     * adds branch employee to that branch
     * @param employee employee to add
     */
    public void addBranchEmployee(BranchEmployees employee);

    /**
     * Removing employee ( this method will be called from Admins only)
     * @param employee employee to be removed
     */
    public void removeBranchEmployee(BranchEmployees employee);
    
    /**
     * @return branch name as string
     */
    @Override
    public String toString();

    /**
     * 
     * @param productType type of product
     * @param color color of product if it has spesific color
     * @param model model of product
     * @return index of product with that color model and product type
     */    
    public int findProductIndex(Products.Product productType, Colors color, int model);

    /**
     * Getting products
     * @return all products as array
     */
    public HybridList<Products> getProducts();


}
