package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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


import Controller.OrderController;

public class OrderView extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel updatePanel = new JPanel();
    JButton editButton = new JButton("Edit Order");
    JButton deleteButton = new JButton("Delete Order");

//FONT//
    Font font1 = new Font("SansSerif", Font.BOLD, 20);

    JTextField IDField = new JTextField();
    JTextField ProductListField = new JTextField();
    JTextField TotalPriceField = new JTextField();


    JLabel IDLabel = new JLabel("Customer ID (Type OrderID here and fill in the fields below)");
    JLabel ProductListLabel = new JLabel("Product List ");
    JLabel TotalPriceLabel = new JLabel("Total Price");
    
    public OrderView() {

        // ---------------Table----------------//

        // Make table uneditable

        table.setEnabled(false);
    //---Container---//
        container.setLayout(new BorderLayout());
        model.addColumn("OrderID");
        model.addColumn("CustomerID");
        model.addColumn("Order Date");
        model.addColumn("Product List");
        model.addColumn("Total Price");

        //---retrieve from database---//
        //-and populate table---//

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String DATABASE_URL = "jdbc:mysql://localhost/cms";
            Connection con = DriverManager.getConnection(DATABASE_URL, "root", "Knockbeg11" );


            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2),Rs.getDate(3),Rs.getString(4),Rs.getDouble(5)});
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
        
        IDField.setSize(1, 2);
        IDField.setMargin(new Insets(5,5,5,5));
        IDField.setFont(font1);
        updatePanel.add(IDLabel);
        updatePanel.add(IDField);

        ProductListField.setSize(10, 5);
        ProductListField.setFont(font1);
        updatePanel.add(ProductListLabel);
        updatePanel.add(ProductListField);

        TotalPriceField.setSize(10, 5);
        TotalPriceField.setFont(font1);
        updatePanel.add(TotalPriceLabel);
        updatePanel.add(TotalPriceField);



        //Action listeners
        editButton.addActionListener(new ButtonHandler());
        updatePanel.add(editButton);

        deleteButton.addActionListener(new ButtonHandler());
        updatePanel.add(editButton);

        
        //------Final Panel Placement--------//
        container.add(updatePanel, BorderLayout.EAST);


    }
    
    private class ButtonHandler implements ActionListener {
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

    }
}

@Override
public void actionPerformed(ActionEvent e) {

}
}
