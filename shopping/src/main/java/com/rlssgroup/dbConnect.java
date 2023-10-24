package com.rlssgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class dbConnect {

    public static Connection conn;

    public static Connection getConnection()throws ClassNotFoundException,SQLException, IOException {
            String configFilePath = "shopping\\src\\main\\java\\com\\rlssgroup\\config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String[] vault = new String[2];
            String dbConn;

            if (conn == null){
                if ((System.getenv("OS").equals("Windows_NT"))){
                    dbConn = prop.getProperty("DEV_ENV");
                    vault[0] = "roger";
                    vault[1] = "P3ngu1n5";
                }
                else{
                    dbConn = prop.getProperty("PROD_ENV");
                    String secretName = prop.getProperty("PROD_SECRET_NAME");
                    String keyVaultName = "rlss-valut-01";
                    vault = azureAuth.authDetails(secretName, keyVaultName);
                }
                Class.forName("org.postgresql.Driver");
                conn = DriverManager
                .getConnection(dbConn,vault[0], vault[1]);
                conn.setAutoCommit(false);
                logging.logEvent("Opened database successfully");
            }
            else {
                logging.logEvent("Connection exists, new one not opened");
            }
        return conn;
    }

    public static void connectionClose()throws ClassNotFoundException,SQLException{
        if (conn != null){   
            conn.close();
            conn = null;
        }
        logging.logEvent("Connection closed");
    }
}