import java.util.Random;    //For random customer number.
/**
 * All customers will be created with that class and a special customer number will be given.
 * Firstly all subscription's are false(means there is no subscription for that user)
 * 
 */
public class Customers extends Users implements CustomersInterface {
    private int customerNumber;
    private boolean subscription;
    private MutableArray<String> previousOrders;

    public Customers(String name, String surname, String email, String password, Company c) {
        super(name, surname, email, password);
        subscription = false;
        previousOrders = new MutableArray<String>();
        setCustomerNumber(c);
        System.out.println(name + " " + surname + "'s customer number is " + customerNumber);
    }    

    /**
     * @return special customer number of customer
     */
    @Override
    public int getCustomerNumber() {
        return customerNumber;
    }

    /**
     * @return previous orders array.
     */
    @Override
    public MutableArray<String> getPreviousOrders(){
        return previousOrders;
    }

    /**
     * Changing previous order info if there is change in supply of admin or not.
     * @param previousOrder last order to add
     * @param index old previous info's index to delete.
     */
    @Override
    public void setPreviousOrders(String previousOrder, int index) {
        this.previousOrders.remove(index);
        previousOrders.add(previousOrder);
    }

    /**
     * Sets special customer number by checking all customers.
     * @param c Company
     */ 
    @Override
    public void setCustomerNumber(Company c) {
        Random random = new Random();
        do{
            this.customerNumber = random.nextInt(100);
        }while( c.checkCustomerNumber(customerNumber) == true ); //If there is another customer with that number, generate another.
    }

    /**
     * Sets subscription if there is no subscription yet.
     * @param flag false if there is no subscription.
     */
    @Override
    public void setSubsription(boolean flag){
        subscription = flag;
    }

    /**
     * @return Gets subscription info
     */
    @Override
    public boolean getSubscription(){
        return subscription;
    }

    /**
     * List all the products in branch b
     * @param b branch
     */
    @Override
    public void ListProducts(Branch b){
        for(int i=0; i<b.getProducts().size(); i++){
            System.out.println( b.getProducts().getIndex(i).toString() );
        }
    }

    /**
     * Prints which branches has that product and shows index of product if customer wants to shop
     * @param c comany
     * @param productType type of product
     * @param color color of product
     */
    @Override
    public boolean searchForProduct(Company c, Products.Product productType, Colors color, int model){
        for(int i=0; i<c.getAllBranches().size(); i++)
            if( c.getAllBranches().getIndex(i).findProductIndex(productType, color, model) != -1)
                return true;
        
        return false;
    }

    /**
     * Polymorphism/ Prints which branches has that product and shows index of product if customer wants to shop
     * @param c comany
     * @param productType type of product
     * @param color color of product
     * @param model model of product
     * @return if found shows product's index. If couldn't found it is -1
     */
    @Override
    public int whichBranchIsIn(Company c, Products.Product productType, Colors color, int model){
        boolean flag = false;
        int index = -1;
        for(int i=0; i<c.getAllBranches().size(); i++)
            if( c.getAllBranches().getIndex(i).findProductIndex(productType, color, model) != -1){
                int stock = c.getAllBranches().getIndex(i).getProducts().getIndex( c.getAllBranches().getIndex(i).findProductIndex(productType, color,model) ).getStock();
                System.out.println("Product found in " + c.getAllBranches().getIndex(i).getBranchName() + " store / stock is " + stock +  " and product index is " + c.getAllBranches().getIndex(i).findProductIndex(productType, color, model));
                index = c.getAllBranches().getIndex(i).findProductIndex(productType,color,model);
                flag = true;
            }
        if(flag == false){
            index = -1;
            System.out.println("Product you searched couldn't find.");
        }
        return index;
    }

