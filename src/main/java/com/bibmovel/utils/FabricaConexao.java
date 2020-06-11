package com.bibmovel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:22
 */
public abstract class FabricaConexao {

    private static final String HOST = "localhost";
    private static final String USER = "admin";
    private static final String PASS = "1234";
    private static final String DATABASE = "BibMovel";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "?useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException, SQLException
            , IllegalAccessException, InstantiationException {

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
