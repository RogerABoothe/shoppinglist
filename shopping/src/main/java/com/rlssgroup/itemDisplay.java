package com.rlssgroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ItemDisplay {

    public static void displayMenu(Scanner scnr) throws ClassNotFoundException, SQLException, IOException {
        Menu menu = new Menu();
        menu.disMenu();
        int usrChoice = menu.choice(scnr, 1, 4);
        switch(usrChoice) {
            case 1:
                displayAll();
                break;
            case 2:
                System.out.println("Enter the item number");
                int max = findMax();
                usrChoice = menu.choice(scnr, 1, max);
                displaySingle(usrChoice);
                break;
            case 3:
                displayNotComp();
                break;
            case 4:
                displayComp();
                break;
        }
    }

    public static void displayAll() throws ClassNotFoundException,SQLException, IOException {
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM shopping ;" ;
        ResultSet rs = stmt.executeQuery(sql);
        printDisplay(rs);
        stmt.close();
    }

    public static void displaySingle(int num) throws ClassNotFoundException,SQLException, IOException {
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM shopping WHERE num = " + num + ";" ;
        ResultSet rs = stmt.executeQuery(sql);
        printDisplay(rs);
        stmt.close();
    }

    public static void displayNotComp() throws ClassNotFoundException,SQLException, IOException {
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM shopping WHERE status = true;" ;
        ResultSet rs = stmt.executeQuery(sql);
        printDisplay(rs);
        stmt.close();
    }

    public static void displayComp() throws ClassNotFoundException,SQLException, IOException {
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM shopping WHERE status = false;" ;
        ResultSet rs = stmt.executeQuery(sql);
        printDisplay(rs);
        stmt.close();
    }

    public static int findMax() throws ClassNotFoundException, SQLException, IOException{
        Connection conn;
        Statement stmt;
        conn = DBConnect.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT COUNT(*) FROM shopping;" ;
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int max = rs.getInt(1);
        stmt.close();
        System.out.println("There are currently " + max + " items on the list. Enter a number");
        return max;
    }

    public static void printDisplay(ResultSet rs) throws ClassNotFoundException,SQLException {
        while ( rs.next() ) {
            int num = rs.getInt("num");
            String status = rs.getString("status");
            String descr  = rs.getString("descr");
            String usr_add = rs.getString("usr_add");
            String time_add = rs.getString("time_add");
            String usr_comp = rs.getString("usr_comp");
            String time_comp = rs.getString("time_comp");
            System.out.println("Number = " + num );
            System.out.println("Item Status = " + status );
            System.out.println("Item Description = " + descr );
            System.out.println("User added = " + usr_add );
            System.out.println("Time added = " + time_add );
            System.out.println("User completed = " + usr_comp );
            System.out.println("Time completed = " + time_comp );
            System.out.println();
        }
    }
}