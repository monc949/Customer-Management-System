package Model;

public class Customer{
    private int customerID;
    private String name;
    private String address;
    private String postcode;
    private String email;
    private String phoneNumber;


    public Customer(String name, String address, String postcode, String email, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPostcode(postcode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public Customer(int customerID, String name, String address, String postcode, String email, String phoneNumber) {
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setPostcode(postcode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return customerID + " : " + name;
    }



    

 


    

}