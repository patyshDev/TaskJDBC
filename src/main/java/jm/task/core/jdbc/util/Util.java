package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Util  {
    public static final String URL = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "1Q1a2w2s3E3d";

    public Util() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

}
