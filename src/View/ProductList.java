package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ProductList extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    public ProductList() {
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("ProductID");
        model.addColumn("Brand");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Price");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Products");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getDouble(5)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();

    }
}
