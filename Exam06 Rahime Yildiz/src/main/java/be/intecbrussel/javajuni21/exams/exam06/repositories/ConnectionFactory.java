package be.intecbrussel.javajuni21.exams.exam06.repositories;


import java.sql.*;


public class ConnectionFactory {

	// TODO: creëer een database genoemd thebelgianbrewerydb
    /*     CREATE DATABASE thebelgianbrewerydb;      */
	
	// TODO: maak een mysql gebruiker met naam ' customer ' en wachtwoord ' P@ssw0rd ' aan.
    /*     CREATE USER 'customer'@'localhost' IDENTIFIED BY  'P@ssw0rd'     */

    String connectionUrl = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    String dbUser = "customer";
    String dbPwd = "P@ssw0rd";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {

    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    
    }
}
