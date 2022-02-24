/**
 * @author Suleyman Golbol
 */
public interface AdminsInterface {
    /**
     * Adds branch b to the system.
     * @param c Company parameter.
     * @param b Adds branch b to the system.
     */
    public void addBranch(Company c, Branch b);
    
    /**
     * Adds employee to that branch.
     * @param branch branch
     * @param bE branch employee 
     */
    public void addBranchEmployee(Branch branch, BranchEmployees bE);

    /**
     * Removes branch employee
     * @param b branch for removing employee from b branch 
     * @param employee employee to remove
     */
    public void removeBranchEmployee(Branch b, BranchEmployees employee);
    
    /**
     * Removes branch
     * @param b branch to remove
     */
    public void removeBranch(Company c, Branch b);

    /**
     * If there is a supply waiting supplies it.
     * @param c company
     * @param customerNumber special customer number
     * @return true if found an query that is not supplied.
     */
    public boolean queryToSupply(Company c, int customerNumber);

    /**
     * If requested amount is bigger than stock branch employee informs admin with calling this function.
     * @param l l will added to lessAmounts so then admin will increase stock.
     */
    public void addLessAmount(LessAmount l);

    /**
     * If in a branch, stock is less add stock.
     * @param c Company
     * @param newStock sets stock to a bigger number
     */
    public void isThereLessAmount(Company c, int newStock);

}
