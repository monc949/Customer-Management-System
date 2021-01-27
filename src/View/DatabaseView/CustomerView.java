package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CustomerController;
import Model.Customer;

public class CustomerView extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel updatePanel = new JPanel();
    JButton createButton = new JButton("Create Customer");
    JButton editButton = new JButton("Edit Customer");

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

        // ---------------Table----------------//

        // Make table uneditable
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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customers");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(
                        new Object[] { Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getString(5),
                                Rs.getString(6), Rs.getString(7), Rs.getString(8), Rs.getString(9), Rs.getString(10) });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(table);
        container.add(pg, BorderLayout.CENTER);

        this.pack();

        // -----------------Side panel-----------------//
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
        updatePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        updatePanel.setVisible(true);

        IDField.setSize(1, 5);
        updatePanel.add(IDField);

        FNameField.setSize(10, 5);
        updatePanel.add(FNameField);

        LNameField.setSize(10, 5);
        updatePanel.add(LNameField);

        Address1Field.setSize(10, 5);
        updatePanel.add(Address1Field);

        Address2Field.setSize(10, 5);
        updatePanel.add(Address2Field);

        CityField.setSize(10, 5);
        updatePanel.add(CityField);

        CountyField.setSize(10, 5);
        updatePanel.add(CountyField);

        PostcodeField.setSize(10, 5);
        updatePanel.add(PostcodeField);

        EmailField.setSize(10, 5);
        updatePanel.add(EmailField);

        PhoneNumberField.setSize(10, 5);
        updatePanel.add(PhoneNumberField);


        createButton.addActionListener(new ButtonHandler());
        updatePanel.add(createButton);


        updatePanel.add(editButton);

        container.add(updatePanel, BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        

    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CustomerController cc = new CustomerController();


         if (e.getSource()==editButton){
            cc.update(IDField.getText().parseInt(), //FIXME: in conjuction with customer controller create method
            FNameField.getText(), 
            LNameField.getText(), 
            Address1Field.getText(), 
            Address2Field.getText(), 
            CityField.getText(), 
            CountyField.getText(), 
            PostcodeField.getText(), 
            EmailField.getText(), 
            PhoneNumberField.getText());


         }
         if (e.getSource()==createButton){
            cc.create(new Customer(FNameField.getText(), 
            LNameField.getText(), 
            Address1Field.getText(), 
            Address2Field.getText(), 
            CityField.getText(), 
            CountyField.getText(), 
            PostcodeField.getText(), 
            EmailField.getText(), 
            PhoneNumberField.getText())
            );
            model.fireTableDataChanged();
         }
        }
    }
}
