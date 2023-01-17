package Util;

import java.sql.*;

public class DBConnection {
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    private static final String url = "jdbc:mysql://localhost:3306/hospitaldb";


    public static Connection dbConnect() {
        Connection connection = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Connection is OK.....");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection ERROR.....");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
