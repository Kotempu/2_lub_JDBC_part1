package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


//Задания для выполнения ЛР:

//     1)	Сделайте выборку по авторам, отсортировав по их Имени и Фамилии
//      2)	Добавьте нового Издателя (publusher).
//     3)	Сделайте выборку Издателей и измените имя определенного Издателя.
//     4)	Предоставьте отсортированный список книг определенного издателя (при этом id требуемого издателя можно менять в sql запросе)
//     5)	Выполните добавление Нового автора в БД
//     6)	Обновите Имя автора по определенному id
//    7)	Добавить нового Publisher
//    Добавить новую Titles (При передачи VALUES publisherID – нужно сделать подзапросом select*from publisher where publisherName =””)
//   Добавить authorISBN (при передачи VALUES необходимо параметр autorID так же сделать подзапросом с указанием имени и фамилии)


//1)	Сделайте выборку по авторам, отсортировав по их Имени и Фамилии
public class Query1 {

    public static void main(String[] args) {

        Statement stmt1 = null;
        try{

            JDBC.connect();

            stmt1 = JDBC.connection.createStatement();
            String query3Author = "SELECT * FROM AUTHORS ORDER BY FIRSTNAME, LASTNAME";
            System.out.println("Show authors");

            ResultSet rs1 = stmt1.executeQuery(query3Author);
            while (rs1.next()) {
                int id = rs1.getInt("authorID");
                String autFirstName = rs1.getString("firstName");
                String autLastName = rs1.getString("lastName");
                System.out.println(id + "\t" + autFirstName+"\t\t\t"+ autLastName);
            }

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }

    }

}