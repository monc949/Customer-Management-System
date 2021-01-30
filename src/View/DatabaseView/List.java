package View.DatabaseView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import Controller.ProductController;

public class List extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    List() throws SQLException {
        setTitle("Product List");

        ProductController pc = new ProductController();
        DefaultListModel dlm = new DefaultListModel();

        ResultSet rs = pc.retrieve();
        List productList = new List();

        while(rs.next()){
                String product = rs.getString(2);
                productList.add(product);
             }

    }
}

//////////FIXME: cant get to work at all yet, 
//will eventually output to clickable list in main view to add items to invoice.