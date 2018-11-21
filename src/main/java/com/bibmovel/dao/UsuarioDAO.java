package com.bibmovel.dao;

import com.bibmovel.entidades.Usuario;
import com.bibmovel.utils.FabricaConexao;

import java.sql.*;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:07
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        conn = FabricaConexao.getConnection();
    }

    public Usuario login(Usuario usuario) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT login, email FROM Usuario WHERE " +
                "login = ? AND senha = ?");

        preparedStatement.setString(1, usuario.getLogin());
        preparedStatement.setString(2, usuario.getSenha());
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            usuario.setSenha(null);
            usuario.setEmail(rs.getString(2));

            return usuario;
        } else
            return null;
    }

    public boolean add(Usuario usuario) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT login from Usuario WHERE login = '" + usuario.getLogin() + "'");

        if (rs.next())
            return false;

        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Usuario VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getEmail());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getLogin());

        preparedStatement.executeUpdate();

        return true;
    }

    public Usuario verify(Usuario google) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT login FROM Usuario WHERE login = ?");
        statement.setString(1, google.getLogin());

        ResultSet rs = statement.executeQuery();

        if (rs.next())
            return google;

        statement = conn.prepareStatement("INSERT INTO Usuario (login) VALUES (?)");
        statement.setString(1, google.getLogin());

        statement.executeUpdate();

        return google;
    }
}
