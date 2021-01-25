package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.DatabaseView.Table;

public class MainView extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //---------Components--------------//
    JButton productDBButton = new JButton("Product Database");
    JButton orderDBButton = new JButton("Order Database");
    JButton customerDBButton = new JButton("Customer Database");

    // ---------Constructor-----------------//

    public MainView() {

        JFrame frame = new JFrame();
        JPanel sideMenu = new JPanel();
        JPanel topMenu = new JPanel();


     // -------Main Panel-----------//

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1020, 680);
        frame.setState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);


    //-------Side Panel-------//
        
        sideMenu.setPreferredSize(new Dimension(300,0));
        sideMenu.setMinimumSize(new Dimension(150,0));
        sideMenu.setBackground(Color.GRAY);
        sideMenu.setVisible(true);
        

        
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



    //--------Center Panel-------////





    //-----Add components ------>
        frame.add(sideMenu, BorderLayout.WEST);
        frame.add(topMenu, BorderLayout.NORTH);

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
