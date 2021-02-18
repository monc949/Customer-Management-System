package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

import Model.Customer;


public class CustomerController {

    //Constructor
    public CustomerController() {

    }

//Create method
public void create(Customer newCustomer) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        String firstname = newCustomer.getFirstName();
        String lastname = newCustomer.getLastName();
        String address1 = newCustomer.getAddress1();
        String address2 = newCustomer.getAddress2();
        String city = newCustomer.getCity();
        String county = newCustomer.getCounty();
        String postcode = newCustomer.getPostcode();
        String email = newCustomer.getEmail();
        String phoneNumber = newCustomer.getPhoneNumber();
        int i;

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO customers (FirstName, LastName, Address1, Address2, City, County, Postcode, Email, PhoneNumber) VALUES (?,?,?,?,?,?,?,?,?)");
                pstat.setString(1, firstname);
                pstat.setString(2, lastname);
                pstat.setString(3, address1);
                pstat.setString(4, address2);
                pstat.setString(5, city);
                pstat.setString(6, county);
                pstat.setString(7, postcode);
                pstat.setString(8, email);
                pstat.setString(9, phoneNumber);

                i = pstat.executeUpdate();
                System.out.println(i + " record successfully added to the database");
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
                try {
                    pstat.close();
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

        }
    }


//Retrieve method
public void retrieve() {
        		// database URL
		        final String DATABASE_URL = "jdbc:mysql://localhost/cms";
	
            Connection connection = null;
            PreparedStatement pstat = null;
            ResultSet resultSet = null;
    try{
                
                // establish connection to database
                connection = DriverManager.getConnection(
                DATABASE_URL, "root", "Knockbeg11" );
                
                // create Statement for querying table
                pstat = connection.prepareStatement("SELECT * From customers");
                
                // query database
                resultSet = pstat.executeQuery("SELECT * From customers");
                
                // process query results
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.println( "Customers Table of CMS Database:\n" );
                
                 for ( int i = 1; i <= numberOfColumns; i++ )
                 System.out.print(metaData.getColumnName( i ) + "\t");
                 System.out.println();
                 
                 while(resultSet.next() ){
                        for ( int i = 1; i <= numberOfColumns; i++ )
                        System.out.print( resultSet.getObject( i ) + "\t\t");
                        System.out.println();
                         }
                     }
                    catch(SQLException sqlException ) {
                        sqlException.printStackTrace();
                     }
                    finally{
                        try{
                            resultSet.close();
                            pstat.close();
                            connection.close();
                        }
                        catch ( Exception exception ){
                            exception.printStackTrace();
                        }
                    }
                }






    //get customer list for combo box 

                public Object[] retrieveCustomerList() {
                    // database URL
                    final String DATABASE_URL = "jdbc:mysql://localhost/cms";
                
                    DefaultListModel<String> model = new DefaultListModel<String>();
                
                    Connection connection = null;
                    PreparedStatement pstat = null;
                    ResultSet resultSet = null;
                    String result = null;
                    try{
                    
                        // establish connection to database
                        connection = DriverManager.getConnection(
                        DATABASE_URL, "root", "Knockbeg11" );
                        
                        // create Statement for querying table
                        pstat = connection.prepareStatement("SELECT CustomerID, FirstName, LastName From Customers");
                        
                        // query database
                        resultSet = pstat.executeQuery("SELECT CustomerID, FirstName, LastName From Customers" );
                        
                        // process query results
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int numberOfColumns = metaData.getColumnCount();
                        
                        
                        while(resultSet.next() ){
                                for ( int i = 1; i <= numberOfColumns; i++ )
                                    result = resultSet.getString("CustomerID") +" : " + resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                                    model.addElement(result);
                        }
                    }
                            catch(SQLException sqlException ) {
                                sqlException.printStackTrace();
                        }
                            finally{
                                try{
                                    resultSet.close();
                                    pstat.close();
                                    connection.close();
                                }
                                catch ( Exception exception ){
                                    exception.printStackTrace();
                                }
                        }
                            Object[] customerString = new String[model.toArray().length];
                            customerString = model.toArray();

                            return customerString;
                
                }
                  







                
//Update method
public void update(int CustomerID, String firstname, String lastname, String address1, String address2, String city, String county, String postcode, String email, String phoneNumber) { 
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		

        Connection connection = null;
        PreparedStatement pstat = null;
        int i;
        
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Knockbeg11" );
            
            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE customers SET FirstName = ?, Lastname = ?, Address1 = ?, Address2 = ?, City = ?, County = ?, Postcode = ?, Email = ?, Phonenumber = ? WHERE CustomerID = ?");
            pstat.setString(1, firstname);
            pstat.setString(2, lastname);
            pstat.setString(3, address1);
            pstat.setString(4, address2);
            pstat.setString(5, city);
            pstat.setString(6, county);
            pstat.setString(7, postcode);
            pstat.setString(8, email);
            pstat.setString(9, phoneNumber);
            pstat.setInt(10, CustomerID);

            //Update data in database
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the database");
         }
        catch(SQLException sqlException ) {
            sqlException.printStackTrace();
         }
        finally{
            try{
                pstat.close();
                connection.close();
            }
            catch ( Exception exception ){
                exception.printStackTrace();
            }
        }
    }


//Delete method
public void delete(int customerID) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        int i = 0;		
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM customers WHERE CustomerID = ?");	
            pstat.setInt(1, customerID);		
            
            //Delete data in database
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully removed from the database. ");
            
         }
        catch(SQLException sqlException ) {
            sqlException.printStackTrace();
         }
        finally{
            try{
                pstat.close();
                connection.close();
            }
            catch ( Exception exception ){
                exception.printStackTrace();
            }
        }
    }
}

