package View;

import javax.swing.JFrame;



public class Table {
        public Table() {
            JFrame frame = new ProductView();
            frame.setTitle("Product Table");
            frame.setSize(900, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}