package com.bibmovel.controller;

import com.bibmovel.models.Usuario;
import com.bibmovel.utils.ConnectionFactory;

import java.sql.*;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:07
 */
public class UsuarioController {

    private Connection conn;

    public UsuarioController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        conn = ConnectionFactory.getConnection();
    }

    public Usuario login(Usuario usuario) throws SQLException {
        return null;
    }

    public boolean add(Usuario usuario) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT id FROM Usuario WHERE usuario = ? OR email = ?");
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getEmail());

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return false;
        }

        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Usuario " +
                "(usuario, nome, email, senha) VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, usuario.getUsuario());
        preparedStatement.setString(2, usuario.getNome());
        preparedStatement.setString(3, usuario.getEmail());
        preparedStatement.setString(4, usuario.getSenha());

        preparedStatement.executeUpdate();

        return true;
    }

    public Usuario verify(Usuario google) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT login FROM Usuario WHERE login = ?");
        statement.setString(1, google.getUsuario());

        ResultSet rs = statement.executeQuery();

        if (rs.next())
            return google;

        statement = conn.prepareStatement("INSERT INTO Usuario (login) VALUES (?)");
        statement.setString(1, google.getUsuario());

        statement.executeUpdate();

        return google;
    }
}
