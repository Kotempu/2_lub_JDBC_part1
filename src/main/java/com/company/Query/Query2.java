package com.company.Query;


import com.company.Connection.JDBC;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//2)	Добавьте нового Издателя (publusher).
public class Query2 {
    public static void main(String[] argv) {
        updateTables();
    }

    public static void updateTables() {
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
        System.out.println("Insert  NAME of new publisher ");

        Scanner scanner = new Scanner(System.in);
        String newPublisher = scanner.nextLine();

        String updatePublishersTable = "INSERT INTO Publishers (publisherName)" + "VALUES ('" + newPublisher + "')";
        try {
            stmt.executeUpdate(updatePublishersTable);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }

    }
}