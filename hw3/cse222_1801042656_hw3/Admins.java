package cse222_1801042656_hw3;

public class Admins extends Users implements AdminsInterface{
    private KWArrayList<LessAmount> lessAmounts;
    /**
     * Constructor for administrator
     * @param name name of administrator
     * @param surname surname of administrator
     * @param email email of administrator
     * @param password password of administrator
     */
    public Admins(String name, String surname, String email, String password){
        super(name, surname, email, password);
        lessAmounts = new KWArrayList<LessAmount>();
    }

    /**
     * Adds branch b to the system.
     * @param c Company parameter.
     * @param b Adds branch b to the system.
     */
    @Override
    public void addBranch(Company c, Branch b){
        c.addBranch(b);
    }
    
    /**
     * Adds employee to that branch.
     * @param branch branch
     * @param bE branch employee 
     */
    @Override
    public void addBranchEmployee(Branch branch, BranchEmployees bE){
        branch.addBranchEmployee(bE);
    }

    /**
     * Removes branch employee
     * @param b branch for removing employee from b branch 
     * @param employee employee to remove
     */
    @Override
    public void removeBranchEmployee(Branch b, BranchEmployees employee){
        b.removeBranchEmployee(employee);
    }

    /**
     * Removes branch
     * @param b branch to remove
     */
    @Override
    public void removeBranch(Company c, Branch b){
        c.removeBranch(b);
    }

    /**
     * If there is a supply waiting supplies it.
     * @param c company
     * @param customerNumber special customer number
     * @return true if found an query that is not supplied.
     */
    @Override
    public boolean queryToSupply(Company c, int customerNumber){
        for(int i=0; i<c.getAllCustomers().size(); i++)
            if( c.getAllCustomers().get(i).getCustomerNumber() == customerNumber ){
                for(int j=0; j<c.getAllCustomers().get(i).getPreviousOrders().size(); j++)
                    if( c.getAllCustomers().get(i).getPreviousOrders().get(j).substring(0, 1).equals("N") ){ //N means not supplied yet.
                        char[] temp = c.getAllCustomers().get(i).getPreviousOrders().get(j).toCharArray();
                        temp[0] = 'S'; //S means supplied.
                        String s = String.valueOf(temp);
                        c.getAllCustomers().get(i).setPreviousOrders(s, j);
                        return true;
                    }   
            }

        return false;
    }

    /**
     * If requested amount is bigger than stock branch employee informs admin with calling this function.
     * @param l l will added to lessAmounts so then admin will increase stock.
     */
    @Override
    public void addLessAmount(LessAmount l){
        lessAmounts.add(l);
    }

    /**
     * If in a branch, stock is less add stock.
     * @param c Company
     * @param newStock sets stock to a bigger number
     */
    @Override
    public void isThereLessAmount(Company c, int newStock){
        int branchIndex, productIndex;
        if(lessAmounts.size() == 0){
            System.out.println("There is no less amount product.");
        }

        else if(lessAmounts.size() != 0){
            System.out.println("Less amont product found and now stock is increasing.");
            for(int i=0; i<lessAmounts.size(); i++){
                branchIndex = lessAmounts.get(i).getBranchIndex();
                productIndex = lessAmounts.get(i).getProductIndex(); 
                c.getAllBranches().get(branchIndex).getProducts().get(productIndex).setStock(newStock);    
            }
        }
    }

    @Override //Using polymorphism
    public void setName(String name) {
        this.name = ("Admin: " + name);
    }

    @Override //Using polymorphism
    public String getName(){
        return "Admin: " + name + " " + surname;
    }


}
