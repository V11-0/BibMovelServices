package com.bibmovel.rest;

import com.bibmovel.controller.UsuarioController;
import com.bibmovel.models.Sessao;
import com.bibmovel.models.Usuario;
import com.bibmovel.models.requests.UsuarioRequest;
import com.bibmovel.values.Internals;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:03
 */
@Path("/usuario")
public class Usuarios {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(UsuarioRequest usuarioRequest) {

        if (usuarioRequest.getOperationKey().equals(Internals.generalKey)) {

            Usuario usuario = usuarioRequest.getUsuario();

            if (usuario.getUsuario() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            try {
                UsuarioController controller = new UsuarioController();

                if (controller.add(usuario)) {
                    return Response.created(null).build();
                } else {
                    return Response.status(Response.Status.CONFLICT).build();
                }

            } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioRequest usuarioRequest) {

        if (usuarioRequest.getOperationKey().equals(Internals.generalKey)) {
            try {
                UsuarioController dao = new UsuarioController();
                Usuario usuario = usuarioRequest.getUsuario();
                List<String> device = usuarioRequest.getDeviceInfo();

                Sessao sessao = dao.login(usuario, device);

                if (sessao != null) {
                    return Response.ok(sessao).build();
                } else {
                    return Response.status(Response.Status.UNAUTHORIZED).build();
                }

            } catch (IllegalAccessException | InstantiationException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/google")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyGoogleAccount(Usuario google) {

        try {
            UsuarioController dao = new UsuarioController();
            return Response.ok(dao.verify(google)).build();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }
}
