package com.company.Query;

import com.company.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//4)	Предоставьте отсортированный список книг определенного издателя (при этом id требуемого издателя можно менять в sql запросе)
public class Query4 {
    public static void main(String[] argv) {
        showTitles();
        selectFromTitles();

    }
    public static void  selectFromTitles(){
        try{

            JDBC.connect();

            Statement stmt = JDBC.connection.createStatement();

            System.out.println("Insert  publisherID");
            Scanner scannerID = new Scanner(System.in);
            int publisher_ID = Integer.parseInt(scannerID.nextLine());

            String querySelectTitle = "SELECT * FROM TITLES WHERE publisherID =("+publisher_ID+") ORDER BY TITLE";
            System.out.println("**** Show selected titles ****");
            System.out.println("***********************************************************************************");
            System.out.println("isbn"+"\t\t\t"+"price"+"\t\t"+"EdiNum"+"\t"+"year"+"\t\t"
                    +"pubID"+"\t"+"title" );
            System.out.println("***********************************************************************************");
            printResult(stmt, querySelectTitle);

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
    public static void  showTitles(){
        try{

            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            String querySelectTitle = "SELECT * FROM TITLES";
            System.out.println("**** Show all titles ****");
            System.out.println("***********************************************************************************");
            System.out.println("isbn"+"\t\t\t"+"price"+"\t\t"+"EdiNum"+"\t"+"year"+"\t\t"
                    +"pubID"+"\t"+"title" );
            System.out.println("***********************************************************************************");
            printResult(stmt, querySelectTitle);

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }

    private static void printResult(Statement stmt, String querySelectTitle) throws SQLException {
        ResultSet rs = stmt.executeQuery(querySelectTitle);
        while (rs.next()) {
            int isbn = rs.getInt("isbn");
            String title = rs.getString("title");
            int editionNumber = rs.getInt("editionNumber");
            String year = rs.getString("year") ;
            int publisherID = rs.getInt("publisherID");
            BigDecimal price = rs.getBigDecimal("price");
            System.out.println(isbn + "\t\t" + price + "\t\t"+ editionNumber+"\t\t"+year
                    +"\t\t"+ publisherID+"\t\t"+ title );
        }
    }


}