    /**
     * Shops with adress phone branch name and product index infos
     * @param address adress of customer
     * @param phoneNumber phone number of customer
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     */
    @Override
    public void shopOnline(String address, int phoneNumber, Company c, String branchName, int productIndex){
        if(subscription == false){
            System.err.println("Please request subscription first.");
        }
        int i;
        for(i=0; i<c.getAllBranches().size(); i++){
            if( c.getAllBranches().getIndex(i).getBranchName().equals(branchName) ){
                c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).decreaseStock();//decreasing stock 1.
                break;
            }
        }
        previousOrders.add("N|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).toString() + "Address: "+ address+ " Phone: " + phoneNumber); //N means not supplied.
        System.out.println("N|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).toString() + "Address: "+ address+ " Phone: " + phoneNumber);
    }

    /**
     * Shops with adress phone branch name and product index infos
     * @param address adress of customer
     * @param phoneNumber phone number of customer
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     * @param amount amount to buy
     */
    @Override
    public void shopOnline(String address, int phoneNumber, Company c, String branchName, int productIndex, int amount){
        boolean flag = false;
        int i, stock = 0;
        if(subscription == false){
            System.err.println("Please request subscription first.");
        }
        for(i=0; i<c.getAllBranches().size(); i++){
            if( c.getAllBranches().getIndex(i).getBranchName().equals(branchName) ){
                stock = c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).getStock();
                if(stock > amount){
                    c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).setStock(stock-amount);//decreasing stock.
                    flag = true;
                    break;    
                }
            }
        }
        if(flag == false){
            System.err.println("Stock is not enough for amount. Tell employee to inform the manager.");
            return;
        }
        
        System.out.println("Buying Successful");
        previousOrders.add("N|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).toString() + " Address: "+ address+ " Phone: " + phoneNumber); //N means not supplied.
        System.out.println("N|Order-> Branch: " + branchName + " " + c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).toString() + " Address: "+ address+ " Phone: " + phoneNumber);
    }

    /**
     * Shops with adress phone branch name and product index infos
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     */
    @Override
    public void shopInStore(Company c, String branchName, int productIndex, BranchEmployees b){
        if(subscription == false){
            System.err.println("Please request subscription first.");
            return;
        }
        if(productIndex < 0){
            System.err.println("You are tyring to buy something that is not in the shop. Try again..");
            return;
        }
        int i;
        for(i=0; i<c.getAllBranches().size(); i++){
            if( c.getAllBranches().getIndex(i).getBranchName().equals(branchName) && productIndex < c.getAllBranches().getIndex(i).getProducts().size() ){
                c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).decreaseStock();//decreasing stock 1.
                break;
            }
            else if(productIndex >= c.getAllBranches().getIndex(i).getProducts().size() ) {
                System.err.println("Product couldn't found in that branch with that index");
                return;
            }
        }
        System.out.println("Buying Successful");
        System.out.println("BRANCH EMPLOYEE IS UPDATING CUSTOMER'S PREVIOUS ORDER");
        b.updatePreviousOrders(c, branchName, i, productIndex, this);
    }
    
    /**
     * Shops with adress phone branch name and product index infos
     * @param c Company
     * @param branchName Name of branch
     * @param productIndex  Which index that product in ( Customer can use whichBranchIsIn to find index. )
     * @param amount amount to buy
     * @param employee branch employee
     */
    @Override
    public void shopInStore(Company c, String branchName, int productIndex, int amount, BranchEmployees employee){
        boolean flag = false;
        if(subscription == false){
            System.err.println("Please request subscription first.");
            return;
        }
        if(productIndex < 0){
            System.err.println("You are tyring to buy something that is not in the shop. Try again..");
            return;
        }
        int i;
        for(i=0; i<c.getAllBranches().size(); i++){
            if( c.getAllBranches().getIndex(i).getBranchName().equals(branchName) && productIndex < c.getAllBranches().getIndex(i).getProducts().size() ){
                int stock = c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).getStock();
                c.getAllBranches().getIndex(i).getProducts().getIndex(productIndex).setStock( stock-amount );//decreasing stock 1.
                if(stock > amount) flag = true;
                break;
            }
            else if(productIndex >= c.getAllBranches().getIndex(i).getProducts().size() ) {
                System.err.println("Product couldn't found in that branch with that index");
                return;
            }
        }
        if(flag == false){
            System.err.println("Stock is not enough for amount. Tell employee to inform the manager.");
            return;
        }
        System.out.println("Buying Successful");
        employee.updatePreviousOrders(c, branchName, i, productIndex, this);
    }


    /**
     * Views all previous orders
     * @param customerNumber special customer number
     */
    @Override
    public void ViewPreviousOrders(int customerNumber){
        for(int i=0; i<previousOrders.size(); i++){
            System.out.println( previousOrders.getIndex(i) );
        }
    }

    /**
     * Request subscription from branch employee
     * @param bE
     */
    @Override
    public void requestSubscription(BranchEmployees bE){
        bE.createSubscriptionForCustomer(customerNumber, this);
    }    

    @Override
    public String toString(){
        return "Name: " + name + " " + surname + " Email: " + email;
    }


}
