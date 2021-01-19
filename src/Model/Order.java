package Model;


public class Order {

    private int customerID;
    private double totalPrice;
    private Product[] productList;

    public Order(int customerID, Product[] productList) {
        setCustomerID(customerID);
        setProductList(productList);
        setTotalPrice();

    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < productList.length - 1; i++) {
            totalPrice = totalPrice + productList[i].getPrice();
        }
        this.totalPrice = totalPrice;
    }

    public Product[] getProductList() {
        return productList;
    }

    public void setProductList(Product[] productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        String productString = "";
        for (Product product : productList) {
            productString+=product.getName() + ", ";
        }

        return productString;
    }




    



}
