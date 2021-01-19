import Controller.*;
import Model.*;


public class App {
    public static void main(String[] args) {
        CustomerController cc = new CustomerController();
        // OrderController oc = new OrderController();
        // cc.create(new Customer("John", "Maye", "35 The Belfry", "Chapel Lane", "Thomastown", "Kilkenny", "R95RX60", "ciaranmaye8@gmail.com", "0871044880"));


        // Product item1 = new Product("Sony", "K400", "TV", 50);
        // Product item2 = new Product("Apple", "iPhone12", "Phone", 100);
        // Product item3 = new Product("Asus", "TP412UA", "Laptop", 200);
        
        // Product[] list = new Product[3];
        // list[0] = item1;
        // list[1] = item2;
        // list[2] = item3;

        // Order order = new Order(1,list);

        // OrderController orderController = new OrderController();
        // orderController.create(order);

        // cc.retrieve();
        // oc.retrieve();
        cc.retrieve(1);

        


    }
}
