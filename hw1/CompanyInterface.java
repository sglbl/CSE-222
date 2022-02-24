 /**
 * @author Suleyman Golbol
 * Company's all features like all branches, admins and customers. ( branch employees are in branch class.)
 */
public interface CompanyInterface {
    /**
     * @return branches array in company.
     */
    public MutableArray<Branch> getAllBranches();

    /**
     * @return all customers.
     */
    public MutableArray<Customers> getAllCustomers();

    /**
     * add that branch to all branches.
     * @param branch will be added.
     */
    public void addBranch(Branch branch);

    /**
     * add that adminstrator to all admins.
     * @param administrator administrator will be added.
     */
    public void addAdministrator(Admins administrator);

    /**
     * Adds customer to all customers array.
     * @param customer Customer will be added.
     */
    public void addCustomer(Customers customer);

    /**
     * Removes branch
     * @param b branch to remove
     */
    public void removeBranch(Branch b);


    /**
     * @param customerNumber special customer number.
     * @return true if there is a customer with that number
     */
    public boolean checkCustomerNumber(int customerNumber);

}