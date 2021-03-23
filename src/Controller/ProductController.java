package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Model.Product;



/**
 * Controller for the Product Table
 *
 */
public class ProductController {

    public ProductController() {

    }





/** Creates a new record in the Product Table using a Product Object
 * @param newProduct
 */
public void createNewProduct(Product newProduct) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        String brand = newProduct.getBrand();
        String name = newProduct.getName();
        String description = newProduct.getDescription();
        double price = newProduct.getPrice();
 

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Products (brand, name, description, price) VALUES (?,?,?,?)");
                pstat.setString(1, brand);
                pstat.setString(2, name);
                pstat.setString(3, description);
                pstat.setDouble(4, price);

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





/** Retrieves the Product Table in the form of a Table Model
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveProductTable() {
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







/** Retrieves the Product Table in the form of a List Model
 * @return DefaultListModel<Product>
 */
public DefaultListModel<Product> retrieveProductList() {
    // database URL
    final String DATABASE_URL = "jdbc:mysql://localhost/cms";

    DefaultListModel<Product> model = new DefaultListModel<Product>();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;
    try{
    
        // establish connection to database
        connection = DriverManager.getConnection(
        DATABASE_URL, "root", "Knockbeg11" );
        
        // create Statement for querying table
        pstat = connection.prepareStatement("SELECT * FROM Products");
        
        // query database
        resultSet = pstat.executeQuery("SELECT * FROM Products");
        
        // process query results
        while(resultSet.next() ){
                    Product element = new Product(resultSet.getInt("ProductID"),
                    resultSet.getString("Brand"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"));

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

    
    

                



/** Updates a Product in the Product table using the Users Entries
 * @param productID
 * @param brand
 * @param name
 * @param description
 * @param price
 */
public void updateProduct(int productID, String brand, String name, String description, double price) {
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		

        Connection connection = null;
        PreparedStatement pstat = null;
        
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
            pstat.setDouble(5, productID);

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




/** Deletes a record from the Product Table. Requires an ProductID Number
 * @param productID
 */
public void deleteProduct(int productID) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Products WHERE ProductID = ?");	
            pstat.setInt(1, productID);		
            
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