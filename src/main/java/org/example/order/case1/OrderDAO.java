package org.example.order.case1;

import org.example.order.share.Order;

import java.util.List;

public interface OrderDAO {
    void createOrderTable();
    void addOrder(int clientId, String clientName, float orderSum, String orderDetails);
    List<Order> getAllOrder();
    long count();
}
