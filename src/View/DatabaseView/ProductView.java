package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel updatePanel = new JPanel();
    JButton createButton = new JButton("Create Product");
    JButton editButton = new JButton("Edit Product");
    JButton deleteButton = new JButton("Delete Product");

    //FONT//
    Font font1 = new Font("SansSerif", Font.BOLD, 20);

    JTextField IDField = new JTextField();
    JTextField BrandField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField priceField = new JTextField();


    JLabel IDLabel = new JLabel("Product ID (Type OrderID here and fill in the fields below)");
    JLabel BrandLabel = new JLabel("Brand");
    JLabel nameLabel = new JLabel("Name");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel priceLabel = new JLabel("Price");
    
    public ProductView() {

        //----------Table----------//

        // Make table uneditable
        table.setEnabled(false);

  //---Container---//
        table.setEnabled(false);

        container.setLayout(new BorderLayout());

        model.addColumn("ProductID");
        model.addColumn("Brand");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Price");

        //---retrieve from database---//
        //-and populate table---//
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );


            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Products");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getDouble(5)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(table);
        container.add(pg);
        
        this.pack();

        // -----------------Side panel-----------------//
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
        updatePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(30, 90, 30, 30));
        updatePanel.setVisible(true);


               //----text fields---//
        
               IDField.setSize(10, 5);
               IDField.setFont(font1);
               updatePanel.add(IDLabel);
               updatePanel.add(IDField);
       
               BrandField.setSize(10, 5);
               BrandField.setFont(font1);
               updatePanel.add(BrandLabel);
               updatePanel.add(BrandField);
       
               nameField.setSize(10, 5);
               nameField.setFont(font1);
               updatePanel.add(nameLabel);
               updatePanel.add(nameField);
       
               descriptionField.setSize(10, 5);
               descriptionField.setFont(font1);
               updatePanel.add(descriptionLabel);
               updatePanel.add(descriptionField);
       
               priceField.setSize(10, 5);
               priceField.setFont(font1);
               updatePanel.add(priceLabel);
               updatePanel.add(priceField);

            //Action listeners
                createButton.addActionListener(new ButtonHandler());
                updatePanel.add(createButton);

                editButton.addActionListener(new ButtonHandler());
                updatePanel.add(editButton);

                deleteButton.addActionListener(new ButtonHandler());
                updatePanel.add(deleteButton);
                

           //------Final Panel Placement--------//
           container.add(updatePanel, BorderLayout.EAST);

    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ProductController pc = new ProductController();

         if (e.getSource()==editButton){
                pc.update(Integer.parseInt(IDField.getText()),
                BrandField.getText(),
                nameField.getText(),
                descriptionField.getText(),
                Double.parseDouble(priceField.getText()));
         }

         if (e.getSource()==createButton){
                pc.create(new Product(BrandField.getText(),
                nameField.getText(),
                descriptionField.getText(),
                Double.parseDouble(priceField.getText()))); 
         }

         if (e.getSource() == deleteButton) {
                pc.delete(Integer.parseInt(IDField.getText())); 

         }

    }
}

}
