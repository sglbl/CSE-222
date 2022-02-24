/**
 * This class will be use for if there is less amount of a product.
 */
public class LessAmount{
    private int branchIndex;
    private int productIndex;

    /**
     * 
     * @param branchIndex in company's allBranches, branch index will be used to get that branch.
     * @param productIndex in Branch's products, product index will be used to get that product.
     */
    public LessAmount(int branchIndex, int productIndex) {
        this.branchIndex = branchIndex;
        this.productIndex = productIndex;
    }

    public int getBranchIndex() {
        return branchIndex;
    }
    public void setBranchIndex(int branchIndex) {
        this.branchIndex = branchIndex;
    }
    public int getProductIndex() {
        return productIndex;
    }
    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

}

