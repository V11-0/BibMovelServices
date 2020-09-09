package com.bibmovel.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:22
 */
public abstract class ConnectionFactory {

    private static final String HOST = "192.168.0.100";
    private static final String USER = "admin";
    private static final String PASS = "1234";
    private static final String DATABASE = "BibMovel";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "?useSSL=false";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (NoSuchMethodException | SQLException | ClassNotFoundException | IllegalAccessException
                | InstantiationException | IllegalArgumentException
                | InvocationTargetException | SecurityException e) {
            
            e.printStackTrace();
        }

        return null;
    }
}
