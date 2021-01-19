package Model;

import java.sql.Date;

public class Order {

    private int customerID;
    private Date orderDate;
    private double totalPrice;
    private Product[] productList;

    public Order(int customerID, Date orderDate, double totalPrice, Product[] productList) {
        setCustomerID(customerID);
        setOrderDate(orderDate);
        setCustomerID(customerID);
        setProductList(productList);
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product[] getProductList() {
        return productList;
    }

    public void setProductList(Product[] productList) {
        this.productList = productList;
    }



    



}
