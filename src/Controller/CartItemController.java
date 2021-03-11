package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Model.Product;


public class CartItemController {

    //Constructor
    public CartItemController() {

    }

//Create method
public void createNewCartItem(Product product) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;
        int ProductID = product.getProductID();
        int i;

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO order VALUES (?)");
                pstat.setInt(1, ProductID);
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



//Delete method
public void deleteCartItem(Product product) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        int productID = product.getProductID();
        int i = 0;		
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM cart WHERE ProductID = ?");	
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

    //Retrieve method
public DefaultTableModel retrieveCartTable() {
    // database URL
    final String DATABASE_URL = "jdbc:mysql://localhost/cms";
    DefaultTableModel model = new DefaultTableModel();
    

    model.addColumn("Brand");
    model.addColumn("Name");
    model.addColumn("Description");
    model.addColumn("Price");



    //---retrieve from database---//
    //-and populate table---//
    try {
        Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Products.Brand, Products.Name, Products.Description, Products.Price FROM Products INNER JOIN Cart ON Products.ProductID = Cart.ProductID");
        ResultSet Rs = pstm.executeQuery();
        while (Rs.next()) {
            model.addRow(
                    new Object[] { Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getDouble(4) });
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return model;
}




}

