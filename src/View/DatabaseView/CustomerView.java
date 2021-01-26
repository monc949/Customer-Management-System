package View.DatabaseView;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CustomerView extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel updatePanel = new JPanel();
    JButton submitButton = new JButton();
    JTextField nameField = new JTextField("Name", 20);
    
    public CustomerView() {


        //---------------Table----------------//

        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("CustomerID");
        model.addColumn("FirstName");
        model.addColumn("LastName");
        model.addColumn("Address1");
        model.addColumn("Address2");
        model.addColumn("City");
        model.addColumn("County");
        model.addColumn("Postcode");
        model.addColumn("Email");
        model.addColumn("PhoneNumber");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );


            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customers");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)
                ,Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9),Rs.getString(10)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(table);
        container.add(pg);
        
        this.pack();



        //-----------------Side panel-----------------//
        updatePanel.setLayout(new FlowLayout());
        updatePanel.setVisible(true);

        nameField.setSize(50, 20);
        updatePanel.add(nameField);


        container.add(updatePanel, BorderLayout.EAST);




    }
}
