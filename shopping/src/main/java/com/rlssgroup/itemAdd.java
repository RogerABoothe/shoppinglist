package com.rlssgroup;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class itemAdd {

    public static void addItem(String usr, String itemDesc) throws ClassNotFoundException,SQLException {
        String usr_add = usr;
        String descr = itemDesc; 
        Connection conn;
        Statement stmt;
        conn = dbConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "INSERT INTO shopping (status, descr, usr_add, time_add)"
           + "VALUES (true, '" + descr + "', '" + usr_add + "', current_timestamp) ; ";
        stmt.executeUpdate(sql);
        logging.logEvent("Adding item " + descr + " done by " + usr_add);
        stmt.close();
        conn.commit();
    }
}