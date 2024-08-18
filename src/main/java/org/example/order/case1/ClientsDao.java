package org.example.order.case1;

import org.example.order.share.Client;

import java.util.List;

public interface ClientsDao {
    void createTable();
    void  addClient(String firstName, int age);
List<Client> getClients();
    long count();
}
