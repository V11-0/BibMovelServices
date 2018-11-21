package com.bibmovel.rest;

import com.bibmovel.dao.ClassificacaoDAO;
import com.bibmovel.entidades.Classificacao;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vinibrenobr11 on 14/11/18 at 17:09
 */
@Path("/classificacao")
public class Classificacoes {

    @GET
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClassificaoes(@PathParam("isbn") String isbn) {

        List<Classificacao> classificacoes = null;

        try {
            ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();
            classificacoes = classificacaoDAO.getClassificacoesByLivro(isbn);

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(classificacoes);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClassificacao(Classificacao classificacao) {

        if (classificacao == null)
            return Response.noContent().build();
        else {

            try {
                ClassificacaoDAO dao = new ClassificacaoDAO();
                dao.addClassificacao(classificacao);

                return Response.ok(classificacao).build();

            } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return Response.serverError().build();
    }
}
