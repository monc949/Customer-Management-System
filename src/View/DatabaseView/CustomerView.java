package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.JLabel;
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
    JButton loadButton = new JButton("Load Customer");
//FONT//
    Font font1 = new Font("SansSerif", Font.BOLD, 20);

    JTextField IDField = new JTextField();
    JTextField FNameField = new JTextField();
    JTextField LNameField = new JTextField();
    JTextField Address1Field = new JTextField();
    JTextField Address2Field = new JTextField();
    JTextField CityField = new JTextField();
    JTextField CountyField = new JTextField();
    JTextField PostcodeField = new JTextField();
    JTextField EmailField = new JTextField();
    JTextField PhoneNumberField = new JTextField();


    JLabel IDLabel = new JLabel("Customer ID (Type Customers ID here and fill in the fields below)");
    JLabel FNameLabel = new JLabel("First Name");
    JLabel LNameLabel = new JLabel("Last Name");
    JLabel Address1Label = new JLabel("Address1");
    JLabel Address2Label = new JLabel("Address2");
    JLabel CityLabel = new JLabel("City");
    JLabel CountyLabel = new JLabel("County");
    JLabel PostcodeLabel = new JLabel("Post Code");
    JLabel EmailLabel = new JLabel("Email");
    JLabel PhoneNumberLabel = new JLabel("Phone Number");

    public CustomerView() {

        // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
//---Container---//
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


        //---retrieve from database---//
        //-and populate table---//
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
        updatePanel.setBorder(BorderFactory.createEmptyBorder(30, 90, 30, 30));
        updatePanel.setVisible(true);



        //-----Text Fields-----////////

        IDField.setSize(1, 2);
        IDField.setMargin(new Insets(5,5,5,5));
        IDField.setFont(font1);
        updatePanel.add(IDLabel);
        updatePanel.add(IDField);



        FNameField.setSize(10, 5);
        FNameField.setFont(font1);
        updatePanel.add(FNameLabel);
        updatePanel.add(FNameField);


        LNameField.setSize(10, 5);
        LNameField.setFont(font1);
        updatePanel.add(LNameLabel);
        updatePanel.add(LNameField);


        Address1Field.setSize(10, 5);
        Address1Field.setFont(font1);
        updatePanel.add(Address1Label);
        updatePanel.add(Address1Field);


        Address2Field.setSize(10, 5);
        Address2Field.setFont(font1);
        updatePanel.add(Address2Label);
        updatePanel.add(Address2Field);


        CityField.setSize(10, 5);
        CityField.setFont(font1);
        updatePanel.add(CityLabel);
        updatePanel.add(CityField);


        CountyField.setSize(10, 5);
        CountyField.setFont(font1);
        updatePanel.add(CountyLabel);
        updatePanel.add(CountyField);


        PostcodeField.setSize(10, 5);
        PostcodeField.setFont(font1);
        updatePanel.add(PostcodeLabel);
        updatePanel.add(PostcodeField);


        EmailField.setSize(10, 5);
        EmailField.setFont(font1);
        updatePanel.add(EmailLabel);
        updatePanel.add(EmailField);


        PhoneNumberField.setSize(10, 5);
        PhoneNumberField.setFont(font1);
        updatePanel.add(PhoneNumberLabel);
        updatePanel.add(PhoneNumberField);




        //Action listeners
        createButton.addActionListener(new ButtonHandler());
        updatePanel.add(createButton);

        editButton.addActionListener(new ButtonHandler());
        updatePanel.add(editButton);


        //------Final Panel Placement--------//
        container.add(updatePanel, BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        

    }


    //-----Both of these next methods control the create and edit requirements for customer database------///////


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CustomerController cc = new CustomerController();

         if (e.getSource()==editButton){
            cc.update(Integer.parseInt(IDField.getText()),
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
         }


        }
    }
    



}
