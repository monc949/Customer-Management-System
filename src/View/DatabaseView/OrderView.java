package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

import Controller.OrderController;

public class OrderView extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    OrderController oc = new OrderController();
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel sidePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton editButton = new JButton("Edit Order");
    JButton deleteButton = new JButton("Delete Order");

    JPanel searchPanel = new JPanel();

    
    JTextField IDField = new JTextField();
    JTextField ProductListField = new JTextField();
    JTextField TotalPriceField = new JTextField();


    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Order ID");
    JLabel ProductListLabel = new JLabel("Product List ");
    JLabel TotalPriceLabel = new JLabel("Total Price");

    JLabel searchLabel = new JLabel("Filter by Customer");
	JTextField searchField = new JTextField();
    JButton searchButton = new JButton("Filter");

//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);

    
    public OrderView() {

        // ---------------Table----------------//

        // Make table uneditable

        table.setEnabled(false);
        setResizable(false);
    //---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(oc.retrieve());

        JScrollPane pg = new JScrollPane(table);
        container.add(pg);
        
        this.pack();

        
        // -----------------Side panel-----------------//
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 445, 15));
        sidePanel.setVisible(true);



        //----text fields---//
        
        IDField.setSize(12, 23);
        IDField.setMaximumSize(new Dimension(Integer.MAX_VALUE, IDField.getPreferredSize().height));
        IDField.setFont(font1);
        sidePanel.add(IDLabel);
        sidePanel.add(IDField);

        ProductListField.setSize(12, 23);
        ProductListField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ProductListField.getPreferredSize().height));
        ProductListField.setFont(font1);
        sidePanel.add(ProductListLabel);
        sidePanel.add(ProductListField);

        TotalPriceField.setSize(12, 23);
        TotalPriceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, TotalPriceField.getPreferredSize().height));
        TotalPriceField.setFont(font1);
        sidePanel.add(TotalPriceLabel);
        sidePanel.add(TotalPriceField);



        //Buttons
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);
        sidePanel.add(instructionLabel);

        editButton.addActionListener(new ButtonHandler());
        editButton.setToolTipText("Enter Order ID and enter new info.");
        buttonPanel.add(editButton);

        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter OrderID only");
        buttonPanel.add(deleteButton);

        sidePanel.add(buttonPanel);


        //Search Panel
        searchPanel.setLayout(new GridLayout(3, 1 , 15, 1));
        searchPanel.setBackground(Color.lightGray);
        searchPanel.setVisible(true);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        sidePanel.add(searchPanel);
        
        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);


    }
    
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            OrderController oc = new OrderController();

         if (e.getSource()==editButton){
            oc.update(Integer.parseInt(IDField.getText()),
            ProductListField.getText(), 
            Double.parseDouble(TotalPriceField.getText())); 


         }

         if (e.getSource() == deleteButton) {
            oc.delete(Integer.parseInt(IDField.getText())); 


         }
         if (e.getSource() == searchButton) {
             //TODO:
         }

    }
}



}
