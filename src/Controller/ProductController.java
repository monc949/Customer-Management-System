package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Model.Product;



public class ProductController {

    public ProductController() {

    }


//Create method
public void createNewProduct(Product newProduct) {
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


//Retrieve method
public Product makeCartItem(int productIndex) {
        	// database URL
		    final String DATABASE_URL = "jdbc:mysql://localhost/cms";

            String brand = "";
            String name = "";
            String description = "";
            double price = 0;
            Product cartItem = new Product(brand, name, description, price);
            
    
            //---retrieve from database---//
            //-and populate product---//
            try {
                Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Products WHERE ProductID = ?");
                pstm.setInt(1, productIndex);

                ResultSet Rs = pstm.executeQuery();
                brand = Rs.getString("Brand");
                name = Rs.getString("Name");
                description = Rs.getString("Description");
                price = Rs.getDouble("Price");

                cartItem.setBrand(brand);
                cartItem.setName(name);
                cartItem.setDescription(description);
                cartItem.setPrice(price);
                
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
                
            
                return cartItem;
         }








//Retrieve product list method
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

    
    

                
//Update method
public void updateProduct(int orderID, String brand, String name, String description, double price) {
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
public void deleteProduct(int productID) {
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