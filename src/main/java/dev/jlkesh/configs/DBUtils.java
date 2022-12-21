package dev.jlkesh.configs;

import dev.jlkesh.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
    private static Connection connection = null;
    private static final ResourceBundle settings = ResourceBundle.getBundle("application");


    public static Connection getConnection() {
        try {

            if (connection == null) {
                synchronized (UserDAO.class) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(
                                settings.getString("datasource.jdbc.diver"),
                                settings.getString("datasource.jdbc.user.username"),
                                settings.getString("datasource.jdbc.user.password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
