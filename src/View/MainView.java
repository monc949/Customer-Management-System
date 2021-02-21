package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import Controller.CustomerController;
import Controller.OrderController;
import Controller.ProductController;
import View.DatabaseView.Table;

public class MainView extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ProductController pc = new ProductController();
    CustomerController cc = new CustomerController();
    OrderController oc = new OrderController();

    

    //---------Components--------------//
    JButton productDBButton = new JButton("Product Database");
    JButton orderDBButton = new JButton("Order Database");
    JButton customerDBButton = new JButton("Customer Database");

    JButton submitInvoiceButton = new JButton("Submit Invoice");
    JButton clearListButton = new JButton("Clear List");

    JLabel productSelectLabel = new JLabel("Select Products");
    JLabel CustomerSelectLabel = new JLabel("Select Customer");

    JLabel productListLabel = new JLabel("Product List");

    JComboBox<Object> customerSelector = new JComboBox<Object>(cc.retrieveCustomerList());
    JList<String> productSelector = new JList<String>(pc.retrieveProductList());

    JList<String> productList = new JList<String>();



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


    //-----------Center panel-------------//

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.CYAN);
        center.setVisible(true);

        productList.setPreferredSize(new Dimension(500, 500));
        productList.setAlignmentX(TOP_ALIGNMENT);
        
        center.add(productListLabel);
        center.add(productList);


       


    //-------Side Panel-------//
        
        sideMenu.setPreferredSize(new Dimension(300,0));
        sideMenu.setMinimumSize(new Dimension(200,0));
        sideMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        sideMenu.setBackground(Color.GRAY);
        sideMenu.setVisible(true);

                        //-------Customer Selection Box------//
                        CustomerSelectLabel.setForeground(Color.white);
                        sideMenu.add(CustomerSelectLabel, BorderLayout.NORTH);
                        sideMenu.add(customerSelector, BorderLayout.NORTH);




                        //-----Product List------//

                        productSelectLabel.setForeground(Color.white);
                        sideMenu.add(productSelectLabel);

                        productSelector.setPreferredSize(new Dimension(150,700));

                        JScrollPane productListContainer = new JScrollPane(productSelector);

                        sideMenu.add(productListContainer);
                        pack();


                        //-----Buttons-----//
                        
                        sideMenu.add(submitInvoiceButton);
                        sideMenu.add(clearListButton);

                

        
    //-----Top Panel-------//
        
        topMenu.setPreferredSize(new Dimension(0, 100));
        topMenu.setMinimumSize(new Dimension(0, 70));
        topMenu.setLayout(new FlowLayout());
        topMenu.setBackground(Color.GRAY);

                        //-----Buttons-----//
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



    //-----Add components ------>
        frame.add(sideMenu, BorderLayout.WEST);
        frame.add(topMenu, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);

    }





    //----------Action listener---------------//

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource()==productDBButton) {
                    new Table(1);
            }
            if (e.getSource()==customerDBButton) {
                    new Table(2);
            }
            if (e.getSource()==orderDBButton) {
                    new Table(3);
            }



            if (e.getSource()==submitInvoiceButton) {
                //TODO:
            }
            if (e.getSource()==clearListButton) {
                //TODO:
            }
        }
    }


  
}
