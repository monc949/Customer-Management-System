package Model;

/**
 * Product Object
 */
public class Product {
    
    private int productID;
    private String brand;
    private String name;
    private String description;
    private double price;

    /**
     * Product Object without ID Number, to be used on creation as ID will be auto-assigned
     * @param brand
     * @param name
     * @param description
     * @param price
     */
    public Product(String brand, String name, String description, double price) {
        setBrand(brand);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    /**
     * Product Object with ID number, to be used when retrieving details from database
     * @param productID
     * @param brand
     * @param name
     * @param description
     * @param price
     */
    public Product(int productID, String brand, String name, String description, double price) {
        setProductID(productID);
        setBrand(brand);
        setName(name);
        setDescription(description);
        setPrice(price);
    }


    
    /** Returns Product Brand
     * @return String
     */
    public String getBrand() {
        return brand;
    }

    
 
    
    /** Sets Product Brand
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    
    
    /** Returns Product Name
     * @return String
     */
    public String getName() {
        return name;
    }


    
    /** Sest Product Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    

    
    /** Returns Product Description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    

    
    /** Sets Product Description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    

    
    /** Returns Product Price
     * @return double
     */
    public double getPrice() {
        return price;
    }

    

    
    /** Sets Product Price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    

    
    /** Returns ProductID
     * @return int
     */
    public int getProductID() {
        return productID;
    }

    /** Sets ProductID
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }


    
    /** Outputs Object as Brand and Name
     * @return String
     */
    @Override
    public String toString() {
        return getBrand() + " " + getName();
    }

    


}
