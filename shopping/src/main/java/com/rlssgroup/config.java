package com.rlssgroup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {

    private static String dbConn;
    private static String vaultUser;
    private static String vaultPwd;
    private static String logLoc;
    private String[] vault = new String[2];

    public config() throws IOException {
        String configFilePath = "shopping\\src\\main\\java\\com\\rlssgroup\\config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);
        if ((System.getenv("OS").equals("Windows_NT"))){
            dbConn = prop.getProperty("DEV_ENV");
            vaultUser = "roger";
            vaultPwd = "P3ngu1n5";
            logLoc = prop.getProperty("DEV_LOGGING");
        }
        else{
            dbConn = prop.getProperty("PROD_ENV");
            String secretName = prop.getProperty("PROD_SECRET_NAME");
            String keyVaultName = "rlss-valut-01";
            vault = azureAuth.authDetails(secretName, keyVaultName);
            vaultUser = vault[0];
            vaultPwd = vault[1];
            logLoc = prop.getProperty("PROD_LOGGING");
        }
    }

    public static String getDbConn() {
        return dbConn;
    }

    public static String getVaultUser() {
        return vaultUser;
    }

    public static String getVaultPwd() {
        return vaultPwd;
    }

    public static String getlogLoc() {
        return logLoc;
    }

}