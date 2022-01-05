package com.knoldus.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class JDBCConnection
{
/*
Main Method
 */
    public static void main(String[] args) {
        try {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/Raj";
            final String USER = "root";
            final String PASS = "Rohit@122333";

            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

            Statement s = conn.createStatement();
            ResultSet res = s.executeQuery("select sum(Amount) as total from Cart");
            while(res.next())
                System.out.println("Total amount to be paid = "+res.getString(1));
            Statement stmt2=conn.createStatement();
            ResultSet result =stmt2.executeQuery("select pname from Product where pid=(select pid from Cart where qty=(select Max(qty) from Cart));");
            while(result.next())
                System.out.println("Maximum sold = "+ result.getString(1));
            System.out.println("List all the Products which are not yet sold.\n");
            Statement stmt3 = conn.createStatement();
            ResultSet r3 = stmt3.executeQuery("select pname from Product where pid NOT IN(Select pid from Cart)");
            while(r3.next())
                System.out.println(r3.getString(1));

            System.out.println("\n\n");
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
