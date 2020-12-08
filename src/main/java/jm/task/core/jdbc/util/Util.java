package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private String url = "jdbc:mysql://localhost:3306/myData?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private String userName = "root";
    private String password = "admin123123";

    public Connection getConnection() throws ClassNotFoundException, SQLException  {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userName, password);
    }

}
