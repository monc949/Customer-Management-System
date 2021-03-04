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
import javax.swing.SwingConstants;
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
    JButton deleteButton = new JButton("Delete Customer");


//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);


//Fields and Labels

    JTextField IDField = new JTextField();
    JTextField NameField = new JTextField();
    JTextField AddressField = new JTextField();
    JTextField PostcodeField = new JTextField();
    JTextField EmailField = new JTextField();
    JTextField PhoneNumberField = new JTextField();

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Customer ID");
    JLabel NameLabel = new JLabel("Name");
    JLabel AddressLabel = new JLabel("Address");
    JLabel PostcodeLabel = new JLabel("PostCode");
    JLabel EmailLabel = new JLabel("Email");
    JLabel PhoneNumberLabel = new JLabel("Phone Number");

    public CustomerView() {

        // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

//---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(cc.retrieveCustomerTable());
        
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


        NameField.setSize(12, 23);
        NameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, NameField.getPreferredSize().height));
        NameField.setFont(font1);
        sidePanel.add(NameLabel);
        sidePanel.add(NameField);


        AddressField.setSize(12, 23);
        AddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, AddressField.getPreferredSize().height));
        AddressField.setFont(font1);
        sidePanel.add(AddressLabel);
        sidePanel.add(AddressField);


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
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);

        sidePanel.add(instructionLabel);

        createButton.addActionListener(new ButtonHandler());
        createButton.setToolTipText("Enter customer info. Do not enter enter Customer ID. This will auto-generated");
        buttonPanel.add(createButton);

        editButton.addActionListener(new ButtonHandler());
        editButton.setToolTipText("Enter Customer ID and fill in new customer info");
        buttonPanel.add(editButton);

        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter Customer ID only to delete ");
        buttonPanel.add(deleteButton);

        sidePanel.add(buttonPanel);


        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);

    }


    //-----Both of these next methods control the create and edit requirements for customer database------///////


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CustomerController cc = new CustomerController();

        if (e.getSource()==createButton){
            cc.createNewCustomer(new Customer(NameField.getText(), 
            AddressField.getText(), 
            PostcodeField.getText(), 
            EmailField.getText(), 
            PhoneNumberField.getText()));
            table.setModel(cc.retrieveCustomerTable());


        //Clear Fields after use
            NameField.setText(""); 
            AddressField.setText("");
            PostcodeField.setText("");
            EmailField.setText("");
            PhoneNumberField.setText("");

        }


         if (e.getSource()==editButton){
                cc.updateCustomer(Integer.parseInt(IDField.getText()),
                NameField.getText(), 
                AddressField.getText(), 
                PostcodeField.getText(), 
                EmailField.getText(), 
                PhoneNumberField.getText());


            //Clear Fields
                IDField.setText("");
                NameField.setText(""); 
                AddressField.setText("");
                PostcodeField.setText("");
                EmailField.setText("");
                PhoneNumberField.setText("");
                table.setModel(cc.retrieveCustomerTable());

         }

         if (e.getSource()==deleteButton){
                cc.deleteCustomer(Integer.parseInt(IDField.getText()));


            //Clear Fields
                IDField.setText(""); 
                table.setModel(cc.retrieveCustomerTable());

         }




        }
    }
    



}
