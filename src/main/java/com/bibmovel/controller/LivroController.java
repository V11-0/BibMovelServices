package com.bibmovel.controller;

import com.bibmovel.models.Livro;
import com.bibmovel.utils.ConnectionFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:29
 */
public class LivroController {

    private Connection conn;

    public LivroController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        conn = ConnectionFactory.getConnection();
    }

    public List<Livro> getBasicInfo() throws SQLException {

        List<Livro> livros = new ArrayList<>();

        ResultSet rs = conn.prepareStatement("SELECT titulo, nomeArquivo, autor, ROUND(AVG(classificacao), 2) " +
                "FROM Livro L LEFT JOIN Classificacao C on L.ISBN = C.FK_Livro_ISBN " +
                "GROUP BY titulo, nomeArquivo, autor").executeQuery();

        Livro livro;

        while (rs.next()) {
            livro = new Livro(rs.getString(1), rs.getString(2)
                    , rs.getString(3), rs.getFloat(4));

            livros.add(livro);
        }
        return livros;
    }

    public void insert(Livro livro) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("INSERT INTO Livro VALUES (?, ?, ?, ?, ?, ?, ?)");

        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getIsbn());
        statement.setString(3, livro.getNomeArquivo());
        statement.setString(4, livro.getGenero());
        statement.setShort(5, livro.getAnoPublicacao());
        statement.setString(6, livro.getEditora());
        statement.setString(7, livro.getAutor());

        statement.executeUpdate();
    }

    public Livro getLivro(String valor, String coluna) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement
                ("SELECT * FROM Livro WHERE " + coluna + " = ?");

        preparedStatement.setString(1, valor);

        ResultSet rs = preparedStatement.executeQuery();
        Livro livro = new Livro();

        if (rs.next()) {

            livro.setTitulo(rs.getString(1));
            livro.setIsbn(rs.getString(2));
            livro.setNomeArquivo(rs.getString(3));
            livro.setGenero(rs.getString(4));
            livro.setAnoPublicacao(rs.getShort(5));
            livro.setEditora(rs.getString(6));
            livro.setAutor(rs.getString(7));

            preparedStatement = conn.prepareStatement("SELECT AVG(classificacao) FROM Classificacao" +
                    " WHERE FK_Livro_ISBN = ?");

            preparedStatement.setString(1, livro.getIsbn());
            rs = preparedStatement.executeQuery();

            if (rs.next())
                livro.setClassificacaoMedia(rs.getFloat(1));
        }
        return livro;
    }

    public static File getCoverByPath(String path) throws IOException {

        File book = new File("/srv/bibmovel/Books/" + path);

        PDDocument pdDocument = PDDocument.load(book);
        PDFRenderer renderer = new PDFRenderer(pdDocument);

        File image_file = new File(path);
        BufferedImage image = renderer.renderImage(0);
        ImageIO.write(image, "png", image_file);

        pdDocument.close();

        return image_file;
    }
}