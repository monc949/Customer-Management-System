package Model;

import java.sql.Date;

public class Invoice {

    private int invoiceID;
    private int customerID;
    private Date invoiceDate;
    private double totalPrice;

    public Invoice(int invoiceID, int customerID, Date invoiceDate, double totalPrice) {
        setInvoiceID(invoiceID);
        setCustomerID(customerID);
        setInvoiceDate(invoiceDate);
        setCustomerID(customerID);
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    



}
