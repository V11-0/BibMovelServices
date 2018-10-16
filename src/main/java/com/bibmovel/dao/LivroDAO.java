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

    public List<Livro> getAll() {

        try {
            Connection connection = FabricaConexao.getConnection();
            List<Livro> livros = new ArrayList<>();

            ResultSet rs = connection.prepareStatement("SELECT * FROM Livro").executeQuery();

            while (rs.next()) {

                Livro livro = new Livro();
                livro.setTitulo(rs.getString(1));
                livro.setIsbn(rs.getString(2));
                livro.setNomeArquivo(rs.getString(3));
                livro.setGenero(rs.getString(4));

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Editora WHERE CNPJ = ?");
                preparedStatement.setString(1, rs.getString(5));

                ResultSet rs1 = preparedStatement.executeQuery();
                rs1.next();

                livro.setEditora(new Editora(rs1.getString(1), rs1.getString(2)));

                preparedStatement = connection.prepareStatement("SELECT AVG(classificacao) FROM Classificacao" +
                        " WHERE FK_Livro_ISBN = ?");

                preparedStatement.setString(1, livro.getIsbn());
                rs1 = preparedStatement.executeQuery();
                rs1.next();

                livro.setClassificacaoMedia(rs1.getFloat(1));

                preparedStatement = connection.prepareStatement("SELECT * FROM Autor A INNER JOIN AutorLivro L on " +
                        "A.id = L.FK_Autor_id WHERE L.FK_Livro_ISBN = ?");

                preparedStatement.setString(1, livro.getIsbn());
                rs1 = preparedStatement.executeQuery();

                List<Autor> autores = new ArrayList<>();

                while (rs1.next()) {

                    Autor autor = new Autor(rs1.getString(1), rs1.getDate(2)
                            , rs1.getString(3),rs1.getInt(4));

                    autores.add(autor);
                }

                livro.setAutores(autores);

                livros.add(livro);
            }

            return livros;

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
