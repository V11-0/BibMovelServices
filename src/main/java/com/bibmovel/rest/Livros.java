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
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.getAll();

        return new Gson().toJson(livros);
    }

    @GET
    @Path("/basic")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicInfo() {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.getBasicInfo();

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
