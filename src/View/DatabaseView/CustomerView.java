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
    JTextField IDField = new JTextField("ID", 20);
    JTextField FNameField = new JTextField("First Name", 20);
    JTextField LNameField = new JTextField("Last Name", 20);
    JTextField Address1Field = new JTextField("Address Line 1", 20);
    JTextField Address2Field = new JTextField("Address Line 2", 20);
    JTextField CityField = new JTextField("City", 20);
    JTextField CountyField = new JTextField("County", 20);
    JTextField PostcodeField = new JTextField("PostCode", 20);
    JTextField EmailField = new JTextField("Email", 20);
    JTextField PhoneNumberField = new JTextField("Phone Number", 20);
    
    public CustomerView() {


        //---------------Table----------------//

        //Make table uneditable
        table.setEnabled(false);

        container.setLayout(new BorderLayout());
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

        //----------Set Column Width-----------//

       // table.getColumnModel().getColumn(0).setPreferredWidth(5); //FIXME:

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
        container.add(pg, BorderLayout.WEST);
        
        this.pack();



        //-----------------Side panel-----------------//
        updatePanel.setLayout(new FlowLayout());
        updatePanel.setVisible(true);

        FNameField.setSize(50, 20);
        updatePanel.add(FNameField);

        LNameField.setSize(50,20);
        updatePanel.add(LNameField);




        container.add(updatePanel, BorderLayout.EAST);




    }
}
