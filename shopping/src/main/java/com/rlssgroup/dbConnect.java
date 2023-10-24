package com.rlssgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class DBConnect {

    public static Connection conn;

    public static Connection getConnection()throws ClassNotFoundException,SQLException, IOException {
        Config configs = new Config();
        String dbConn = configs.getDbConn();
        String vaultUser = configs.getVaultUser();
        String vaultPwd = configs.getVaultPwd();

        if (conn == null){
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
            .getConnection(dbConn,vaultUser, vaultPwd);
            conn.setAutoCommit(false);
            Logging.logEvent("Opened database successfully");
        }
        else {
            Logging.logEvent("Connection exists, new one not opened");
        }
        return conn;
    }

    public static void connectionClose()throws ClassNotFoundException,SQLException, IOException{
        if (conn != null){   
            conn.close();
            conn = null;
        }
        Logging.logEvent("Connection closed");
    }
}