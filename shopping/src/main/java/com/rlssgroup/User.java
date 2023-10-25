package com.rlssgroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
    
    private boolean authStatus;
    private String userName;

    public User() throws ClassNotFoundException, SQLException, IOException {
        Scanner userScnr = new Scanner(System.in); //closed in Shopping class
        System.out.println("Please enter your user name");
        userName = userScnr.nextLine();
        System.out.println("Please enter your password");
        String userPWD = userScnr.nextLine();
        Logging.logEvent("Attempting to authorize " + userName);
        authStatus = authorizeUser(userName, userPWD);
    }

    public boolean authorizeUser(String userName, String userPWD) throws IOException{
        authStatus = false;
        try {
            Connection conn;
            Statement stmt;
            conn = DBConnect.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT usrpwd FROM shoppingusers WHERE usrname = '" + userName +  "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                userPWD = rs.getString("usrpwd");
                authStatus = true;
            }
            
        }
        catch (ClassNotFoundException | IOException | SQLException e) {
            Logging.logException(e);
        } 
        return authStatus ;
    }

    public String getUserName(){
        return userName ;
    }

    public boolean getAuthStatus(){
        return authStatus ;
    }
}