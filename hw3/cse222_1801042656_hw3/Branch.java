package cse222_1801042656_hw3;
/**
 * Branch class which contains employees and products.
 */
public class Branch implements BranchInterface{
    private String branchName;
    private KWLinkedList<BranchEmployees> branchEmployees; 
    private HybridList<Products> products;
    
    /**
     * Creates array for branch employees with my custom KWLinkedList class ( which uses simple array! )
     * Creates array for Products with my custom KWLinkedList class ( which uses simple array! )
     * @param branchName name of branch
     */
    public Branch(String branchName) {
        this.branchEmployees = new KWLinkedList<BranchEmployees>();
        this.products =  new HybridList<Products>();
        this.branchName = branchName;
    }

    /**
     * @return branch name
     */
    @Override
    public String getBranchName(){
        return branchName;
    }

    /**
     * @return branch employees in that branch
     */
    @Override
    public KWLinkedList<BranchEmployees> getBranchEmployees(){
        return branchEmployees;
    }

    /**
     * Add product to that branch
     * @param product product
     */
    @Override
    public void addProduct(Products product){
        products.add(product);
    }

    /**
     * Adds products to system
     * @param newProducts Products to be add
     */
    public void addProductArray(HybridList<Products> newProducts){
        products.clear();
        this.products = newProducts;
    }

    /**
     * adds branch employee to that branch
     * @param employee employee to add
     */
    @Override
    public void addBranchEmployee(BranchEmployees employee){
        branchEmployees.add(branchEmployees.size(), employee);
    }

    /**
     * Removing employee ( this method will be called from Admins only)
     * @param employee employee to be removed
     */
    @Override
    public void removeBranchEmployee(BranchEmployees employee){
        branchEmployees.remove(employee);
    }

    public void removeProduct(Products.Product productType, Colors color, int model){
        boolean flag = false;
        for(int i=0; i<products.size(); i++){
            if(productType == products.get(i).getProduct() && color == products.get(i).getColor() && model == products.get(i).getModel() ){
                products.remove(i);
                flag = true;
            }
        }
        if(flag == false)
            System.err.println("Product you want to delete couldn't find.");
    }

    /**
     * @return branch name as string
     */
    @Override
    public String toString(){
        return branchName;
    }

    /**
     * 
     * @param productType type of product
     * @param color color of product if it has spesific color
     * @return index of product with that color and product type(model)
     */    
    @Override
    public int findProductIndex(Products.Product productType, Colors color, int model){
        int flag = -1;
        for(int i=0; i<products.size(); i++){
            if(productType == products.get(i).getProduct() && color == products.get(i).getColor() && model == products.get(i).getModel() )
                flag = i;
        }
        return flag;
    }

    /**
     * Getting products
     * @return all products as array
     */
    @Override
    public HybridList<Products> getProducts(){
        return products;
    }

}
