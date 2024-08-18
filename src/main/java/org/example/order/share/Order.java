package org.example.order.share;

import lombok.Data;

@Data
public class Order {
    @Id
    private int orderId; // ID заказа, primary key
    private int clientId; // ID клиента
    private String clientName;
    private double price;
    private String orderDetails;
}
