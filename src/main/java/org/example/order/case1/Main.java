package org.example.order.case1;

import org.example.order.share.Client;
import org.example.order.share.ConnectionFactory;
import org.example.order.share.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection();
             Scanner sc = new Scanner(System.in)) {

            ClientDAOImpl dao = new ClientDAOImpl(conn);
            dao.createTable();

            OrderDAO odao = new OrderDAOImpl(conn);
            odao.createOrderTable();

            while (true) {
                System.out.println("1: add client");
                System.out.println("2: view clients");
                System.out.println("3: view count");

                System.out.println("6: add order");
                System.out.println("7: view orders");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        System.out.print("Enter client name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter client age: ");
                        String sAge = sc.nextLine();
                        int age = Integer.parseInt(sAge);

                        dao.addClient(name, age);
                        break;

                    case "2":
                        List<Client> list = dao.getAll();
                        for (Client client : list) {
                            System.out.println(client);
                        }
                        break;

                    case "3":
                        System.out.println("Count: " + dao.count());
                        break;

                    case "6":
                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter client name: ");
                        String clientName = sc.nextLine();
                        System.out.print("Enter order sum: ");
                        float orderSum = Float.parseFloat(sc.nextLine());
                        System.out.print("Enter order details: ");
                        String orderDetails = sc.nextLine();

                        odao.addOrder(clientId, clientName, orderSum, orderDetails);
                        break;

                    case "7":
                        List<Order> ordersList = odao.getAllOrder(); // Исправлено название метода
                        for (Order order : ordersList) {
                            System.out.println(order);
                        }
                        break;

                    default:
                        System.out.println("Exiting...");
                        return;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println("Invalid number format: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("An unexpected error occurred: " + ex.getMessage());
        }
    }
}


