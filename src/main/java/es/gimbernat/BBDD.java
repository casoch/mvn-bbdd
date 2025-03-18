package es.gimbernat;

import java.awt.im.InputContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BBDD {
    private  Connection conn;

    public  boolean init() {
        try {
        	Properties p = loadPropertiesFile();
        	String strConn = (String) p.get("db.string_connection");
        	System.out.println(strConn);
            conn = DriverManager.getConnection(strConn);

            return true;
        } catch (SQLException e) {
            showError(e);
            return false;
        }
    }

    public void showError(SQLException e) {
        System.out.println("Mensaje de error: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    
    public Properties loadPropertiesFile()
    {
    	Properties p = new Properties();
    	try {
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			p.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    	return p;
    	
    }
}
