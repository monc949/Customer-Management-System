package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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

import Controller.ProductController;
import Model.Product;

public class ProductView extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    ProductController pc = new ProductController();
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable();
    JPanel sidePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create Product");
    JButton editButton = new JButton("Edit Product");
    JButton deleteButton = new JButton("Delete Product");

    //FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);

    JTextField IDField = new JTextField();
    JTextField brandField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField priceField = new JTextField();

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");
    JLabel IDLabel = new JLabel("Product ID");
    JLabel brandLabel = new JLabel("Brand");
    JLabel nameLabel = new JLabel("Name");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel priceLabel = new JLabel("Price");
    
    public ProductView() {

        //----------Table----------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

  //---Container---//

        container.setLayout(new BorderLayout());
        setResizable(false);
        table.setModel(pc.retrieve());
        
        JScrollPane pg = new JScrollPane(table);
        container.add(pg);
        
        this.pack();

        // -----------------Side panel-----------------//
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 345, 15));
        sidePanel.setVisible(true);


               //----text fields---//
        
               IDField.setPreferredSize(new Dimension(15, 23));
               IDField.setMaximumSize(new Dimension(Integer.MAX_VALUE, IDField.getPreferredSize().height));
               IDField.setFont(font1);
               sidePanel.add(IDLabel);
               sidePanel.add(IDField);
       
               brandField.setPreferredSize(new Dimension(12, 23));
               brandField.setMaximumSize(new Dimension(Integer.MAX_VALUE, brandField.getPreferredSize().height));
               brandField.setFont(font1);
               sidePanel.add(brandLabel);
               sidePanel.add(brandField);
       
               nameField.setPreferredSize(new Dimension(12, 23));
               nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
               nameField.setFont(font1);
               sidePanel.add(nameLabel);
               sidePanel.add(nameField);
       
               descriptionField.setPreferredSize(new Dimension(12, 23));
               descriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, descriptionField.getPreferredSize().height));
               descriptionField.setFont(font1);
               sidePanel.add(descriptionLabel);
               sidePanel.add(descriptionField);
       
               priceField.setPreferredSize(new Dimension(12, 23));
               priceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, priceField.getPreferredSize().height));
               priceField.setFont(font1);
               sidePanel.add(priceLabel);
               sidePanel.add(priceField);





            //Buttons
                buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
                buttonPanel.setBackground(Color.lightGray);
                sidePanel.add(instructionLabel);

                createButton.addActionListener(new ButtonHandler());
                createButton.setToolTipText("Do Not Enter Product ID when creating new Product");
                buttonPanel.add(createButton);

                editButton.addActionListener(new ButtonHandler());
                editButton.setToolTipText("Enter Product ID and fill in new product info");
                buttonPanel.add(editButton);

                deleteButton.addActionListener(new ButtonHandler());
                deleteButton.setToolTipText("Enter Product ID only to delete");
                buttonPanel.add(deleteButton);

                sidePanel.add(buttonPanel);
                

           //------Final Panel Placement--------//
           container.add(sidePanel, BorderLayout.EAST);

    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ProductController pc = new ProductController();

         if (e.getSource()==editButton){
                pc.update(Integer.parseInt(IDField.getText()),
                brandField.getText(),
                nameField.getText(),
                descriptionField.getText(),
                Double.parseDouble(priceField.getText()));
                table.setModel(pc.retrieve());

              //Clear Fields after use
                IDField.setText("");
                brandField.setText("");
                nameField.setText("");
                descriptionField.setText("");
                priceField.setText("");

         }

         if (e.getSource()==createButton){
                pc.create(new Product(brandField.getText(),
                nameField.getText(),
                descriptionField.getText(),
                Double.parseDouble(priceField.getText())));
                table.setModel(pc.retrieve());

                //Clear Fields after use
                IDField.setText("");
                brandField.setText("");
                nameField.setText("");
                descriptionField.setText("");
                priceField.setText("");
         }

         if (e.getSource() == deleteButton) {
                pc.delete(Integer.parseInt(IDField.getText())); 
                table.setModel(pc.retrieve());

                //Clear Fields after use
                IDField.setText("");
                brandField.setText("");
                nameField.setText("");
                descriptionField.setText("");
                priceField.setText("");


         }

    }
}

}
