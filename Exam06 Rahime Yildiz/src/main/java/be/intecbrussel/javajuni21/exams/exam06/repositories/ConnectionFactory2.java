package be.intecbrussel.javajuni21.exams.exam06.repositories;

import java.sql.*;

public class ConnectionFactory2 {
    String connectionUrl = "jdbc:mysql://localhost:3306/dbName";
    String dbUser = "dbUser";
    String dbPwd = "dbPassword";

    private static ConnectionFactory2 connectionFactory = null;

    private ConnectionFactory2() { }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory2 getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory2();
        }
        return connectionFactory;
    }
}
