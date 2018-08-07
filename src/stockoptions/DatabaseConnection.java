/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockoptions;

import java.sql.*;

/**
 *
 * @author Alex
 */
public class DatabaseConnection
{
    public static Connection connection;
    public static Connection getConnection() throws Exception
    {

        String url = "jdbc:mysql://localhost:3306/invento_systems";
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            System.out.println("Could not load driver.");
        }

        try
        {
            connection = DriverManager.getConnection(url, "root", "password");
            System.out.println("connected to DB");
        } catch (SQLException e)
        {
            System.out.println("could not connect to DB");
            System.out.println(e);
        }
        
        return connection;
    }
}
