package com.bibmovel.rest;

import com.bibmovel.controller.LivroController;
import com.bibmovel.models.Livro;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:46
 */

@Path("/livro")
public class Livros {

    @GET
    @Path("/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLivro(@PathParam("value") String valor, @QueryParam("column") String coluna) {

        Livro livro = null;

        try {
            LivroController dao = new LivroController();
            livro = dao.getLivro(valor, coluna);
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        if ((livro != null ? livro.getIsbn() : null) == null)
            return Response.status(404).build();

        return Response.ok(livro).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicInfo() {

        List<Livro> livros = null;

        try {
            LivroController dao = new LivroController();
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

            LivroController dao = new LivroController();

            if (livro == null || livro.getIsbn() == null)
                return Response.status(406).build();

            else {
                dao.insert(livro);
                return Response.ok().build();
            }

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {

            if (e instanceof SQLException)
                return Response.status(409).build();

            return Response.serverError().build();
        }
    }

    @GET
    @Path("cover/{path}")
    @Produces("image/png")
    public Response getCapa(@PathParam("path") String path) {

        try {

            File cover = LivroController.getCoverByPath(path);

            if (cover == null)
                return Response.status(404).build();

            return Response.ok(cover).build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.serverError().build();
    }
}
