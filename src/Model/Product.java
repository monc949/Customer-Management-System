package Model;

public class Product {
    
    private String brand;
    private String name;
    private String description;
    private float price;
    private int quantity;

    public Product(String brand, String name, String description, float price, int quantity) {
        setBrand(brand);
        setName(name);
        setDescription(description);
        setPrice(price);
        setQuantity(quantity);
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    


}
