package com.bibmovel.api;

import com.bibmovel.controller.LivroController;
import com.bibmovel.models.Livro;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:46
 */

@Path("/livro")
public class LivroRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response baixarLivro() {
        // TODO
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLivros() {
        // TODO
        return null;
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
