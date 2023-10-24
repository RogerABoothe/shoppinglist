package com.rlssgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class dbConnect {

    public static Connection conn;

    public static Connection getConnection()throws ClassNotFoundException,SQLException, IOException {
        String dbConn = config.getDbConn();
        String vaultUser = config.getVaultUser();
        String vaultPwd = config.getVaultPwd();

        if (conn == null){
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
            .getConnection(dbConn,vaultUser, vaultPwd);
            conn.setAutoCommit(false);
            logging.logEvent("Opened database successfully");
        }
        else {
            logging.logEvent("Connection exists, new one not opened");
        }
        return conn;
    }

    public static void connectionClose()throws ClassNotFoundException,SQLException, IOException{
        if (conn != null){   
            conn.close();
            conn = null;
        }
        logging.logEvent("Connection closed");
    }
}