package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    JLabel ProductSelectLabel = new JLabel("Select Products");

    JComboBox<Object> customerSelector = new JComboBox<Object>(cc.retrieveCustomerList());


    // ---------Constructor-----------------//

    public MainView() {


        JFrame frame = new JFrame();
        JPanel sideMenu = new JPanel();
        JPanel topMenu = new JPanel();
        JPanel center = new JPanel();


     // -------Main Panel (frame)-----------//

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


    //-----------Center panel-------------//

        center.setLayout(new FlowLayout());
        center.setBackground(Color.CYAN);
        center.setVisible(true);

       





    //-------Side Panel-------//
        
        sideMenu.setPreferredSize(new Dimension(500,0));
        sideMenu.setMinimumSize(new Dimension(300,0));
        sideMenu.setLayout(new FlowLayout());
        sideMenu.setBackground(Color.GRAY);
        sideMenu.setVisible(true);

                        //-------Customer Selection Box------//
                        customerSelector.setBounds(150, 450, 500, 75);
                        
                        sideMenu.add(customerSelector);

                        //-----Product List------//
                        ProductSelectLabel.setForeground(Color.white);
                        ProductSelectLabel.setSize(new Dimension(500, 0));
                        sideMenu.add(ProductSelectLabel);

                        JList<String> productList = new JList<String>();
                        productList.setModel(pc.retrieveProductList());
                        productList.setPreferredSize(new Dimension(250,700));

                        JScrollPane productListContainer = new JScrollPane(productList);

                        sideMenu.add(productListContainer);

                

        
    //-----Top Panel-------//
        
        topMenu.setPreferredSize(new Dimension(0, 50));
        topMenu.setMinimumSize(new Dimension(0, 30));
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
        }
    }


  
}
