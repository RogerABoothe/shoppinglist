package com.rlssgroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class itemComp {
    public static int num ;

    public static void compItem(String usr, String itemNum) throws ClassNotFoundException,SQLException {
        String usr_comp = usr;
        num = Integer.parseInt(itemNum);
        Connection conn;
        Statement stmt;
        String status = compStatus(num);

        if (status.equals("t")){
            conn = dbConnect.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE shopping"
            + " SET status = false, usr_comp = '" + usr_comp + "', time_comp = current_timestamp"
            + " WHERE num = " + num +  ";";
            stmt.executeUpdate(sql);
            logging.logEvent("Marking item " + num  + " done by " + usr_comp);
            stmt.close();
            conn.commit();
            logging.logEvent("Item " + num + " marked complete by " + usr_comp);
            System.out.println("Item marked complete!");
        }
        else {
            System.out.println("Item previously marked completed, no change made.");
        }
    }

    public static String compStatus(int itemNum) throws ClassNotFoundException, SQLException{
        num = itemNum;
        Connection conn;
        Statement stmt;
        conn = dbConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT status FROM shopping WHERE num = " + num +  ";";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String status = rs.getString("status");
        return status;
    }
}
            