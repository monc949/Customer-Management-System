package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Globals;
import Model.Order;
import Model.Product;

/**
 * Controller for the Order Table
 */
public class OrderController implements Globals {
    
    //Constructor
    public OrderController() {

    }




/** Creates a new record in the Order Table using an Order Object
 * @param newOrder
 */
public void createNewOrder(Order newOrder) {


        Connection connection = null;
        PreparedStatement pstat = null;

        
        
        int customerID = newOrder.getCustomerID();
        double totalPrice = newOrder.getTotalPrice();
       

        try {

            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Orders (CustomerID, TotalPrice) VALUES (?,?)");
                pstat.setInt(1, customerID);
                pstat.setDouble(2, totalPrice);

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

    /** Creates a new record in the OrderDetails Table using the product list form the order object passed in
 * @param orderID should be aquired using getLastOrderID()
 * @param newOrder
 */
    public void createNewOrderDetails(int orderID, Order newOrder) {
        Connection connection = null;
        PreparedStatement pstat = null;
        
        ArrayList<Product> productList = newOrder.getProductList();

        try {

            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            //create Prepared Statement for inserting into table
                for (Product product : productList) {
                        pstat = connection.prepareStatement("INSERT INTO OrderDetails(OrderID, ProductID, ProductName) VALUES (?,?,?)");
                        pstat.setInt(1, orderID);
                        pstat.setInt(2, product.getProductID());
                        pstat.setString(3, product.toString());
        
                        pstat.executeUpdate();
                }




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







/** Retrieves the Order Table in the form of a Table Model
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveOrderTable() {
                Connection connection = null;
                DefaultTableModel model = new DefaultTableModel();

	
                model.addColumn("OrderID");
                model.addColumn("CustomerID");
                model.addColumn("Order Date");
                model.addColumn("Total Price");
        
                //---retrieve from database---//
                //-and populate table---//
        
                try {
                    connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Orders");
                    ResultSet Rs = pstm.executeQuery();
                    while(Rs.next()){
                        model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getDouble(4)});
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }

/** Retrieves the Order Details Table in the form of a Table Model, showing only the record matching the order ID passed in
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveOrderDetailsTable(int orderID) {
                Connection connection = null;
                DefaultTableModel model = new DefaultTableModel();

	
                model.addColumn("OrderID");
                model.addColumn("ProductID");
                model.addColumn("Product Name");

        
                //---retrieve from database---//
                //-and populate table---//
        
                try {
                    connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM orderdetails WHERE OrderID = ?");
                    pstm.setInt(1, orderID);
                    ResultSet Rs = pstm.executeQuery();
                    while(Rs.next()){
                        model.addRow(new Object[]{Rs.getInt(2), Rs.getInt(3),Rs.getString(4)});
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }







/** Retrieves the Order Table in the form of a Table Model, filtered to show the Orders from a particular Customer. Requires an Customer ID number
 * @param id
 * @return DefaultTableModel
 */
public DefaultTableModel retrieveFilteredOrders(int id) {
                Connection connection = null;
                DefaultTableModel model = new DefaultTableModel();
    
                model.addColumn("OrderID");
                model.addColumn("CustomerID");
                model.addColumn("Order Date");
                model.addColumn("Total Price");
        
                //---retrieve from database---//
                //-and populate table---//
        
                try {
                    connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Orders WHERE CustomerID = ?");
                    pstm.setInt(1, id);
                    ResultSet Rs = pstm.executeQuery();
                    while(Rs.next()){
                        model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getDouble(4)});
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return model;
            }


/** Deletes a record from the Order Table. Requires an OrderID Number
 * @param orderID
 */
public void deleteOrder(int orderID) {
		
        Connection connection = null;
        PreparedStatement pstat = null;	
        try{
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

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


/** Returns the OrderID of the most recent Order record
 * @param orderID
 */
public int getLastOrderID() {
		
        Connection connection = null;
        int orderID = 0;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT OrderID FROM Orders");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                if(Rs.isLast()) {
                    orderID = Rs.getInt("OrderID");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            return orderID;
        }
    

}
