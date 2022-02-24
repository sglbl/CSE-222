/**
 * @author Suleyman Golbol
 * Company's all features like all branches, admins and customers. ( branch employees are in branch class.)
 */
public class Company implements CompanyInterface{
    private MutableArray<Branch> allBranches = new MutableArray<Branch>();
    private MutableArray<Admins> allAdmins = new MutableArray<Admins>();
    private MutableArray<Customers> allCustomers = new MutableArray<Customers>();
    
    /**
     * @return branches array in company.
     */
    @Override
    public MutableArray<Branch> getAllBranches(){
        return allBranches;
    }

    /**
     * @return all customers.
     */
    @Override
    public MutableArray<Customers> getAllCustomers(){
        return allCustomers;
    }

    /**
     * add that branch to all branches.
     * @param branch will be added.
     */
    @Override
    public void addBranch(Branch branch){
        allBranches.add(branch);
    }

    /**
     * add that adminstrator to all admins.
     * @param administrator administrator will be added.
     */
    @Override
    public void addAdministrator(Admins administrator){
        allAdmins.add(administrator);
    }

    /**
     * Adds customer to all customers array.
     * @param customer Customer will be added.
     */
    @Override
    public void addCustomer(Customers customer){
        allCustomers.add(customer);
    }

    /**
     * Removes branch
     * @param b branch to remove
     */
    @Override
    public void removeBranch(Branch b){
        allBranches.remove(b);
    }

    /**
     * @param customerNumber special customer number.
     * @return true if there is a customer with that number
     */
    @Override
    public boolean checkCustomerNumber(int customerNumber){
        for(int i=0; i<allCustomers.size(); i++){
            if(allCustomers.getIndex(i).getCustomerNumber() == customerNumber)
                return true;
        }
        return false;
    }



}