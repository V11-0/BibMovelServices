package com.bibmovel.controller;

import com.bibmovel.models.Classificacao;
import com.bibmovel.models.Livro;
import com.bibmovel.models.Usuario;
import com.bibmovel.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinibrenobr11 on 14/11/18 at 17:16
 */
public class ClassificacaoController {

    private Connection connection;

    public ClassificacaoController() throws ClassNotFoundException, SQLException
            , InstantiationException, IllegalAccessException {

        this.connection = ConnectionFactory.getConnection();
    }

    public List<Classificacao> getClassificacoesByLivro(String isbn) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "SELECT login, titulo, classificacao, comentario FROM Usuario INNER JOIN Classificacao C " +
                        "on Usuario.login = C.FK_Usuario_login INNER JOIN Livro L on C.FK_Livro_ISBN = L.ISBN " +
                        "WHERE C.FK_Livro_ISBN = ?"
        );

        statement.setString(1, isbn);
        ResultSet rs = statement.executeQuery();

        List<Classificacao> classificacoes = new ArrayList<>();

        while (rs.next()) {

            Classificacao classificacao = new Classificacao();
            classificacao.setUsuario(new Usuario(rs.getString(1)));
            classificacao.setLivro(new Livro(rs.getString(2)));
            classificacao.setClassificacao(rs.getFloat(3));
            classificacao.setComentario(rs.getString(4));

            classificacoes.add(classificacao);
        }

        return classificacoes;
    }

    public void addClassificacao(Classificacao classificacao) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Classificacao VALUES (?, ?, ?, ?)"
        );

        statement.setString(1, classificacao.getUsuario().getUsuario());
        statement.setString(2, classificacao.getLivro().getIsbn());
        statement.setFloat(3, classificacao.getClassificacao());
        statement.setString(4, classificacao.getComentario());

        statement.executeUpdate();
    }
}
