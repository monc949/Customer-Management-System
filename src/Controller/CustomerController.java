package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Customer;


/**
 * Controller for the Customer Table
 *
 *
 */
public class CustomerController {

    //Constructor
    public CustomerController() {

    }



/** Creates a new record in the Customer Table using a Customer Object
 * @param newCustomer
 */
public void createNewCustomer(Customer newCustomer) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        String name = newCustomer.getName();
        String address = newCustomer.getAddress();
        String postcode = newCustomer.getPostcode();
        String email = newCustomer.getEmail();
        String phoneNumber = newCustomer.getPhoneNumber();

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO customers (Name, Address, Postcode, Email, PhoneNumber) VALUES (?,?,?,?,?)");
                pstat.setString(1, name);
                pstat.setString(2, address);
                pstat.setString(3, postcode);
                pstat.setString(4, email);
                pstat.setString(5, phoneNumber);

                pstat.executeUpdate();

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




/** Retrieves the Customer table in a the form of a table model
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveCustomerTable() {
        		// database URL
		        final String DATABASE_URL = "jdbc:mysql://localhost/cms";
                DefaultTableModel model = new DefaultTableModel();
                
	
                model.addColumn("CustomerID");
                model.addColumn("Name");
                model.addColumn("Address");
                model.addColumn("Postcode");
                model.addColumn("Email");
                model.addColumn("PhoneNumber");
        
        
                //---retrieve from database---//
                //-and populate table---//
                try {
                    Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");
        
                    PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customers");
                    ResultSet Rs = pstm.executeQuery();
                    while (Rs.next()) {
                        model.addRow(
                                new Object[] { Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getString(5),
                                        Rs.getString(6) });
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }






    
    /** Retrieves the Customer table in the form of a Combobox model
     * @return DefaultComboBoxModel<Customer>
     */
    public DefaultComboBoxModel<Customer> retrieveCustomerList() {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";
    
        DefaultComboBoxModel<Customer> model = new DefaultComboBoxModel<Customer>();
    

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try{
        
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Knockbeg11" );
            
            // create Statement for querying table
            pstat = connection.prepareStatement("SELECT * From Customers");
            
            // query database
            resultSet = pstat.executeQuery("SELECT * From Customers");
            
            // process query results
            
            while(resultSet.next() ){
                        Customer element = new Customer(resultSet.getInt("CustomerID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Address"),
                            resultSet.getString("Postcode"),
                            resultSet.getString("Email"),
                            resultSet.getString("PhoneNumber"));

                            model.addElement(element);
                        
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
                return model;
    
    }
      

/** Updates a Customer in the Customer table using the Users Entries
 * @param CustomerID
 * @param name
 * @param address
 * @param postcode
 * @param email
 * @param phoneNumber
 */
public void updateCustomer(int CustomerID, String name, String address, String postcode, String email, String phoneNumber) { 
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		

        Connection connection = null;
        PreparedStatement pstat = null;
        
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Knockbeg11" );
            
            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE customers SET Name = ?, Address = ?, Postcode = ?, Email = ?, Phonenumber = ? WHERE CustomerID = ?");
            pstat.setString(1, name);
            pstat.setString(2, address);
            pstat.setString(3, postcode);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, CustomerID);

            //Update data in database
            pstat.executeUpdate();
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




/** Deletes a record from the Customer Table using a Customer ID
 * @param customerID
 */
public void deleteCustomer(int customerID) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM customers WHERE CustomerID = ?");	
            pstat.setInt(1, customerID);		
            
            //Delete data in database
            pstat.executeUpdate();
            
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

