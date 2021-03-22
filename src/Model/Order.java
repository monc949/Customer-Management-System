package Model;

import java.util.ArrayList;

public class Order {

    private int customerID;
    private double totalPrice;
    private ArrayList<Product> productList = new ArrayList<Product>();

    public Order(int customerID, ArrayList<Product> productList) {
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
        for (Product product : productList) {
            totalPrice+=product.getPrice();
        }
        this.totalPrice = totalPrice;
    }

    
 
    public ArrayList<Product> getProductList() {
        return productList;
    }

    

    public void setProductList(ArrayList<Product> productList) {
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
