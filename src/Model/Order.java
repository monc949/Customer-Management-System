package Model;

import java.util.ArrayList;

/**
 * Order Object
 */
public class Order {
    private int orderID;
    private int customerID;
    private double totalPrice;
    private ArrayList<Product> productList = new ArrayList<Product>();

    /**
     * Creates an Order Object using a Customer ID and a ArrayList of Products
     * @param customerID
     * @param productList
     */
    public Order(int customerID, ArrayList<Product> productList) {
        setCustomerID(customerID);
        setProductList(productList);
        setTotalPrice();

    }

    /**
     * Creates an Order Object using a Customer ID and a ArrayList of Products
     * @param customerID
     * @param productList
     */
    public Order(int orderID, int customerID, ArrayList<Product> productList) {
        setOrderID(orderID);
        setCustomerID(customerID);
        setProductList(productList);
        setTotalPrice();

    }

    

    
    /** Returns The CustomerID of the Order
     * @return int
     */
    public int getCustomerID() {
        return customerID;
    }


    
    /** Sets The CustomerID of the Order
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    

    
    /** Returns the Total Price of Order
     * @return double
     */
    public double getTotalPrice() {
        return totalPrice;
    }


    /**
     * Sets the Total Price of the Order by aggregating the price of each Product in the List
     */
    public void setTotalPrice() {
        double totalPrice = 0;
        for (Product product : productList) {
            totalPrice+=product.getPrice();
        }
        this.totalPrice = totalPrice;
    }

    
 
    
    /** Returns the list of Products in the Order
     * @return ArrayList<Product>
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    

    
    /** Sets the list of Products in the Order
     * @param productList
     */
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }




    



}
