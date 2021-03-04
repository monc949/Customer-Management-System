package Model;

public class Product {
    
    private int productID;
    private String brand;
    private String name;
    private String description;
    private double price;

    public Product(String brand, String name, String description, double price) {
        setBrand(brand);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public Product(int productID, String brand, String name, String description, double price) {
        setProductID(productID);
        setBrand(brand);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return getBrand() + " " + getName();
    }

    


}
