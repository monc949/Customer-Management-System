import Controller.*;
import Model.*;


public class App {
    public static void main(String[] args) {
        Product item1 = new Product("Sony", "K400", "TV", 50);
        
        Product[] list = new Product[1];
        list[0] = item1;

        Order order = new Order(1,list);

        OrderController orderController = new OrderController();
        orderController.create(order);


        


    }
}
