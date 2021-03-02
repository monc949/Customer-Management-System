package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Model.Product;



public class ProductController {

    public ProductController() {

    }


//Create method
public void create(Product newProduct) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        String brand = newProduct.getBrand();
        String name = newProduct.getName();
        String description = newProduct.getDescription();
        double price = newProduct.getPrice();
 
        int i;

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Products (brand, name, description, price) VALUES (?,?,?,?)");
                pstat.setString(1, brand);
                pstat.setString(2, name);
                pstat.setString(3, description);
                pstat.setDouble(4, price);

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
public DefaultTableModel retrieve() {
        	// database URL
		    final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ProductID");
            model.addColumn("Brand");
            model.addColumn("Name");
            model.addColumn("Description");
            model.addColumn("Price");
    
            //---retrieve from database---//
            //-and populate table---//
            try {
                Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );
    
    
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Products");
                ResultSet Rs = pstm.executeQuery();
                while(Rs.next()){
                    model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getDouble(5)});
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
                return model;
         }





//Retrieve product list method
public DefaultListModel<String> retrieveProductList() {
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
        pstat = connection.prepareStatement("SELECT Brand, Name From Products");
        
        // query database
        resultSet = pstat.executeQuery("SELECT Brand, Name From Products" );
        
        // process query results
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        
        
        while(resultSet.next() ){
                for ( int i = 1; i <= numberOfColumns; i++ )
                    result = resultSet.getString("Brand") + " " + resultSet.getString("Name");
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
            return model;

}

    
    

                
//Update method
public void update(int orderID, String brand, String name, String description, double price) {
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
            pstat = connection.prepareStatement("UPDATE products SET brand = ?, name = ?, description = ?, price = ? Where ProductID = ?");
            pstat.setString(1, brand);
            pstat.setString(2, name);
            pstat.setString(3, description);
            pstat.setDouble(4, price);
            pstat.setDouble(5, orderID);

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
public void delete(int productID) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        int i = 0;		
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Products WHERE ProductID = ?");	
            pstat.setInt(1, productID);		
            
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