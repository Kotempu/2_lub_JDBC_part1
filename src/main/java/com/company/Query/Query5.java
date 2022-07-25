package com.company.Query;


import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//5)	Выполните добавление Нового автора в БД
public class Query5 {

    public static void main(String[] argv) {

        showAuthors();
        updateTables();
        showAuthors();

    }

    public static void  showAuthors(){
        try{

            JDBC.connect();

            Statement stmt = JDBC.connection.createStatement();
            String queryShowAuthor = "SELECT * FROM authors";
            System.out.println("Show all authors");
            System.out.println("*****************************************************");
            System.out.println("authorID"+"    "+"firstName"+"       "+"lastName");
            System.out.println("*****************************************************");

            ResultSet rs = stmt.executeQuery(queryShowAuthor);
            while (rs.next()) {
                int id = rs.getInt("authorID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println(id + "\t\t\t" + firstName+"\t\t\t"+lastName);
            }

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }


    public static void updateTables() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            updateAuthorTable(stmt);

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }

    private static void updateAuthorTable(Statement stmt) {
        System.out.println("Insert new author ");
        System.out.println("**********************************************");

        System.out.println("Insert first name");
        Scanner scanner1 = new Scanner(System.in);
        String newFirstName = scanner1.nextLine();

        System.out.println("Insert last name");
        Scanner scanner2 = new Scanner(System.in);
        String newLastName = scanner2.nextLine();

        String updatePublishersTable = "INSERT INTO Authors (firstName, lastName)" +
                "VALUES ('" + newFirstName +"', '"+newLastName+"')";

        try {
            stmt.executeUpdate(updatePublishersTable);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }

    }
}
