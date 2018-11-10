package com.bibmovel.dao;

import com.bibmovel.entidades.Usuario;
import com.bibmovel.utils.FabricaConexao;

import java.sql.*;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:07
 */
public class UsuarioDAO {

    public Usuario login(Usuario usuario) throws ClassNotFoundException, SQLException, InstantiationException
            , IllegalAccessException {

        Connection connection = FabricaConexao.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, email FROM Usuario WHERE " +
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

    public boolean add(Usuario usuario) throws SQLException, IllegalAccessException, InstantiationException
            , ClassNotFoundException {

        Connection connection = FabricaConexao.getConnection();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT login from Usuario WHERE login = '" + usuario.getLogin() + "'");

        if (rs.next())
            return false;

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Usuario VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getEmail());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getLogin());

        preparedStatement.executeUpdate();

        return true;
    }
}
