import javax.swing.JFrame;

import View.ProductList;


public class Table {
    public Table() {
        JFrame frame = new ProductList();
        frame.setTitle("Swing Example");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}