package com.rlssgroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemAdd {

    public static void addItem(String usr, String itemDesc) throws ClassNotFoundException,SQLException, IOException {
        String usr_add = usr;
        String descr = itemDesc; 
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "INSERT INTO shopping (status, descr, usr_add, time_add)"
           + "VALUES (true, '" + descr + "', '" + usr_add + "', current_timestamp) ; ";
        stmt.executeUpdate(sql);
        Logging.logEvent("Adding item " + descr + " done by " + usr_add);
        stmt.close();
        conn.commit();
    }
}