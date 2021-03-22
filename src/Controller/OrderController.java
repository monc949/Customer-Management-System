package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Model.Order;

public class OrderController {
    
    //Constructor
    public OrderController() {

    }




/** 
 * @param newOrder
 */
public void createNewOrder(Order newOrder) {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/cms";

        Connection connection = null;
        PreparedStatement pstat = null;

        
        
        int customerID = newOrder.getCustomerID();
        double totalPrice = newOrder.getTotalPrice();
        String productList = newOrder.toString();
       

        try {

            //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Orders (CustomerID, ProductList, TotalPrice) VALUES (?,?,?)");
                pstat.setInt(1, customerID);
                pstat.setString(2, productList);
                pstat.setDouble(3, totalPrice);

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





/** 
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveOrderTable() {
        		// database URL
		        final String DATABASE_URL = "jdbc:mysql://localhost/cms";
                DefaultTableModel model = new DefaultTableModel();

	
                model.addColumn("OrderID");
                model.addColumn("CustomerID");
                model.addColumn("Order Date");
                model.addColumn("Product List");
                model.addColumn("Total Price");
        
                //---retrieve from database---//
                //-and populate table---//
        
                try {
                    Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );
        
                    PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders");
                    ResultSet Rs = pstm.executeQuery();
                    while(Rs.next()){
                        model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getString(4),Rs.getDouble(5)});
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }







/** 
 * @param id
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveFilteredOrders(int id) {
        		// database URL
		        final String DATABASE_URL = "jdbc:mysql://localhost/cms";
                DefaultTableModel model = new DefaultTableModel();
    
                model.addColumn("OrderID");
                model.addColumn("CustomerID");
                model.addColumn("Order Date");
                model.addColumn("Product List");
                model.addColumn("Total Price");
        
                //---retrieve from database---//
                //-and populate table---//
        
                try {
                    Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );
        
                    PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders WHERE CustomerID = ?");
                    pstm.setInt(1, id);
                    ResultSet Rs = pstm.executeQuery();
                    while(Rs.next()){
                        model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getString(4),Rs.getDouble(5)});
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }


                


/** 
 * @param orderID
 * @param productList
 * @param totalPrice
 */
public void updateOrder(int orderID, String productList, double totalPrice) { 
        // database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		

        Connection connection = null;
        PreparedStatement pstat = null;
        
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Knockbeg11" );
            
            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE Orders SET ProductList = ?, TotalPrice = ? Where orderID = ?");
            pstat.setString(1, productList);
            pstat.setDouble(2, totalPrice);
            pstat.setInt(3, orderID);

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





/** 
 * @param orderID
 */
public void deleteOrder(int orderID) {
        	// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/cms";
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Orders WHERE OrderID = ?");	
            pstat.setInt(1, orderID);		
            
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
