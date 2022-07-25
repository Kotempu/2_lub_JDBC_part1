package com.company.Query;


import com.company.Connection.JDBC;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.company.Query.Query5.showAuthors;

//6)	Обновите Имя автора по определенному id
public class Query6 {
    public static void main(String[] argv) {
        showAuthors();//import from Query5
        updateAuthors();
        showAuthors();
    }

    public static void updateAuthors() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();

            System.out.println("Insert  authorID to change ");
            Scanner scannerID = new Scanner(System.in);
            int author_ID = Integer.parseInt(scannerID.nextLine());
            updateAuthorTableFirstName(stmt, author_ID);
            updateAuthorTableLastName(stmt, author_ID);

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }

    private static void updateAuthorTableFirstName(Statement stmt, int author_ID) {

        System.out.println("Insert NEW first name");
        Scanner scanner1 = new Scanner(System.in);
        String newFirstName = scanner1.nextLine();


        String updatePublishersTable = "UPDATE authors  set firstName =  ('"+newFirstName+ "')" +
                "WHERE authorID = ("+author_ID+")";

        try {
            stmt.executeUpdate(updatePublishersTable);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }
    private static void updateAuthorTableLastName(Statement stmt, int author_ID) {

        System.out.println("Insert NEW last name");
        Scanner scanner2 = new Scanner(System.in);
        String newLastName = scanner2.nextLine();

        String updatePublishersTable = "UPDATE authors  set lastName =  ('"+newLastName+ "')" +
                "WHERE authorID = ("+author_ID+")";
        try {
            stmt.executeUpdate(updatePublishersTable);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }
}