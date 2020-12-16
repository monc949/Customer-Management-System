package DataObjects;
//TODO:
public class Customer{

    private String firstName;
    private String lastName;
    private String address;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postcode;
    private String email;
    private String phoneNumber;


    public Customer(String firstName, String lastName, String address1, String address2, String city,
    String county, String postcode, String email, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setAddress1(address1);
        setAddress2(address2);
        setCity(city);
        setCounty(county);
        setPostcode(postcode);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }


    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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


    

}