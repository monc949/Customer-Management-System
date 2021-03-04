package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Model.Customer;


public class CustomerController {

    //Constructor
    public CustomerController() {

    }

//Create method
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
        int i;

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

                i = pstat.executeUpdate();
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
                        pstat = connection.prepareStatement("SELECT CustomerID, Name From Customers");
                        
                        // query database
                        resultSet = pstat.executeQuery("SELECT CustomerID, Name From Customers" );
                        
                        // process query results
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int numberOfColumns = metaData.getColumnCount();
                        
                        
                        while(resultSet.next() ){
                                for ( int i = 1; i <= numberOfColumns; i++ )
                                    result = resultSet.getString("CustomerID") +" : " + resultSet.getString("Name");
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
public void updateCustomer(int CustomerID, String name, String address, String postcode, String email, String phoneNumber) { 
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
            pstat = connection.prepareStatement("UPDATE customers SET Name = ?, Address = ?, Postcode = ?, Email = ?, Phonenumber = ? WHERE CustomerID = ?");
            pstat.setString(1, name);
            pstat.setString(2, address);
            pstat.setString(3, postcode);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, CustomerID);

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
public void deleteCustomer(int customerID) {
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

