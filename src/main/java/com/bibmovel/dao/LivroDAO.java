package com.bibmovel.dao;

import com.bibmovel.entidades.Autor;
import com.bibmovel.entidades.Editora;
import com.bibmovel.entidades.Livro;
import com.bibmovel.utils.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:29
 */
public class LivroDAO {

    public List<Livro> getAll() throws Exception {

        Connection connection = FabricaConexao.getConnection();
        List<Livro> livros = new ArrayList<>();

        ResultSet rs = connection.prepareStatement("SELECT * FROM Livro").executeQuery();

        while (rs.next())
            livros.add(getLivro(rs.getString("isbn")));

        return livros;
    }

    public List<Livro> getBasicInfo() throws Exception {

        Connection connection = FabricaConexao.getConnection();
        List<Livro> livros = new ArrayList<>();

        ResultSet rs = connection.prepareStatement("SELECT titulo, AVG(classificacao) " +
                "FROM Livro LEFT JOIN Classificacao C on Livro.ISBN = C.FK_Livro_ISBN " +
                "GROUP BY titulo").executeQuery();

        while (rs.next())
            livros.add(new Livro(rs.getString(1), rs.getFloat(2)));

        return livros;
    }

    public void insert(Livro livro) throws Exception {

        Connection conn = FabricaConexao.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO Livro VALUES (?, ?, ?, ?, ?)");

        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getIsbn());
        statement.setString(3, livro.getNomeArquivo());
        statement.setString(4, livro.getGenero());
        statement.setString(5, livro.getEditora().getCnpj());

        statement.executeUpdate();
    }

    public Livro getLivro(String isbn) throws Exception {

        Connection connection = FabricaConexao.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Livro WHERE isbn = ?");
        preparedStatement.setString(1, isbn);

        ResultSet rs = preparedStatement.executeQuery();
        Livro livro = new Livro();

        if (rs.next()) {

            livro.setTitulo(rs.getString(1));
            livro.setIsbn(rs.getString(2));
            livro.setNomeArquivo(rs.getString(3));
            livro.setGenero(rs.getString(4));

            preparedStatement = connection.prepareStatement("SELECT * FROM Editora WHERE CNPJ = ?");
            preparedStatement.setString(1, rs.getString(5));

            rs = preparedStatement.executeQuery();

            if (rs.next())
                livro.setEditora(new Editora(rs.getString(1), rs.getString(2)));

            preparedStatement = connection.prepareStatement("SELECT AVG(classificacao) FROM Classificacao" +
                    " WHERE FK_Livro_ISBN = ?");

            preparedStatement.setString(1, livro.getIsbn());
            rs = preparedStatement.executeQuery();

            if (rs.next())
                livro.setClassificacaoMedia(rs.getFloat(1));

            preparedStatement = connection.prepareStatement("SELECT * FROM Autor A INNER JOIN AutorLivro L on " +
                    "A.id = L.FK_Autor_id WHERE L.FK_Livro_ISBN = ?");

            preparedStatement.setString(1, livro.getIsbn());
            rs = preparedStatement.executeQuery();

            List<Autor> autores = new ArrayList<>();

            while (rs.next()) {

                Autor autor = new Autor(rs.getString(1), rs.getDate(2)
                        , rs.getString(3),rs.getInt(4));

                autores.add(autor);
            }

            livro.setAutores(autores);
        }

        return livro;
    }
}
