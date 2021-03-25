package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.OrderController;

/**
 * Order Database Manangement Window
 */
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
    JButton deleteButton = new JButton("Delete Order");

    JPanel searchPanel = new JPanel();

    
    JTextField IDField = new JTextField();
    JTextField TotalPriceField = new JTextField();


    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Order ID");
    JLabel TotalPriceLabel = new JLabel("Total Price");

    JLabel searchLabel = new JLabel("Filter by Customer");
	JTextField searchField = new JTextField();
    JButton filterButton = new JButton("Filter");
    JButton resetButton = new JButton("Reset Table");

    JLabel orderDetailFilterLabel = new JLabel("Show Order Details");
	JTextField orderDetailFilterField = new JTextField();
    JButton showOrderDetailsButton = new JButton("Show Details");

//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);

    
    public OrderView() {

        // ---------------Table----------------//

        // Make table uneditable

        table.setEnabled(false);
        setResizable(false);

    //---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(oc.retrieveOrderTable());

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
        IDField.setFont(font1);
        sidePanel.add(IDLabel);
        sidePanel.add(IDField);


        TotalPriceField.setSize(12, 23);
        TotalPriceField.setFont(font1);
        sidePanel.add(TotalPriceLabel);
        sidePanel.add(TotalPriceField);



        //Buttons
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);
        sidePanel.add(instructionLabel);

        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter OrderID only");
        buttonPanel.add(deleteButton);

        sidePanel.add(buttonPanel);


        //Search Panel
        searchPanel.setLayout(new GridLayout(7, 1 , 15, 1));
        searchPanel.setBackground(Color.lightGray);
        searchPanel.setVisible(true);
        searchPanel.add(searchLabel);

        searchPanel.add(searchField);

        filterButton.addActionListener(new ButtonHandler());
        filterButton.setToolTipText("Enter Customer ID");
        searchPanel.add(filterButton);

        searchPanel.add(orderDetailFilterLabel);
        searchPanel.add(orderDetailFilterField);

        showOrderDetailsButton.addActionListener(new ButtonHandler());
        showOrderDetailsButton.setToolTipText("Enter OrderID");
        searchPanel.add(showOrderDetailsButton);

        searchPanel.add(resetButton);
        resetButton.addActionListener(new ButtonHandler());
        
        sidePanel.add(searchPanel);


        
        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);


    }
    
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            OrderController oc = new OrderController();

         if (ae.getSource() == deleteButton) {
             try {
                oc.deleteOrder(Integer.parseInt(IDField.getText())); 
                table.setModel(oc.retrieveOrderTable());
             } catch (Exception e) {
                OrderView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");
             }
             finally{
                //Clear Fields after use
                IDField.setText("");
                TotalPriceField.setText("");
             }
           


         }

         if (ae.getSource() == filterButton) {
             try {
             table.setModel(oc.retrieveFilteredOrders(Integer.parseInt(searchField.getText())));
                 
             } catch (Exception e) {
                OrderView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");
             }
         }

         if (ae.getSource() == showOrderDetailsButton) {
             try {
                 table.setModel(oc.retrieveOrderDetailsTable(Integer.parseInt(orderDetailFilterField.getText())));
             } catch (Exception e) {
                OrderView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information"); 
             }
         }

         if (ae.getSource() == resetButton) {
             table.setModel(oc.retrieveOrderTable());
             searchField.setText("");
         }

    }
}

        

        public static void infoBox(String infoMessage, String titleBar)
        {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }



}
