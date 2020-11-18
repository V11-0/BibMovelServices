package com.bibmovel.api;

import com.bibmovel.controller.LivroController;
import com.bibmovel.controller.SessaoController;
import com.bibmovel.models.Livro;
import com.bibmovel.models.Usuario;
import com.bibmovel.models.requests.SessaoRequest;
import com.bibmovel.values.Internals;
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
public class LivroRest {

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response baixarLivro(@PathParam("id") int id) {
        return null;
    }

    @POST
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLivros(SessaoRequest request) {
        
        if (request.getOperationKey().equals(Internals.generalKey)) {
         
            SessaoController controller = new SessaoController();
            Usuario usuario = new Usuario();

            try {
                usuario = controller.validate(request.getSessao());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (usuario.getUsuario() != null) {

                try {
                    LivroController dao = new LivroController();
                    List<Livro> livros = dao.getBasicInfo();
        
                    return Response.ok(new Gson().toJson(livros)).build();
        
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
        
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.FORBIDDEN).build();
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
