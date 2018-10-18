package com.bibmovel.rest;

import com.bibmovel.dao.LivroDAO;
import com.bibmovel.entidades.Livro;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:46
 */

@Path("/livros")
public class Livros {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        LivroDAO dao = new LivroDAO();
        List<Livro> livros = null;

        try {
            livros = dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livros);
    }

    @GET
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLivro(@PathParam("isbn") String isbn) {

        LivroDAO dao = new LivroDAO();
        Livro livro = null;

        try {
            livro = dao.getLivro(isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livro);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicInfo() {

        LivroDAO dao = new LivroDAO();
        List<Livro> livros = null;

        try {
            livros = dao.getBasicInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(livros);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Livro livro) {

        LivroDAO dao = new LivroDAO();

        if (livro == null) {
            return Response.noContent().build();
        } else {
            try {
                dao.insert(livro);
                return Response.status(201).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
    }
}
