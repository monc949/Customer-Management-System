package Model;

/**
 * Customer Object
 */
public class Customer{
    private int customerID;
    private String name;
    private String address;
    private String postcode;
    private String email;
    private String phoneNumber;


    /**
     * Customer Object without ID Number, to be used on creation as ID will be auto-assigned
     * @param name
     * @param address
     * @param postcode
     * @param email
     * @param phoneNumber
     */
    public Customer(String name, String address, String postcode, String email, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPostcode(postcode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    /**
     * Customer Object with ID number, to be used when retrieving details from database
     * @param customerID
     * @param name
     * @param address
     * @param postcode
     * @param email
     * @param phoneNumber
     */
    public Customer(int customerID, String name, String address, String postcode, String email, String phoneNumber) {
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setPostcode(postcode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }


    
    /** Returns Customer Name
     * @return String
     */
    public String getName() {
        return name;
    }


    
    /** Sets Customer Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    

    
    /** Returns Customer Address
     * @return String
     */
    public String getAddress() {
        return address;
    }

    

    
    /** Sets Customer Address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
    
    /** Returns Customer Postcode
     * @return String
     */
    public String getPostcode() {
        return postcode;
    }

    

    
    /** Sets Customer Postcode
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    

    
    /** Returns Customer Email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    

    
    /** Sets Customer Email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    

    
    /** Returns Customer Phone Number
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    
 
    
    /** Sets Customer Phone Number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
   
    
    /** Returns Customer ID
     * @return int
     */
    public int getCustomerID() {
        return customerID;
    }

    
 
    
    /** Sets Customer ID
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    

    
    /** Outputs object as CustomerID and Customer Name
     * @return String
     */
    @Override
    public String toString() {
        return customerID + " : " + name;
    }



    

 


    

}