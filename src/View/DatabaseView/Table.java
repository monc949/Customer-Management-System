package View.DatabaseView;

import javax.swing.JFrame;




public class Table {

        public Table(int i) {
            if (i == 1) {
                JFrame frame = new ProductView();
                frame.setTitle("Product Table");
                frame.setSize(900, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 


            if (i == 2) {
                JFrame frame = new CustomerView();
                frame.setTitle("Customer Table");
                frame.setSize(900, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
            
            if (i == 3) {
                JFrame frame = new OrderView();
                frame.setTitle("Order Table");
                frame.setSize(900, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 

            if (i == 4) {
                OrderView panel = new OrderView();
                panel.setName("Order Table");
                panel.setVisible(true);
            } 

            


    }



    
}