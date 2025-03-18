package es.gimbernat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BBDD {
    private static Connection conn;

    public static boolean init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/maven_bbdd?user=root&password=linux123");

            return true;
        } catch (SQLException e) {
            showError(e);
            return false;
        }
    }

    public static void showError(SQLException e) {
        System.out.println("Mensaje de error: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
}
