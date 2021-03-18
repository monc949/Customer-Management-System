package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CartItemController;
import Controller.CustomerController;
import Controller.OrderController;
import Controller.ProductController;
import Model.Customer;
import Model.Order;
import Model.Product;
import View.DatabaseView.Table;

public class MainView extends JFrame {
    private static final long serialVersionUID = 1L;

    ProductController pc = new ProductController();
    CustomerController cc = new CustomerController();
    OrderController oc = new OrderController();
    CartItemController cic = new CartItemController();

    // ---------Components--------------//
    JButton productDBButton = new JButton("Product Database");
    JButton orderDBButton = new JButton("Order Database");
    JButton customerDBButton = new JButton("Customer Database");

    JButton submitInvoiceButton = new JButton("Submit Invoice");
    JButton clearCartButton = new JButton("Clear Cart");
    JButton ATCButton = new JButton("Add to Cart");

    JLabel productSelectLabel = new JLabel("Select Products");
    JLabel CustomerSelectLabel = new JLabel("Select Customer");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    JComboBox<Customer> customerSelector = new JComboBox<Customer>(cc.retrieveCustomerList());

    JList<Product> productSelector = new JList<Product>(pc.retrieveProductList());

    // ---------Constructor-----------------//

    public MainView() {

        JFrame frame = new JFrame();
        JPanel sideMenu = new JPanel();
        JPanel topMenu = new JPanel();
        JPanel center = new JPanel();

        // -------Main Panel (frame)-----------//

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // -----------Center panel-------------//

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.lightGray);
        center.setVisible(true);

                // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

//---Container---//
        table.setModel(cic.retrieveCartTable());
        
        JScrollPane pg = new JScrollPane(table);
        center.add(pg, BorderLayout.CENTER);

        this.pack();

        // -------Side Panel-------//

        sideMenu.setPreferredSize(new Dimension(300, 0));
        sideMenu.setMinimumSize(new Dimension(200, 0));
        sideMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        sideMenu.setBackground(Color.lightGray);
        sideMenu.setVisible(true);



        // -------Customer Selection Box------//
        sideMenu.add(CustomerSelectLabel);
        sideMenu.add(customerSelector);




        // -----Product List------//
        sideMenu.add(productSelectLabel);

        productSelector.setPreferredSize(new Dimension(250, 700));

        JScrollPane productListContainer = new JScrollPane(productSelector);

        sideMenu.add(productListContainer);
        ATCButton.addActionListener(new ButtonHandler());
        sideMenu.add(ATCButton);



        // -----Buttons-----//
        submitInvoiceButton.addActionListener(new ButtonHandler());
        sideMenu.add(submitInvoiceButton);


        clearCartButton.addActionListener(new ButtonHandler());
        sideMenu.add(clearCartButton);



        // -----Top Panel-------//

        topMenu.setPreferredSize(new Dimension(0, 100));
        topMenu.setMinimumSize(new Dimension(0, 70));
        topMenu.setLayout(new FlowLayout());
        topMenu.setBackground(Color.lightGray);



        // -----Buttons-----//
        productDBButton.setBounds(0, 0, 90, 5);
        productDBButton.setFocusable(false);
        productDBButton.addActionListener(new ButtonHandler());
        topMenu.add(productDBButton);

        customerDBButton.setBounds(0, 0, 90, 5);
        customerDBButton.setFocusable(false);
        customerDBButton.addActionListener(new ButtonHandler());
        topMenu.add(customerDBButton);

        orderDBButton.setBounds(0, 0, 90, 5);
        orderDBButton.setFocusable(false);
        orderDBButton.addActionListener(new ButtonHandler());
        topMenu.add(orderDBButton);

        topMenu.setVisible(true);

        // -----Add components ------>
        frame.add(sideMenu, BorderLayout.WEST);
        frame.add(topMenu, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);

    }

    // ----------Action listener---------------//

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == productDBButton) {
                new Table(1);
            }
            if (e.getSource() == customerDBButton) {
                new Table(2);
            }
            if (e.getSource() == orderDBButton) {
                new Table(3);
            }

            if (e.getSource() == ATCButton) {
                cic.createNewCartItem(productSelector.getSelectedValue());
                table.setModel(cic.retrieveCartTable());

            }

            if (e.getSource() == submitInvoiceButton) {
                Customer customer = (Customer) customerSelector.getSelectedItem();
                int CustomerID = customer.getCustomerID();
                
                ArrayList<Product> cartItems = cic.retrieveCartItems();
                Order newOrder = new Order(CustomerID, cartItems);
                oc.createNewOrder(newOrder);
                cic.clearCart();
            }
            if (e.getSource() == clearCartButton) {
                cic.clearCart();
                table.setModel(cic.retrieveCartTable());
            }
        }
    }


  
}
