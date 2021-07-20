package ir.maktab.hw7.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {
    private Connection connection;
    private String dbName;
    private String user;
    private String pass;

    public ConnectDb(String dbName, String user, String pass) {
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName,
                    user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
