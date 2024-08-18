package org.example.order.case1;

import org.example.order.share.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final Connection conn;

    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createOrderTable() {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Orders");
            st.execute("CREATE TABLE Orders(orderId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "clientId INT, clientName VARCHAR(20), orderSum FLOAT, orderDetails VARCHAR(100))");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addOrder(int clientId, String clientName, float orderSum, String orderDetails) {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO Orders (clientId, clientName, orderSum, orderDetails) VALUES(?, ?, ?, ?)")) {
            st.setInt(1, clientId);
            st.setString(2, clientName);
            st.setFloat(3, orderSum);
            st.setString(4, orderDetails);
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> res = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM Orders")) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setClientId(rs.getInt("clientId"));
                    order.setClientName(rs.getString("clientName"));

                    order.setOrderDetails(rs.getString("orderDetails"));
                    res.add(order);
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Order> getAllOrders() {
        List<Order> res = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM Orders")) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setClientId(rs.getInt("clientId"));
                    order.setClientName(rs.getString("clientName"));

                    order.setOrderDetails(rs.getString("orderDetails"));
                    res.add(order);
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public long count() {
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Orders")) {
                if (rs.next())
                    return rs.getLong(1);
                else
                    throw new RuntimeException("Count failed");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
