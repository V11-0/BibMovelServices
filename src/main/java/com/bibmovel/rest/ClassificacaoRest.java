package com.bibmovel.rest;

import com.bibmovel.controller.ClassificacaoController;
import com.bibmovel.models.Classificacao;
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
public class ClassificacaoRest {

    @GET
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClassificaoes(@PathParam("isbn") String isbn) {

        List<Classificacao> classificacoes = null;

        try {
            ClassificacaoController classificacaoController = new ClassificacaoController();
            classificacoes = classificacaoController.getClassificacoesByLivro(isbn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(classificacoes);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClassificacao(Classificacao classificacao) {

        if (classificacao.getLivro() == null || classificacao.getUsuario() == null)
            return Response.status(406).build();
        else {

            try {
                ClassificacaoController dao = new ClassificacaoController();
                dao.addClassificacao(classificacao);

                return Response.ok(classificacao).build();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Response.serverError().build();
    }
}
