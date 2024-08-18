package org.example.order.case1;

import org.example.order.share.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl  {

    private final Connection conn;

    public ClientDAOImpl(Connection conn) {
        this.conn = conn;
    }


    public void createTable() {
        try {
            try (Statement st = conn.createStatement()) {
                st.execute("DROP TABLE IF EXISTS Clients");
                st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void addClient(String name, int age) {
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO Clients (name, age) VALUES(?, ?)")) {
                st.setString(1, name);
                st.setInt(2, age);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Client> getAll() {
        List<Client> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM Clients")) {
                    while (rs.next()) {
                        Client client = new Client();

                        client.setId(rs.getLong(1));
                        client.setName(rs.getString(2));
                        client.setAge(rs.getInt(3));

                        res.add(client);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public long count() {
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Clients")) {
                    if (rs.next())
                        return rs.getLong(1);
                    else
                        throw new RuntimeException("Count failed");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
