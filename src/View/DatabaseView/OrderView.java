package View.DatabaseView;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class OrderView extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    
    public OrderView() {
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("OrderID");
        model.addColumn("CustomerID");
        model.addColumn("Order Date");
        model.addColumn("Product List");
        model.addColumn("Total Price");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );


            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getString(4),Rs.getDouble(5)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(table);
        container.add(pg);
        
        this.pack();

    }
}
