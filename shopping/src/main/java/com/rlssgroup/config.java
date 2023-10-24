package com.rlssgroup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {

    private String dbConn;
    private String[] vault;

    public config() throws IOException {
        String configFilePath = "shopping\\src\\main\\java\\com\\rlssgroup\\config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);
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
        System.out.println(dbConn);
        System.out.println(vault[0]);
        System.out.println(vault[1]);
    }

    public String getDbConn() {
        return dbConn;
    }

    public void setDbConn(String dbConn) {
        this.dbConn = dbConn;
    }

    public String[] getVault() {
        return vault;
    }

    public void setVault(String[] vault) {
        this.vault = vault;
    }
}