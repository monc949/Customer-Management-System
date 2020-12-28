package DataHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class CustomerHandler {
    public void create() {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";

        Connection connection = null;
        PreparedStatement pstat = null;
        String firstname = "Michael";
        String lastname = "Stevenson";
        int i = 0;

        try {

            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table


            pstat = connection.prepareStatement("INSERT INTO Customer (FirstName, LastName) VALUES (?,?)");
            pstat.setString(1, firstname);
            pstat.setString(2, lastname);

            i = pstat.executeUpdate();
            System.out.println(i + " record successfully added to the database");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

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

    public void update() {
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
        String firstname="Lisa";
        String lastname="Brennan";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;
        
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

    public void delete() {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        int i = 0;
        int authorID = 1;		
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Customer WHERE CustomerID = ?");	
            pstat.setInt(1, authorID);		
            
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