package com.company.Query;

// 7)	7.1 Добавить нового Publisher
// 7.2 Добавить новую Titles (При передачи VALUES publisherID – нужно сделать
// подзапросом select*from publisher where publisherName =””)
// 7.3 Добавить authorISBN (при передачи VALUES необходимо параметр autorID
// так же сделать подзапросом с указанием имени и фамилии)

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Query7 {
    public static void main(String[] args) {

        Statement stmt = null;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String newPublisher;
            System.out.println("Укажите нового издателя:");
            newPublisher = (new Scanner(System.in).nextLine());
            String query = "INSERT INTO publishers(publisherName) VALUES('" + newPublisher + "')";
            String query2_1 = "SELECT * FROM publishers where publisherName = '" + newPublisher + "'";

            System.out.println("Добавлена новая запись в publishers:");
            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery(query2_1);
            String publisherID;
            String publisherName;
            while (rs.next()){
                publisherID = rs.getString("publisherID");
                publisherName = rs.getString("publisherName");
                System.out.println( "publisherID:\t" + publisherID);
                System.out.println( "publisherName:\t" + publisherName+ "\n");
            }


            int isbn;
            String title;
            int editionNumber;
            int year;
            float price;
            System.out.println("*** Теперь добавьте книгу! *** ");
            System.out.print("Укажите ISBN: ");
            isbn = (new Scanner(System.in).nextInt());
            System.out.print("Укажите название книги: ");
            title = (new Scanner(System.in).nextLine());
            System.out.print("Номер издания: ");
            editionNumber = (new Scanner(System.in).nextInt());
            System.out.print("Год: ");
            year = (new Scanner(System.in).nextInt());
            System.out.print("Цена: ");
            price = (new Scanner(System.in).nextFloat());
            query = "INSERT INTO titles(isbn, title, editionNumber, year, publisherID, price) " +
                    "VALUES(" + isbn + ",'" + title + "'," + editionNumber + "," + year +
                    ", (SELECT publisherID FROM publishers WHERE publisherName = '" + newPublisher + "')," + price + ")";
            stmt.executeUpdate(query);



            query = "INSERT INTO authorisbn(authorID, isbn) VALUES ( (SELECT authorID FROM authors WHERE firstName = 'Мэрилин' and lastName = 'Монро'), " + "'" + isbn + "')";
            stmt.executeUpdate(query);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }

    }


}