package com.company.Query;


import com.company.Connection.JDBC;

import java.sql.*;
import java.util.Scanner;


//3)	Сделайте выборку Издателей и измените имя определенного Издателя.

public class Query3 {
    public static void main(String[] argv) {
        showPublishers();
        updatePublishers();
        showPublishers();
    }
    public static void  showPublishers(){
        Statement stmt = null;
        try{

            JDBC.connect();

            stmt = JDBC.connection.createStatement();
            String query2Author = "SELECT * FROM publishers";
            System.out.println("Show all publishers");

            ResultSet rs1 = stmt.executeQuery(query2Author);
            while (rs1.next()) {
                int id = rs1.getInt("publisherID");
                String pubName = rs1.getString("publisherName");
                System.out.println(id + "\t" + pubName);
            }

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }




    public static void updatePublishers() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            updatePublishersTable(stmt);

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }

    private static void updatePublishersTable(Statement stmt) {
        System.out.println("Insert  publisherID to change ");
        Scanner scannerID = new Scanner(System.in);
        int publisher_ID = Integer.parseInt(scannerID.nextLine());

        System.out.println("Insert new publisher name");
        Scanner scannerName = new Scanner(System.in);
        String newPublisherName = scannerName.nextLine();


        String updatePublishersTable = "UPDATE Publishers  set publisherName =  '"+newPublisherName+ "'" +
                "WHERE publisherID = "+publisher_ID ;
        try {
            stmt.executeUpdate(updatePublishersTable);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }

    }
}