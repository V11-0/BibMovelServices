package com.bibmovel.controller;

import com.bibmovel.models.Sessao;
import com.bibmovel.models.Usuario;
import com.bibmovel.utils.ConnectionFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:07
 */
public class UsuarioController {

    private final Connection conn;

    public UsuarioController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        conn = ConnectionFactory.getConnection();
    }

    public Sessao login(Usuario usuario) throws SQLException, NoSuchAlgorithmException {

        String field = usuario.getUsuario() == null? "email" : "usuario";
        String value = usuario.getUsuario() == null? usuario.getEmail() : usuario.getUsuario();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM Usuario WHERE "
                + field + " = ? AND senha = ?");

        preparedStatement.setString(1, value);
        preparedStatement.setString(2, usuario.getSenha());

        ResultSet rs = preparedStatement.executeQuery();
        int id;

        if (rs.next()) {
            id = rs.getInt(1);
            rs.close();

            MessageDigest md = MessageDigest.getInstance("MD5");
            SecureRandom random = new SecureRandom();

            byte[] bytes = new byte[16];
            random.nextBytes(bytes);

            byte[] digest = md.digest(bytes);
            String hash_code = DatatypeConverter.printHexBinary(digest);

            preparedStatement.close();
            preparedStatement = conn.prepareStatement(
                    "INSERT INTO Sessao (id_usuario, hash_code) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, hash_code);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao iniciar sessao");
            }

            int id_sessao;

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_sessao = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating session failed, no ID obtained.");
                }
            }

            preparedStatement.close();
            preparedStatement = conn.prepareStatement(
                    "SELECT id, id_usuario, hash_code, data_inicio FROM Sessao WHERE id = ?");

            preparedStatement.setInt(1, id_sessao);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Sessao(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4));
            }
        }

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
