/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Snowyy
 */
public class DAO {

    public static Connection con;

    private final String serverName = "DESKTOP-NTTAICS";
    private final String dbName = "cuahang";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123456";
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}
