/**
 * BranchEmployees class is for Branch Employees.
 * branchIn contains the info of which branch that employee works on.
 */
public class BranchEmployees extends Users implements BranchEmployeesInterface{
    private Branch branchIn;

    /** 
     * @param name name of employee
     * @param surname surname of employee
     * @param email email of employee
     * @param password password of employee
     * @param branchIn which branch working on
     */
    public BranchEmployees(String name, String surname, String email, String password, Branch branchIn) {
        super(name, surname, email, password);
        this.branchIn = branchIn;
    }

    /**
     * @return which branch employee works.
     */
    @Override
    public Branch getBranchIn(){
        return branchIn;
    }

    /**
     * @return name and surname of employee
     */
    @Override
    public String getName(){
        return name + " " + surname + " ";
    }

    /**
     * Adds new products to that branch
     * @param branchIn employee's branch that working on
     * @param product product will be added.
     */
    @Override
    public void addProduct(Branch branchIn, Products product){
        branchIn.getProducts().add(product);
    }

    /**
     * Adds new products to that branch.
     * @param branchIn branch will have products
     * @param products products will be added
     */
    @Override
    public void addProduct(Branch branchIn, MutableArray<Products> products){
        branchIn.addProductArray(products);
    }

    /**
     * Removes products from that branch
     * @param branchIn employee's branch that working on
     * @param product  product will be deleted.
     */
    @Override
    public void removeProduct(Branch branchIn, Products product){
        branchIn.getProducts().remove(product);
    }

    /**
     * Removes products from that branch
     * @param branchIn employee's branch that working on
     * @param product  product will be deleted.
     * @param model model of product
     */
    @Override
    public void removeProduct(Branch branchIn, Products.Product product, Colors color, int model){
        branchIn.removeProduct(product, color, model);
    }

    /**
     * Inquires stock.
     * @param productType type of product
     * @param color color of product if has any.
     * @param model model of product
     * @return stock number if there is that product. If there is not return -1
     */
    @Override
    public int inquireStock(Products.Product productType, Colors color, int model){
        int index = -1;
        index = branchIn.findProductIndex(productType, color, model);
        try{
            if(index == -1) throw new NullPointerException();
        }catch(NullPointerException e){
            System.err.println("Index cannot be -1 so there is no product yet. Please create product first.");
            return -1;
        }
        return branchIn.getProducts().getIndex( index ).getStock();
    }

    /**
     * @param productType type of product
     * @param color color of product if has any.
     * @param requestedAmount how many requested.
     * @return If requested amount is less than stock return false.
     */
    @Override
    public boolean isAmountEnough(Products.Product productType, Colors color, int model, int requestedAmount){
        if( inquireStock(productType, color, model) < requestedAmount )
            return false;
        return true;
    }

    /**
     * If amount is not enough inform admin.
     * @param c company
     * @param a admin
     * @param productType type of prduct
     * @param color color of product if it has color
     * @param model model of product
     * @param requestedAmount amount requested
     */
    @Override
    public void informManager(Company c, Admins a, Products.Product productType, Colors color, int model, int requestedAmount){
        int branchIndex, productIndex;
        if(isAmountEnough(productType, color, model, requestedAmount) == false){
            LessAmount l;
            for(int i=0; i<c.getAllBranches().size(); i++)
                if( c.getAllBranches().getIndex(i) == branchIn ){
                    branchIndex = i;
                    productIndex = c.getAllBranches().getIndex(i).findProductIndex(productType, color, model);
                    l = new LessAmount(branchIndex, productIndex);
                    a.addLessAmount(l); //adds to admin's object that info.
                }
            
        }
    }

    /**
     * Prints customer's previous orders.
     * @param customer customer
     */
    @Override
    public void accessPreviousOrderInfo(Customers customer){
        customer.ViewPreviousOrders(customer.getCustomerNumber());
    }

    /**
     * Sales product from shop
     * @param branchIn branch that employee works
     * @param type type of product
     * @param color Color of product
     * @param model Model of product
     * @param customer Customer which will buy.
     */
    @Override
    public void sale(Branch branchIn, Products.Product type, Colors color,int model, Customers customer){
        int index = branchIn.findProductIndex(type, color, model);
        branchIn.getProducts().getIndex(index).decreaseStock();
        customer.getPreviousOrders().add("Order-> Branch: " + branchIn.getBranchName() + " " + branchIn.getProducts().getIndex(index).toString());
    }

    /**
     * Adds a new order for customer
     * @param customer customer
     * @param product product to sell
     * @param address adress of customer
     * @param phoneNumber number of customer
     */
    @Override
    public void addNewOrderForCustomer(Customers customer, Products product, String address, int phoneNumber){
        int productIndex = -1;
        for(int i=0; i< branchIn.getProducts().size(); i++){
            if( branchIn.getProducts().getIndex(i) == product ){
                productIndex = i;
            }
        }
        if(productIndex == -1){
            throw new IndexOutOfBoundsException("Product couldn't found.");
        }
        customer.getPreviousOrders().add("Order-> Branch: " + branchIn.getBranchName() + " " + branchIn.getProducts().getIndex(productIndex).toString() + "Address: "+ address+ " Phone: " + phoneNumber);
        branchIn.getProducts().getIndex(productIndex).decreaseStock();
    }


    /**
     * Create a subscription for customer if she is shopping for the first time.
     * @param customerNumber customer number
     * @param customer customer
     */
    @Override
    public void createSubscriptionForCustomer(int customerNumber, Customers customer){
        customer.setSubsription(true);
    }

    /**
     * This method will be invoked by branch employee in-store sales so no need to product supply(not online)
     * @param c company
     * @param branchName name of branch
     * @param index branch index in array
     * @param productIndex product's index in array
     * @param cust customer
     */
    @Override
    public void updatePreviousOrders(Company c, String branchName, int index, int productIndex, Customers cust){
        cust.getPreviousOrders().add("S|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(index).getProducts().getIndex(productIndex).toString() ); //N means not supplied.
        System.out.println("S|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(index).getProducts().getIndex(productIndex).toString() );
    }



    public String toString(){
        return name + " " + surname;
    }
    
}
