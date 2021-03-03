package View;

import java.awt.Dimension;

import javax.swing.*;

public class CartItem {

    private JPanel container = new JPanel();
    private JLabel itemName = new JLabel("Temp Name");
    private JLabel itemPrice = new JLabel("Temp Price");
    private JButton clearButton = new JButton("Clear Item");



    public CartItem(String selectedItem) {
        
        container.setPreferredSize(new Dimension(0,50));
        itemName.setText(selectedItem);
        container.add(itemName);
        container.add(itemPrice);
        container.add(clearButton);
    }


}
