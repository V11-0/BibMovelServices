package com.bibmovel.dao;

import com.bibmovel.entidades.Usuario;
import com.bibmovel.utils.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:07
 */
public class UsuarioDAO {

    public boolean login(Usuario usuario) {

        try {
            Connection connection = FabricaConexao.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT login FROM Usuario WHERE " +
                    "login = ? AND senha = ?");

            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(Usuario usuario) {

        try {
            Connection connection = FabricaConexao.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Usuario VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getLogin());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
