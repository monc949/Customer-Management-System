package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Product;


public class CartItemController {

    //Constructor
    public CartItemController() {

    }




/** 
 * @param product
 */
public void createNewCartItem(Product product) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        int ProductID = product.getProductID();
        String brand = product.getBrand();
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO cart (ProductID, Brand, Name, Description, Price) VALUES (?,?,?,?,?)");
                pstat.setInt(1, ProductID);
                pstat.setString(2, brand);
                pstat.setString(3, name);
                pstat.setString(4, description);
                pstat.setDouble(5, price);
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


public void clearCart() {
    // database URL

        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;			
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM cart");			
            
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

    

/** 
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveCartTable() {
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
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

        PreparedStatement pstm = con.prepareStatement("SELECT ProductID, Brand, Name, Description, Price FROM Cart");
        ResultSet Rs = pstm.executeQuery();
        while (Rs.next()) {
            model.addRow(
                    new Object[] { Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getDouble(5) });
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return model;
}





/** 
 * @return ArrayList<Product>
 */
public ArrayList<Product> retrieveCartItems() {
    // database URL
    final String DATABASE_URL = "jdbc:mysql://localhost/cms";
    ArrayList<Product> cartItems = new ArrayList<Product>();

    //---retrieve from database---//
    //-and populate table---//
    try {
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Cart");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Product element = new Product(resultSet.getInt("ProductID"),
            resultSet.getString("Brand"),
            resultSet.getString("name"),
            resultSet.getString("description"),
            resultSet.getDouble("price"));

            
            cartItems.add(element);
        }
    }
         catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return cartItems; 
    }

}





