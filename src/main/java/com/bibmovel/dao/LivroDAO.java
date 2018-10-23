package com.bibmovel.dao;

import com.bibmovel.entidades.Autor;
import com.bibmovel.entidades.Editora;
import com.bibmovel.entidades.Livro;
import com.bibmovel.utils.FabricaConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:29
 */
public class LivroDAO {

    private Connection conn;

    public LivroDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        conn = FabricaConexao.getConnection();
    }

    public List<Livro> getAll() throws SQLException {

        List<Livro> livros = new ArrayList<>();

        ResultSet rs = conn.prepareStatement("SELECT * FROM Livro").executeQuery();

        while (rs.next())
            livros.add(getLivro(rs.getString("isbn")));

        return livros;
    }

    public List<Livro> getBasicInfo() throws SQLException {

        List<Livro> livros = new ArrayList<>();

        ResultSet rs = conn.prepareStatement("SELECT titulo, ISBN, nomeArquivo, AVG(classificacao) " +
                "FROM Livro L LEFT JOIN Classificacao C on L.ISBN = C.FK_Livro_ISBN " +
                "GROUP BY titulo, ISBN, nomeArquivo").executeQuery();

        while (rs.next())
            livros.add(new Livro(rs.getString(1), rs.getString(2)
                    , rs.getString(3), rs.getFloat(4)));

        return livros;
    }

    public void insert(Livro livro) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("INSERT INTO Livro VALUES (?, ?, ?, ?, ?, ?)");

        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getIsbn());
        statement.setString(3, livro.getNomeArquivo());
        statement.setString(4, livro.getGenero());
        statement.setShort(5, livro.getAnoPublicacao());
        statement.setString(6, livro.getEditora().getCnpj());

        statement.executeUpdate();

        List<Autor> autores = livro.getAutores();

        for (Autor a : autores) {

            statement = conn.prepareStatement("INSERT INTO AutorLivro VALUES (?, ?)");
            statement.setString(1, livro.getIsbn());
            statement.setInt(2, a.getId());

            statement.executeUpdate();
        }
    }

    public Livro getLivro(String isbn) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Livro WHERE isbn = ?");
        preparedStatement.setString(1, isbn);

        ResultSet rs = preparedStatement.executeQuery();
        Livro livro = new Livro();

        if (rs.next()) {

            livro.setTitulo(rs.getString(1));
            livro.setIsbn(rs.getString(2));
            livro.setNomeArquivo(rs.getString(3));
            livro.setGenero(rs.getString(4));
            livro.setAnoPublicacao(rs.getShort(5));

            preparedStatement = conn.prepareStatement("SELECT nome FROM Editora WHERE CNPJ = ?");
            preparedStatement.setString(1, rs.getString(6));

            rs = preparedStatement.executeQuery();

            if (rs.next())
                livro.setEditora(new Editora(rs.getString(1)));

            preparedStatement = conn.prepareStatement("SELECT AVG(classificacao) FROM Classificacao" +
                    " WHERE FK_Livro_ISBN = ?");

            preparedStatement.setString(1, livro.getIsbn());
            rs = preparedStatement.executeQuery();

            if (rs.next())
                livro.setClassificacaoMedia(rs.getFloat(1));

            preparedStatement = conn.prepareStatement("SELECT nome FROM Autor A INNER JOIN AutorLivro L on " +
                    "A.id = L.FK_Autor_id WHERE L.FK_Livro_ISBN = ?");

            preparedStatement.setString(1, livro.getIsbn());
            rs = preparedStatement.executeQuery();

            List<Autor> autores = new ArrayList<>();

            while (rs.next()) {
                Autor autor = new Autor(rs.getString(1));
                autores.add(autor);
            }

            livro.setAutores(autores);
        }

        return livro;
    }
}
