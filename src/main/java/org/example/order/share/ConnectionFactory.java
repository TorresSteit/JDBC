package org.example.order.share;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static boolean inited;

    public static Connection getConnection() throws SQLException {
        initialize();
        return dataSource.getConnection();
    }

    private static void initialize() {
        if (inited) return;

        try (var inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Cannot find 'database.properties' file in classpath");
            }

            Properties props = new Properties();
            props.load(inputStream);

            dataSource.setDriverClass(props.getProperty("db.driver"));
            dataSource.setJdbcUrl(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.password"));

            inited = true;
        } catch (Exception e) {
            // Использование логирования вместо e.printStackTrace()
            System.err.println("Failed to initialize database connection pool.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
