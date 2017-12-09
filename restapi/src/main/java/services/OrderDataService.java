package services;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDataService {

    private List<Order> orderList;

    private static OrderDataService serviceInstance = new OrderDataService();

    public static OrderDataService getInstance(){
        return serviceInstance;
    }

    private OrderDataService(){
        orderList = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            Order order = new Order(i);
            order.setDesc("Order with id: " + i);
            orderList.add(order);
        }

    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order){
        long newId = orderList.size() + 1;
        order.setId(newId);
        orderList.add(order);
    }
}
