package Controller;

//TODO: Need to do the whole thing, still just a copy of customercontroller
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import Model.Invoice;
import Model.Product;

public class InvoiceController {
    
    //Constructor
    public InvoiceController() {

    }

//Create method
public void create(Invoice newInvoice) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";

        Connection connection = null;
        PreparedStatement pstat = null;
        PreparedStatement pstat2 = null;

        
        
        int customerID = newInvoice.getCustomerID();
        Date invoiceDate = newInvoice.getInvoiceDate();
        double totalPrice = newInvoice.getTotalPrice();
        Product[] productList = newInvoice.getProductList();
       
        int i;

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Invoice (CustomerID, InvoiceDate, TotalPrice) VALUES (?,?,?)");
                pstat.setInt(1, customerID);
                pstat.setDate(2, invoiceDate);
                pstat.setDouble(3, totalPrice);

                i = pstat.executeUpdate();
                System.out.println(i + " record successfully added to the database");

                //TODO: Write a for loop that makes a new entry in the invoiceDetails table. The for lopp will execute
                // as many times as there is elements in 'product list'. i.e. productlist.getlength -1? 

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
		        final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
	
            Connection connection = null;
            PreparedStatement pstat = null;
            ResultSet resultSet = null;
    try{
                
                // establish connection to database
                connection = DriverManager.getConnection(
                DATABASE_URL, "root", "Knockbeg11" );
                
                // create Statement for querying table
                pstat = connection.prepareStatement("SELECT * From Customer");
                
                // query database
                resultSet = pstat.executeQuery(
                "SELECT * From Customer" );
                
                // process query results
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.println( "Customer Table of CMS Database:\n" );
                
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
                
//Update method
public void update() { //FIXME:
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
        String firstname="Lisa";
        String lastname="Brennan";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i;
        
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Knockbeg11" );
            
            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE Customer SET lastName = ? Where firstName = ?");
            pstat.setString(1, lastname);
            pstat.setString(2, firstname);

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

		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        int i = 0;		
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Customer WHERE CustomerID = ?");	
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
