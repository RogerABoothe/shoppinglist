package com.rlssgroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemComp {
    
    public static int num ;

    public static void compItem(String usr_comp, String num) throws ClassNotFoundException,SQLException, IOException {
        Connection conn;
        Statement stmt;
        String status = compStatus(num);

        if (status.equals("t")){
            conn = DBConnect.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE shopping"
            + " SET status = false, usr_comp = '" + usr_comp + "', time_comp = current_timestamp"
            + " WHERE num = " + num +  ";";
            stmt.executeUpdate(sql);
            Logging.logEvent("Marking item " + num  + " complete by " + usr_comp);
            stmt.close();
            conn.commit();
            System.out.println("Item marked complete!");
        }
        else {
            System.out.println("Item previously marked completed, no change made.");
        }
    }

    public static String compStatus(String num) throws ClassNotFoundException, SQLException, IOException{
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT status FROM shopping WHERE num = " + num +  ";";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String status = rs.getString("status");
        return status;
    }
}
            