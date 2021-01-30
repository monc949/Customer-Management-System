package View.DatabaseView;



import javax.swing.JFrame;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class Table extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

        public Table(int i) {
            if (i == 1) {
                JFrame frame = new ProductView();
                frame.setTitle("Product Table");
                frame.setSize(1500, 900);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 


            if (i == 2) {
                JFrame frame = new CustomerView();
                frame.setTitle("Customer Table");
                frame.setSize(1500, 900);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
            
            if (i == 3) {
                JFrame frame = new OrderView();
                frame.setTitle("Order Table");
                frame.setSize(1500, 900);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 

            
    }



    
}