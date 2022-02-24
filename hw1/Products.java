/**
 * Product class with model and color if there is a color option.
 */
public class Products implements ProductsInterface{
    /**
     * Product model that has a color.
     */
    enum Product{
        MEETING_TABLES, OFFICE_DESKS, OFFICE_CHAIRS, // Products with color option.
        OFFICE_CABINETS, BOOK_CASES; // Products with no color option
    }

    private Colors color;
    private Product product;
    private int model;
    private int stock;

    /**
     * Constructor for products
     * @param color color of product
     * @param model model of product
     * @param stock stock info of product
     * @param product type of product
     */
    public Products(Colors color, int model, int stock, Product product) {
        this.color = color;
        this.model = model;
        this.product = product;
        for(int i=0; i<model; i++)
            this.stock = stock;
    }

    /**
     * Sets color for product
     * @color Color enum
     */
    @Override
    public void setColor(Colors color){
        this.color = color;
    }

    /**
     * @return color
     */
    @Override
    public Colors getColor(){
        return color;
    }
    
    /**
     * @return product type
     * no @Override because contains enum.
     */
    public Product getProduct(){
        return product;
    }

    /**
     * Sets product type
     * @param product product type
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return stock of product in that branch
     */
    @Override
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock for product.
     * @param stock stock of product
     */
    @Override
    public void setStock(int stock) {
            this.stock = stock;
    }

    /**
     * @return model number
     */
    public int getModel() {
        return model;
    }

    /**
     * Sets model number
     * @param model model no to be set
     */
    public void setModel(int model) {
        this.model = model;
    }

    /**
     * Decreases stock by 1.
     */
    @Override
    public void decreaseStock(){
        --(this.stock);
    }

    /**
     * Increases stock by 1.
     */
    @Override
    public void increaseStock(){
        ++(this.stock);
    }

    /**
     * @return product info as string.
     */
    @Override
    public String toString() {
        String s = "";
        s = "Model: " + model + " Color: " + color + " Type: " + product;
        return s;
    }



}
