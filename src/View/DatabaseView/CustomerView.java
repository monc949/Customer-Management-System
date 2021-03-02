package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
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

public class CustomerView extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    CustomerController cc = new CustomerController();

    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel sidePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create Customer");
    JButton editButton = new JButton("Edit Customer");
    JButton loadButton = new JButton("Load Customer");
//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);

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

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Customer ID");
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
        setResizable(false);

//---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(cc.retrieve());
        
        // model.addColumn("CustomerID");
        // model.addColumn("FirstName");
        // model.addColumn("LastName");
        // model.addColumn("Address1");
        // model.addColumn("Address2");
        // model.addColumn("City");
        // model.addColumn("County");
        // model.addColumn("Postcode");
        // model.addColumn("Email");
        // model.addColumn("PhoneNumber");


        // //---retrieve from database---//
        // //-and populate table---//
        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver");

        //     final String DATABASE_URL = "jdbc:mysql://localhost/cms";
        //     Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11");

        //     PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customers");
        //     ResultSet Rs = pstm.executeQuery();
        //     while (Rs.next()) {
        //         model.addRow(
        //                 new Object[] { Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getString(5),
        //                         Rs.getString(6), Rs.getString(7), Rs.getString(8), Rs.getString(9), Rs.getString(10) });
        //     }
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
        JScrollPane pg = new JScrollPane(table);
        container.add(pg, BorderLayout.CENTER);

        this.pack();

        // -----------------Side panel-----------------//
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 345, 15));
        sidePanel.setVisible(true);



        //-----Text Fields-----////////

        IDField.setSize(12, 23);
        IDField.setMaximumSize(new Dimension(Integer.MAX_VALUE, IDField.getPreferredSize().height));
        IDField.setFont(font1);
        sidePanel.add(IDLabel);
        sidePanel.add(IDField);



        FNameField.setSize(12, 23);
        FNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, FNameField.getPreferredSize().height));
        FNameField.setFont(font1);
        sidePanel.add(FNameLabel);
        sidePanel.add(FNameField);


        LNameField.setSize(12, 23);
        LNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, LNameField.getPreferredSize().height));
        LNameField.setFont(font1);
        sidePanel.add(LNameLabel);
        sidePanel.add(LNameField);


        Address1Field.setSize(12, 23);
        Address1Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, Address1Field.getPreferredSize().height));
        Address1Field.setFont(font1);
        sidePanel.add(Address1Label);
        sidePanel.add(Address1Field);


        Address2Field.setSize(12, 23);
        Address2Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, Address2Field.getPreferredSize().height));
        Address2Field.setFont(font1);
        sidePanel.add(Address2Label);
        sidePanel.add(Address2Field);


        CityField.setSize(12, 23);
        CityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, CityField.getPreferredSize().height));
        CityField.setFont(font1);
        sidePanel.add(CityLabel);
        sidePanel.add(CityField);


        CountyField.setSize(12, 23);
        CountyField.setMaximumSize(new Dimension(Integer.MAX_VALUE, CountyField.getPreferredSize().height));
        CountyField.setFont(font1);
        sidePanel.add(CountyLabel);
        sidePanel.add(CountyField);


        PostcodeField.setSize(12, 23);
        PostcodeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, PostcodeField.getPreferredSize().height));
        PostcodeField.setFont(font1);
        sidePanel.add(PostcodeLabel);
        sidePanel.add(PostcodeField);


        EmailField.setSize(12, 23);
        EmailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, EmailField.getPreferredSize().height));
        EmailField.setFont(font1);
        sidePanel.add(EmailLabel);
        sidePanel.add(EmailField);


        PhoneNumberField.setSize(12, 23);
        PhoneNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, PhoneNumberField.getPreferredSize().height));
        PhoneNumberField.setFont(font1);
        sidePanel.add(PhoneNumberLabel);
        sidePanel.add(PhoneNumberField);




        //Buttons
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);

        sidePanel.add(instructionLabel);

        createButton.addActionListener(new ButtonHandler());
        createButton.setToolTipText("Enter customer info. Do not enter enter Customer ID. This auto generated");
        buttonPanel.add(createButton);

        editButton.addActionListener(new ButtonHandler());
        editButton.setToolTipText("Enter Customer ID and fill in new info");
        buttonPanel.add(editButton);

        sidePanel.add(buttonPanel);


        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);

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
