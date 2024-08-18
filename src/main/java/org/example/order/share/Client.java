package org.example.order.share;

import lombok.Data;

@Data
public class Client {
    @Id
    private  Long id;
    private  String name;
    private  int age;

}
