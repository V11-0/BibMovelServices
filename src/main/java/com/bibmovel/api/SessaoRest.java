package com.bibmovel.api;

import com.bibmovel.controller.SessaoController;
import com.bibmovel.models.Sessao;
import com.bibmovel.models.Usuario;
import com.bibmovel.models.requests.SessaoRequest;
import com.bibmovel.values.Internals;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 26/06/2020 at 16:46
 */
@Path("/sessao")
public class SessaoRest {

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateSession(SessaoRequest sessaoRequest) {

        if (sessaoRequest.getOperationKey().equals(Internals.generalKey)) {

            try {
                Sessao sessao = sessaoRequest.getSessao();
                SessaoController sessaoController = new SessaoController();

                Usuario usuario = sessaoController.validate(sessao);

                if (usuario == null) {
                    return Response.status(Response.Status.FORBIDDEN).build();
                } else {
                    return Response.ok(usuario).build();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.serverError().build();
    }
}
