package cse222_1801042656_hw3;
 /**
 * Product class with model and color if there is a color option.
 */
public interface ProductsInterface {

    /**
     * Sets color for product
     * @color Color enum
     */
    public void setColor(Colors color);

    /**
     * @return color
     */
    public Colors getColor();

    /**
     * @return stock of product in that branch
     */
    public int getStock();

    /**
     * Sets stock for product.
     * @param stock stock of product
     */
    public void setStock(int stock);

    /**
     * @return model number
     */
    public int getModel();

    /**
     * Sets model number
     * @param model model no to be set
     */
    public void setModel(int model);

    /**
     * Decreases stock by 1.
     */
    public void decreaseStock();

    /**
     * Increases stock by 1.
     */
    public void increaseStock();

    /**
     * @return product info as string.
     */
    public String toString();


}