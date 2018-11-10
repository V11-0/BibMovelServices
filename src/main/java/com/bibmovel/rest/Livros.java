package com.bibmovel.rest;

import com.bibmovel.dao.LivroDAO;
import com.bibmovel.entidades.Livro;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:46
 */

@Path("/livro")
public class Livros {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        List<Livro> livros = null;

        try {
            LivroDAO dao = new LivroDAO();
            livros = dao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livros);
    }

    @GET
    @Path("/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLivro(@PathParam("value") String valor, @QueryParam("column") String coluna) {

        Livro livro = null;

        try {
            LivroDAO dao = new LivroDAO();
            livro = dao.getLivro(valor, coluna);
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livro);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicInfo() {

        List<Livro> livros = null;

        try {
            LivroDAO dao = new LivroDAO();
            livros = dao.getBasicInfo();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livros);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Livro livro) {

        try {

            LivroDAO dao = new LivroDAO();

            if (livro == null) {
                return Response.noContent().build();
            } else {
                dao.insert(livro);
                return Response.status(201).build();
            }

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
